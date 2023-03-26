package com.back.balbadak.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class testAPIController {

    @RequestMapping(value="/test/testAPI")
    @ResponseBody
    public HashMap<String,Object> testAPI () {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", "haewon");
        result.put("age", "31");
        result.put("apple", "apple");

        return result;
    }
}
