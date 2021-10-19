package com.konkuk.kureal.controller;

import io.swagger.annotations.ApiOperation;
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
}