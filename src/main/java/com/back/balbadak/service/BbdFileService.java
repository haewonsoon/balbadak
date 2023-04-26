package com.back.balbadak.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdFile.BbdFileRepository;
import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.model.CommonResponse;
import com.back.balbadak.utils.FileUtils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BbdFileService {
	
	@Value("${file.resource.upload.path}")
	private String uploadPath;

	private final BbdFileRepository bbdFileRepository;

	public BbdFileService(BbdFileRepository bbdFileRepository) {
		this.bbdFileRepository = bbdFileRepository;
	}

	public CommonResponse<List<BbdFile>> fileSave(HttpServletRequest request, BbdPost postVO)
			throws IllegalStateException, IOException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		List<BbdFile> list = FileUtils.fileUpload(multiRequest, postVO, uploadPath);
		if (list.size() > 0)
			bbdFileRepository.saveAll(list);

		return CommonResponse.create(list);
	}

}
