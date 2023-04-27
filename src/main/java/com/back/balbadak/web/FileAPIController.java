package com.back.balbadak.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.service.BbdFileService;

@RequestMapping("file")
@RestController
public class FileAPIController {
	private final BbdFileService bbdFileService;
	
	public FileAPIController(BbdFileService bbdFileService) {
		this.bbdFileService = bbdFileService;
	}

    @RequestMapping(value="/FileFetchAPI")
    public Optional<BbdFile> FileAPI(@RequestBody BbdPost postVO) {
    	return bbdFileService.fileFetchById(postVO);
    }

    @GetMapping("/FilesFetchAPI")
    public ArrayList<HashMap<String,Object>> showImg2() throws IOException {
        return bbdFileService.fileFetchAll();
    }

}
