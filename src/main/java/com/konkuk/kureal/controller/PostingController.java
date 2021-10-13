package com.konkuk.kureal.controller;

import com.konkuk.kureal.dao.response.BasicResponse;
import com.konkuk.kureal.dao.response.SuccessResponse;
import com.konkuk.kureal.dto.Postings;
import com.konkuk.kureal.service.PostingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        int insertResult = postingService.insertArticleWithPostingData(posting_data);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse(200, "posting 등록 성공")); //성공 response
    }

    @GetMapping(path = "/{pk}")
    @ApiOperation(value = "글 조회", notes = "조회할 글의 pk값을 주시면 글 정보를 반환합니다.")
    public Postings selectOneArticleWithPk(@Parameter(description = "조회할 글의 pk값을 주시면 글 정보를 반환합니다.", required = true) @PathVariable(name = "pk") int pk) {
        Postings posting = postingService.selectOneArticleWithPk(pk);

        return posting;
    }
}
