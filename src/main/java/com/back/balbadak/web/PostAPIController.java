package com.back.balbadak.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.model.CommonResponse;
import com.back.balbadak.service.BbdFileService;
import com.back.balbadak.service.BbdPostService;

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
	public ResponseEntity<CommonResponse<ArrayList<HashMap<String, Object>>>> postFetchAPI() {
		CommonResponse<ArrayList<HashMap<String, Object>>> result = CommonResponse
				.create(bbdPostService.postFetchAll());
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/postFetchByIdAPI")
	public ResponseEntity<CommonResponse<Optional<BbdPost>>> postFetchByIdAPI(@RequestBody BbdPost postVO) {
		CommonResponse<Optional<BbdPost>> result = CommonResponse
				.create(bbdPostService.postFetchById(postVO));
		return ResponseEntity.ok(result);
	}

	@PostMapping("/postSaveAPI")
	public ResponseEntity<CommonResponse<List<BbdFile>>> postSaveAPI(HttpServletRequest httpServletRequest,
			@RequestPart(value = "content") BbdPost postVO) throws IOException {
		BbdPost newPost = bbdPostService.postSave(postVO);
		List<BbdFile> files = bbdFileService.fileSave(httpServletRequest, newPost);
		CommonResponse<List<BbdFile>> result = CommonResponse.create(files);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/postUpdateAPI")
	public ResponseEntity<CommonResponse<Integer>> postUpdateAPI(@RequestBody BbdPost postVO) throws IOException {
		CommonResponse<Integer> result = CommonResponse.create(bbdPostService.postUpdate(postVO));
		return ResponseEntity.ok(result);
	}
}
