package com.konkuk.kureal.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkuk.kureal.dao.response.BasicResponse;
import com.konkuk.kureal.dao.response.CommonResponse;
import com.konkuk.kureal.dao.response.ErrorResponse;
import com.konkuk.kureal.dao.response.SuccessResponse;
import com.konkuk.kureal.dto.Postings;
import com.konkuk.kureal.service.PostingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/posting") // 해당 주소로 들어오면 아래의 함수를 메소드와 함께 이용 가능, 더 자원을 표시하고 싶다면 더 표시 가능
public class PostingController {
    private PostingService postingService;

    @Autowired
    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @PostMapping
    @ApiOperation(value = "포스팅 등록", notes = "사진과 article을 이용하여 글을 데이터베이스에 저장합니다.(pk값 불필요)")
    public ResponseEntity<? extends BasicResponse> insertArticleWithPostingData(@RequestBody Postings posting_data) {
        int building = -1;
        String s3Url = posting_data.getPhoto().toString(); // kureal/ 뒤에 자르기
        String[] s3Arr = s3Url.split("/");

        building = getBuildingWithS3Url("photo/"+s3Arr[5]);
        posting_data.setBuilding(building);

        int insertResult = postingService.insertArticleWithPostingData(posting_data);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(200, "posting 등록 성공")); //성공 response
    }

    @GetMapping(path = "/{pk}")
    @ApiOperation(value = "글 조회", notes = "조회할 글의 pk값을 주시면 글 정보를 반환합니다.")
    public Postings selectOneArticleWithPk(@Parameter(description = "조회할 글의 pk값을 주시면 글 정보를 반환합니다.", required = true) @PathVariable(name = "pk") int pk) {
        Postings posting = postingService.selectOneArticleWithPk(pk);

        return posting;
    }

    @GetMapping
    @ApiOperation(value = "글 리스트 조회", notes = "찍은 사진의 근방에 존재하는 article 리스트를 리턴합니다. ")
    public ResponseEntity<? extends BasicResponse> selectArticleWithS3Url(@RequestParam @ApiParam(value = "이미지 S3 주소", required = true) String s3Url){
        //플라스크 돌려서 건물 번호(building) get하기
        int building = -1;
        //photo/JPEG_20211014_113822_6724216538930263291.jpg
        String[] s3Arr = s3Url.split("/");

        building = getBuildingWithS3Url("photo/"+s3Arr[5]);

        //해당 빌딩 글 조회
        List<Postings> postingResult = postingService.selectArticleWithBuilding(building);
        if(postingResult.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("조회 정보가 존재하지 않습니다. 이미지 URl주소가 올바른지 확인해주세요")); //실패 response
        }
        return ResponseEntity.ok().body(new CommonResponse<List<Postings>>(postingResult)); //성공 data response
    }

    private int getBuildingWithS3Url(String s3Url){
        int building = -1;
        try {
            //String url = "http://15.164.48.206:8090/imageMatching?img_path="; 	// 외부 API URL
            String url = "http://ec2-15-164-48-206.ap-northeast-2.compute.amazonaws.com:8090/imageMatching?img_path="; 	// 외부 API URL

            url += s3Url;

            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            try {
                HttpResponse response = client.execute(httpGet);

                if (response.getStatusLine().getStatusCode() == 200) {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    String body = handler.handleResponse(response);
                    System.out.println("[RESPONSE] requestHttpForm() " + body);

                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode node = objectMapper.readTree(body);
                    building = node.get("result").asInt();
                } else {
                    System.out.println("response is error : " + response.getStatusLine().getStatusCode());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch(HttpClientErrorException | HttpServerErrorException e) {
            System.out.println(e.toString());
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return building;
    }
}