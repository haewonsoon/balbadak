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

    @GetMapping("/file/FileArrAPI")
    @ResponseBody
    public ArrayList<HashMap<String,Object>> showImg2() throws IOException {
        ArrayList<HashMap<String,Object>> result = new ArrayList<>();
        List<BbdFile> fileList = bbdFileRepository.findAll();
        
        for (int i=0; i < fileList.size(); i++) {
        	BbdFile file = fileList.get(i);
        	HashMap<String,Object> map = new HashMap<>();
        	
        	map.put("itemPostId", file.getPostId());
        	map.put("itemFileId", file.getFileId());
            map.put("itemFilePath", baseUrl + "/upload" + file.getFilePath() + file.getFileName());
            map.put("itemFileName", file.getFileName());
            map.put("itemContent", "안녕 난 뉴욕에 사는 존이야 언제든 궁금한게 있다면, 편하게 물어봐");

            result.add(i, map);
        }

        return result;
    }

}
