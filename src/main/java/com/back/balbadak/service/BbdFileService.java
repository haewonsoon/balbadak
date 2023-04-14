package com.back.balbadak.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdFile.BbdFileRepository;
import com.back.balbadak.domain.bbdPost.BbdPost;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class BbdFileService {
	
	private final BbdFileRepository bbdFileRepository;
	
	public BbdFileService(BbdFileRepository bbdFileRepository) {
		this.bbdFileRepository = bbdFileRepository;
	}
	
	public List<BbdFile> fileSave(HttpServletRequest request, BbdPost postVO) throws IllegalStateException, IOException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		List<BbdFile> list = new ArrayList<BbdFile>();

		List<MultipartFile> fileMap = multiRequest.getFiles("files");
		for(MultipartFile multipartFile : fileMap) {
			if(!multipartFile.isEmpty()) {
				Long fileSize = multipartFile.getSize();	// 파일 크기
				String fileName = multipartFile.getOriginalFilename();	// 원래 파일명

				//변경된 파일이름 구하기
//				String fileName = getUniqueFileName(oName);

				//업로드할 폴더 구하기
//				String uploadPath = getUploadPath(request, uploadFlag);

				//파일 업로드 처리
				File file = new File("C:/user/2023/04/", fileName);
				multipartFile.transferTo(file);

				//업로드된 파일 정보 저장, (파일크기, 파일명2개를 VO나 Map으로 묶으면 된다)
				//[1] Map에 저장
				BbdFile newFile = new BbdFile(); // <>뒤에는 생략가능
				newFile.setFileName(fileName);
				newFile.setFileExtsn(fileName);
				newFile.setFilePath("/2023/04/");
				newFile.setFileSize(fileSize);
				newFile.setPostId(postVO.getPostId());

				//[2] 여러 개의 Map을 List에 저장
				list.add(newFile);
			}
		}
		
		return bbdFileRepository.saveAll(list);
	}

}
