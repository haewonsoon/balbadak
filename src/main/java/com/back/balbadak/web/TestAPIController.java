package com.back.balbadak.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @SuppressWarnings("unchecked")
    @GetMapping("/fileTest1")
    @ResponseBody
    public ArrayList<HashMap<String,Object>> showImg() throws IOException {
    	ArrayList<HashMap<String,Object>> result = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        
    	File file1 = new File("C:/user/1.png");
    	byte[] byte1 = Files.readAllBytes(file1.toPath());
    	byte[] file1Base64 = Base64.getEncoder().encode(byte1);
//    	String str1 = Base64.getEncoder().encodeToString(byte1);
    	
    	map.put("file", file1Base64);
    	map.put("name", "John Doe");
        map.put("age", "33");
        map.put("country", "USA");
        map.put("city", "New York City");
        map.put("memo", "안녕 난 뉴욕에 사는 존이야 언제든 궁금한게 있다면, 편하게 물어봐");

        result.add(0, (HashMap<String, Object>) map.clone());
        
        File file2 = new File("C:/user/2.png");
    	byte[] byte2 = Files.readAllBytes(file2.toPath());
    	byte[] file2Base64 = Base64.getEncoder().encode(byte2);
//    	String str1 = Base64.getEncoder().encodeToString(byte1);
    	
    	map.put("file", file2Base64);
        map.put("name", "Sopia Kim");
        map.put("age", "56");
        map.put("country", "France");
        map.put("city", "Paris");
        map.put("memo", "이건 나의 손녀가 관리하는 계정입니다.");

        result.add(1, (HashMap<String, Object>) map.clone());
        
        File file3 = new File("C:/user/3.png");
    	byte[] byte3 = Files.readAllBytes(file3.toPath());
    	byte[] file3Base64 = Base64.getEncoder().encode(byte3);
//    	String str1 = Base64.getEncoder().encodeToString(byte1);
    	
    	map.put("file", file3Base64);
        map.put("name", "min son");
        map.put("age", "17");
        map.put("country", "Korea");
        map.put("city", "Seoul");
        map.put("memo", "서울고등학교 1학년 8반 손민입니다. 반갑습니다.");

        result.add(2, (HashMap<String, Object>) map.clone());
    	
    	return result;
    }
    
    @GetMapping("/fileTest2")
    public ResponseEntity<byte[]> showImg1() throws IOException {
        File file = new File("C:/user/2.png");
        byte[] byte1 = Files.readAllBytes(file.toPath());
        byte[] base64 = Base64.getEncoder().encode(byte1);
        String str1 = Base64.getEncoder().encodeToString(byte1);

        return new ResponseEntity<>(base64, HttpStatus.OK);
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/fileTest3")
    @ResponseBody
    public ArrayList<HashMap<String,Object>> showImg2() throws IOException {
    	ArrayList<HashMap<String,Object>> result = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();
        
    	map.put("itemFileId", "1");
    	map.put("itemFilePath", "http://10.10.76.199:8070/upload/1.png");
    	map.put("itemFileName", "1.png");
        map.put("itemContent", "안녕 난 뉴욕에 사는 존이야 언제든 궁금한게 있다면, 편하게 물어봐");

        result.add(0, (HashMap<String, Object>) map.clone());
        
    	map.put("itemFileId", "2");
    	map.put("itemFilePath", "http://10.10.76.199:8070/upload/2.png");
        map.put("itemFileName", "Sopia Kim");
        map.put("itemContent", "이건 나의 손녀가 관리하는 계정입니다.");

        result.add(1, (HashMap<String, Object>) map.clone());
        
    	map.put("itemFileId", "3");
    	map.put("itemFilePath", "http://10.10.76.199:8070/upload/3.png");
        map.put("itemFileName", "min son");
        map.put("itemContent", "서울고등학교 1학년 8반 손민입니다. 반갑습니다.");

        result.add(2, (HashMap<String, Object>) map.clone());
        
        map.put("itemFileId", "4");
        map.put("itemFilePath", "http://10.10.76.199:8070/upload/4.png");
    	map.put("itemFileName", "John Doe");
        map.put("itemcontent", "안녕 난 뉴욕에 사는 존이야 언제든 궁금한게 있다면, 편하게 물어봐");

        result.add(3, (HashMap<String, Object>) map.clone());
        
        map.put("itemFileId", "5");
        map.put("itemFilePath", "http://10.10.76.199:8070/upload/2.png");
        map.put("itemFileName", "Sopia Kim");
        map.put("itemContent", "이건 나의 손녀가 관리하는 계정입니다.");

        result.add(4, (HashMap<String, Object>) map.clone());
        
        map.put("itemFileId", "6");
    	map.put("itemFilePath", "http://10.10.76.199:8070/upload/3.png");
        map.put("itemFileName", "min son");
        map.put("itemContent", "서울고등학교 1학년 8반 손민입니다. 반갑습니다.");

        result.add(5, (HashMap<String, Object>) map.clone());
    	
    	return result;
    }

}
