package com.back.balbadak.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.domain.bbdPost.BbdPostRepository;

import jakarta.transaction.Transactional;

@Service
public class BbdPostService {

	private final BbdPostRepository bbdPostRepository;
	
	public BbdPostService(BbdPostRepository bbdPostRepository) {
		this.bbdPostRepository = bbdPostRepository;
	}
	
	public List<BbdPost> postFetchAll() {
        return bbdPostRepository.findAll();
	}
	
	@Transactional
	public void postUpdate(BbdPost post) {
		
	}
}
