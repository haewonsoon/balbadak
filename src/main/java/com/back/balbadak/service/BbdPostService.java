package com.back.balbadak.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.back.balbadak.domain.bbdFile.BbdFile;
import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.domain.bbdPost.BbdPostRepository;
import com.back.balbadak.utils.DateTimeUtils;

import jakarta.transaction.Transactional;

@Service
public class BbdPostService {
	@Value("${url.base}")
    private String baseUrl;
    
    @Value("${file.upload.path}")
	private String fileUploadPath;
	
	private final BbdPostRepository bbdPostRepository;
	
	public BbdPostService(BbdPostRepository bbdPostRepository) {
		this.bbdPostRepository = bbdPostRepository;
	}
	
	public ArrayList<HashMap<String,Object>> postFetchAll() {
		ArrayList<HashMap<String,Object>> result = new ArrayList<>();
        List<BbdPost> postList = bbdPostRepository.findAllByOrderByCreateDtDesc();
        
        for (BbdPost post : postList) {
        	HashMap<String,Object> postMap = new HashMap<>();
        	ArrayList<HashMap<String,Object>> files = new ArrayList<>();
        	
        	for (BbdFile file : post.getFiles()) {
        		HashMap<String,Object> fileMap = new HashMap<>();
        		fileMap.put("fileId", file.getFileId());
        		fileMap.put("filePath", baseUrl + fileUploadPath + file.getFilePath() + "thumb_" + file.getFileName());
        		fileMap.put("fileName", file.getFileOriginName());
        		files.add(fileMap);
        	}
        	
        	postMap.put("postId", post.getPostId());
        	postMap.put("postContent", post.getPostContent());
        	postMap.put("postLike", post.getPostLike());
        	postMap.put("createDt", DateTimeUtils.getTextDateFormat(post.getCreateDt()));
        	postMap.put("files", files);
        	
            result.add(postMap);
        }

        return result;
	}
	
	@Transactional
	public BbdPost postUpdate(BbdPost post) {
		BbdPost result = BbdPost.builder().build();
		if (post.getPostId() != null) {
			result = bbdPostRepository.save(post);
		}
		return result;
	}
	
	public BbdPost postSave(BbdPost post) {
		return bbdPostRepository.save(post);
	}

	public Optional<BbdPost> postFetchById(BbdPost post) {
		return bbdPostRepository.findById(post.getPostId());
	}
}
