package com.back.balbadak.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.service.BbdPostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class PostAPIController {
	
	private final BbdPostService bbdPostService;
	
	public PostAPIController(BbdPostService bbdPostService) {
		this.bbdPostService = bbdPostService;
	}

	@RequestMapping(value="/post/postFetchAPI")
    @ResponseBody
    public List<BbdPost> postFetchAPI() {
    	List<BbdPost> result = bbdPostService.postFetchAll();
        return result;
    }
	
	@RequestMapping(value="/post/postUpdateAPI")
	@ResponseBody
	public void postUpdateAPI() {
	}
	
	@PostMapping("/filesUpload")
    public String filesUpload(@RequestParam("files") List<MultipartFile> files, @RequestParam(value = "content" , required=false) String content) throws IOException {
		/*
		 * fileService.saveFile(file);
		 * 
		 * for (MultipartFile multipartFile : files) {
		 * fileService.saveFile(multipartFile); }
		 */
		for (MultipartFile multipartFile : files) {
			 System.out.println(multipartFile.getOriginalFilename());
		}
		
		GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        BbdPost post = gson.fromJson(content,BbdPost.class);
		
		System.out.println(post.getPostContent());
		return "OK 200";
    }
	
	@PostMapping("/fileUpload")
    public void fileUpload(@RequestParam("files") MultipartFile file) throws IOException {
		/*
		 * fileService.saveFile(file);
		 * 
		 * for (MultipartFile multipartFile : files) {
		 * fileService.saveFile(multipartFile); }
		 */
    }
}
