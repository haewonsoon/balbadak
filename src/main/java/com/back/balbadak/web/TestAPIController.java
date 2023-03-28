package com.back.balbadak.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class TestAPIController {

    @RequestMapping(value="/test/testAPI")
    @ResponseBody
    public HashMap<String,Object> testAPI () {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", "haewon");
        result.put("age", "31");
        result.put("apple", "apple");

        return result;
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value="/test/testArrayAPI")
    @ResponseBody
    public ArrayList<ArrayList<String>> testArrayAPI () {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> arr = new ArrayList<>();

        arr.add(0,"a");
        arr.add(1,"b");
        arr.add(2,"c");
        arr.add(3,"d");
        arr.add(4,"e");
        arr.add(5,"f");

        result.add(0, (ArrayList<String>) arr.clone());

        arr.set(0,"1");
        arr.set(1,"2");
        arr.set(2,"3");
        arr.set(3,"4");
        arr.set(4,"5");
        arr.set(5,"6");

        result.add(1, (ArrayList<String>) arr.clone());

        return result;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value="/test/testArrayMapAPI")
    @ResponseBody
    public ArrayList<HashMap<String,Object>> testArrayMapAPI () {
        ArrayList<HashMap<String,Object>> result = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();

        map.put("name", "John Doe");
        map.put("age", "33");
        map.put("country", "USA");
        map.put("city", "New York City");
        map.put("memo", "안녕 난 뉴욕에 사는 존이야 언제든 궁금한게 있다면, 편하게 물어봐");

        result.add(0, (HashMap<String, Object>) map.clone());

        map.put("name", "Sopia Kim");
        map.put("age", "56");
        map.put("country", "France");
        map.put("city", "Paris");
        map.put("memo", "이건 나의 손녀가 관리하는 계정입니다.");

        result.add(1, (HashMap<String, Object>) map.clone());
        map.put("name", "min son");
        map.put("age", "17");
        map.put("country", "Korea");
        map.put("city", "Seoul");
        map.put("memo", "서울고등학교 1학년 8반 손민입니다. 반갑습니다.");

        result.add(2, (HashMap<String, Object>) map.clone());

        return result;
    }

}
