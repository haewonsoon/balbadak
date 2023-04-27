package com.back.balbadak.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
	@Value("${url.base}")
    private String baseUrl;
    
    @Value("${file.upload.path}")
	private String fileUploadPath;
	
	@Value("${file.resource.upload.path}")
	private String uploadPath;

	private final BbdFileRepository bbdFileRepository;

	public BbdFileService(BbdFileRepository bbdFileRepository) {
		this.bbdFileRepository = bbdFileRepository;
	}
	
	public ArrayList<HashMap<String,Object>> fileFetchAll() {
		ArrayList<HashMap<String,Object>> result = new ArrayList<>();
        List<BbdFile> fileList = bbdFileRepository.findAllByOrderByCreateDtDesc();
        
        for (int i=0; i < fileList.size(); i++) {
        	BbdFile file = fileList.get(i);
        	HashMap<String,Object> map = new HashMap<>();
        	
        	map.put("postId", file.getBbdPost().getPostId());
        	map.put("fileId", file.getFileId());
            map.put("filePath", baseUrl + fileUploadPath + file.getFilePath() + "thumb_" + file.getFileName());
            map.put("fileName", file.getFileOriginName());
            map.put("postContent", file.getBbdPost().getPostContent());
            map.put("postLike", file.getBbdPost().getPostLike());
            map.put("createDt", file.getCreateDt());

            result.add(i, map);
        }

        return result;
	}
	
	public Optional<BbdFile> fileFetchById(BbdPost postVO) {
		return bbdFileRepository.findById(postVO.getPostId());
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
