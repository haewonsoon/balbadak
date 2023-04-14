package com.back.balbadak.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.balbadak.domain.bbdFile.BbdFile;

import jakarta.servlet.http.HttpServletRequest;

public class FileUtlis {

	// TODO 1. 파일업로드 2. 파일명 생성 3. 파일업로드 DIR 경로 생성
	public List<BbdFile> fileUpload (HttpServletRequest request) throws IllegalStateException, IOException {
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

				//[2] 여러 개의 Map을 List에 저장
				list.add(newFile);
			}
		}
		
		return list;
	}
	
	public String getUniqueFileName(String fileName) {
		//파일명이 중복될 경우 파일이름 변경하기
		//파일명에 현재시간(년원일 시분초 밀리초)을 붙여서 변경된 파일이름 구하기
		//ex) a.txt => a_20220602113820123.txt
		
		//순수 파일명만 구하기 => a
		int idx = fileName.lastIndexOf(".");
		String fileNm = fileName.substring(0, idx);		// a
		
		//확장자 구하기 => .txt
		String ext = fileName.substring(idx);		// .txt
		
		//변경된 파일이름
		Date d = new Date();
		SimpleDateFormat sdf = new  SimpleDateFormat("yyyyMMddHHmmssSSS");
		// => '년원일시분초밀리초' 포멧
		String today = sdf.format(d);
		String result = fileNm + "_" + today + ext;
		
		return result;
	}
	
}
