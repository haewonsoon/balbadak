package com.back.balbadak.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdPost.BbdPost;

import jakarta.servlet.http.HttpServletRequest;
import net.coobird.thumbnailator.Thumbnails;
import com.back.balbadak.consts.Constant;

public class FileUtils {
	public static List<BbdFile> fileUpload (HttpServletRequest request, BbdPost postVO, String uploadPath) throws IllegalStateException, IOException {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
		List<BbdFile> list = new ArrayList<BbdFile>();

		List<MultipartFile> fileMap = multiRequest.getFiles("files");
		for(MultipartFile multipartFile : fileMap) {
			if(!multipartFile.isEmpty()) {
				Long fileSize = multipartFile.getSize();	// 파일 크기
				String oName = multipartFile.getOriginalFilename();	// 원래 파일명
				// 변경된 파일이름 구하기
				String fileName = getUniqueFileName(oName);
				String filePath = getUploadPath();

				File dir = new File(uploadPath + filePath);
				if(!dir.exists()) dir.mkdirs();
				
				// 파일 업로드 처리
				File file = new File(uploadPath + filePath, fileName);
				multipartFile.transferTo(file);
				
				// 썸네일 생성
				Thumbnails
		        .of(new File(uploadPath + filePath, fileName))
		        .forceSize(Constant.IMAGE_THUMB_WIDTH, Constant.IMAGE_THUMB_HEIGHT)
		        .toFile(new File(uploadPath + filePath, "thumb_" + fileName));


				BbdFile newFile = BbdFile.builder().fileOriginName(oName).fileName(fileName).fileExtsn(getFileExtsn(oName))
						.filePath(filePath).fileSize(fileSize).bbdPost(postVO).build();

				list.add(newFile);
			}
		}
		
		return list;
	}
	
	public static String getUniqueFileName(String fileName) {
		// 파일명이 중복될 경우 파일이름 변경하기
		// 파일명에 현재시간(년원일 시분초 밀리초)을 붙여서 변경된 파일이름 구하기
		// ex) a.txt => a_20220602113820123.txt
		
		// 순수 파일명만 구하기 => a
		int idx = fileName.lastIndexOf(".");
		String fileNm = fileName.substring(0, idx);		// a
		
		// 확장자 구하기 => .txt
		String ext = fileName.substring(idx);		// .txt
		
		// 변경된 파일이름
		String result = fileNm + "_" + DateTimeUtils.nowSimpleDateTime() + ext;
		
		return result;
	}
	
	public static String getFileExtsn(String fileName) {
		int idx = fileName.lastIndexOf(".");
		// 확장자 구하기 => .txt
		String ext = fileName.substring(idx + 1);		// .txt
		
		return ext;
	}
	
	public static String getUploadPath() {
		return DateTimeUtils.nowYear() + "/" + DateTimeUtils.nowMonth() + "/";
	}
	
}
