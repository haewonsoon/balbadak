package com.back.balbadak.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.model.CommonResponse;
import com.back.balbadak.service.BbdFileService;

@RequestMapping("file")
@RestController
public class FileAPIController {
	private final BbdFileService bbdFileService;
	
	public FileAPIController(BbdFileService bbdFileService) {
		this.bbdFileService = bbdFileService;
	}

    @GetMapping("/FilesFetchAPI")
    public ResponseEntity<CommonResponse<ArrayList<HashMap<String,Object>>>> FilesFetchAPI() throws IOException {
    	CommonResponse<ArrayList<HashMap<String,Object>>> result = CommonResponse.create(bbdFileService.fileFetchAll());
        return ResponseEntity.ok(result);
    }
    
    @RequestMapping(value="/FileFetchAPI")
    public ResponseEntity<CommonResponse<Optional<BbdFile>>> FileFetchAPI(@RequestBody BbdFile fileVO) {
    	CommonResponse<Optional<BbdFile>> result = CommonResponse.create(bbdFileService.fileFetchById(fileVO));
    	return ResponseEntity.ok(result);
    }

}
