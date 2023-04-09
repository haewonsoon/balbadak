package com.back.balbadak.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdFile.BbdFileRepository;

@Controller
public class FileAPIController {

    @Value("${url.base}")
    private String baseUrl;
	
	private final BbdFileRepository bbdFileRepository;
	
	public FileAPIController(BbdFileRepository bbdFileRepository) {
		this.bbdFileRepository = bbdFileRepository;
	}

    @RequestMapping(value="/file/FileFetchAPI")
    @ResponseBody
    public List<BbdFile> FileFetchAPI() {
    	List<BbdFile> result = bbdFileRepository.findAll();
        
        return result;
    }
    
    @RequestMapping(value="/file/FileAPI")
    @ResponseBody
    public Optional<BbdFile> FileAPI() {
    	Optional<BbdFile> file = bbdFileRepository.findById((long) 1);
    	
    	return file;
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/file/FileArrAPI")
    @ResponseBody
    public ArrayList<HashMap<String,Object>> showImg2() throws IOException {
        ArrayList<HashMap<String,Object>> result = new ArrayList<>();
        HashMap<String,Object> map = new HashMap<>();

        map.put("itemFileId", "1");
        map.put("itemFilePath", baseUrl + "/upload/1.png");
        map.put("itemFileName", "1.png");
        map.put("itemContent", "안녕 난 뉴욕에 사는 존이야 언제든 궁금한게 있다면, 편하게 물어봐");

        result.add(0, (HashMap<String, Object>) map.clone());

        map.put("itemFileId", "2");
        map.put("itemFilePath", baseUrl + "/upload/2.png");
        map.put("itemFileName", "2.png");
        map.put("itemContent", "이건 나의 손녀가 관리하는 계정입니다.");

        result.add(1, (HashMap<String, Object>) map.clone());

        map.put("itemFileId", "3");
        map.put("itemFilePath", baseUrl + "/upload/3.png");
        map.put("itemFileName", "3.png");
        map.put("itemContent", "서울고등학교 1학년 8반 손민입니다. 반갑습니다.");

        result.add(2, (HashMap<String, Object>) map.clone());

        map.put("itemFileId", "4");
        map.put("itemFilePath", baseUrl + "/upload/4.png");
        map.put("itemFileName", "4.png");
        map.put("itemcontent", "안녕 난 뉴욕에 사는 존이야 언제든 궁금한게 있다면, 편하게 물어봐");

        result.add(3, (HashMap<String, Object>) map.clone());

        map.put("itemFileId", "5");
        map.put("itemFilePath", baseUrl + "/upload/2.png");
        map.put("itemFileName", "2.png");
        map.put("itemContent", "이건 나의 손녀가 관리하는 계정입니다.");

        result.add(4, (HashMap<String, Object>) map.clone());

        map.put("itemFileId", "6");
        map.put("itemFilePath",  baseUrl + "/upload/3.png");
        map.put("itemFileName", "3.png");
        map.put("itemContent", "서울고등학교 1학년 8반 손민입니다. 반갑습니다.");

        result.add(5, (HashMap<String, Object>) map.clone());

        return result;
    }

}
