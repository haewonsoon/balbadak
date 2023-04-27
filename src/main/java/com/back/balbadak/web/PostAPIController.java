package com.back.balbadak.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.model.CommonResponse;
import com.back.balbadak.service.BbdFileService;
import com.back.balbadak.service.BbdPostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("post")
@RestController
public class PostAPIController {

	private final BbdPostService bbdPostService;
	private final BbdFileService bbdFileService;

	public PostAPIController(BbdPostService bbdPostService, BbdFileService bbdFileService) {
		this.bbdPostService = bbdPostService;
		this.bbdFileService = bbdFileService;
	}

	@RequestMapping(value = "/postFetchAPI")
	@ResponseBody
	public ArrayList<HashMap<String,Object>> postFetchAPI() {
		return bbdPostService.postFetchAll();
	}

	@PostMapping("/filesUpload")
	public String filesUpload(@RequestParam("files") List<MultipartFile> files,
			@RequestParam(value = "content", required = false) String content) throws IOException {
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
		BbdPost post = gson.fromJson(content, BbdPost.class);

		System.out.println(post.getPostContent());
		return "OK 200";
	}

	@PostMapping("/filesUploadTest")
	public String filesUploadTest(HttpServletRequest httpServletRequest, @RequestPart(value = "content") BbdPost postVO)
			throws IOException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;

		List<MultipartFile> files = multiRequest.getFiles("files");
		for (MultipartFile multipartFile : files) {
			System.out.println(multipartFile.getOriginalFilename());
		}

		System.out.println(postVO.getPostContent());
		return "OK 200";
	}

//	@PostMapping("/postSaveAPI")
//	public HashMap<String,Object> postSaveAPI(HttpServletRequest httpServletRequest,
//			  @RequestPart(value = "content") BbdPost postVO) throws IOException {
//		BbdPost newPost = bbdPostService.postSave(postVO);
//		List<BbdFile> files = bbdFileService.fileSave(httpServletRequest, newPost);
//		
//		HashMap<String,Object> result = new HashMap<String,Object>();
//		result.put("count", files.size());
//		
//		return result;
//	}

	@PostMapping("/postSaveAPI")
	public ResponseEntity<CommonResponse<List<BbdFile>>> postSaveAPI(HttpServletRequest httpServletRequest,
			@RequestPart(value = "content") BbdPost postVO) throws IOException {
		BbdPost newPost = bbdPostService.postSave(postVO);
		CommonResponse<List<BbdFile>> files = bbdFileService.fileSave(httpServletRequest, newPost);

		return ResponseEntity.ok(files);
	}

	@PostMapping("/postUpdateAPI")
	public ResponseEntity<CommonResponse<BbdPost>> postUpdateAPI(@RequestBody BbdPost postVO)
			throws IOException {
		CommonResponse<BbdPost> newPost = bbdPostService.postUpdate(postVO);
//		CommonResponse<List<BbdFile>> files = bbdFileService.fileSave(httpServletRequest, newPost);

		return ResponseEntity.ok(newPost);
	}
}
