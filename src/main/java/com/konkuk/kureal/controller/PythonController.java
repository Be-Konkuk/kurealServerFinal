package com.konkuk.kureal.controller;

import com.konkuk.kureal.dto.Postings;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.python.util.PythonInterpreter;

@RestController
@RequestMapping(value="/api/python")
public class PythonController {
    private static PythonInterpreter interpreter;

    @Autowired
    public PythonController() {

    }

    @GetMapping
    @ApiOperation(value = "test python", notes = "파이썬 test 코드 실행")
    public String testPython() {
        System.setProperty("python.import.site","false"); //오류나서 코드 추가

        //방법1.
        interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/java/com/konkuk/kureal/python/test.py");
        interpreter.exec("print(testFunc(5,10))");

        //방법2.
        PyFunction pyFunction = (PyFunction) interpreter.get("testFunc", PyFunction.class);
        int a = 10, b = 20;
        PyObject pyobj = pyFunction.__call__(new PyInteger(a),new PyInteger(b));
        System.out.println(pyobj.toString());

        return pyobj.toString();
    }

    @GetMapping(path = "/test")
    @ApiOperation(value = "정상작동테스트", notes = "서버 정상 작동 테스트")
    public String serverTest() {
        //s3주소가 파라미터로 넘어오면 -> flask 서버 돌릴거자나
        //그거에 넘겨서 return 값 오는지 확인
        return "테스트완료";
    }
}