package com.back.balbadak.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.back.balbadak.domain.file.File;
import com.back.balbadak.domain.file.FileRepository;

@Controller
public class FileAPIController {
	
	private final FileRepository fileRepository;
	
	public FileAPIController(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

    @SuppressWarnings("unchecked")
    @RequestMapping(value="/file/FileFetchAPI")
    @ResponseBody
    public List<File> FileFetchAPI() {
    	List<File> result = fileRepository.findAll();
        
        return result;
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/file/FileAPI")
    @ResponseBody
    public Optional<File> FileAPI() {
    	Optional<File> file = fileRepository.findById((long) 1);
    	
    	return file;
    }

}
