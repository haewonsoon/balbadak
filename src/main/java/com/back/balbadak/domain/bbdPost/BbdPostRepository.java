package com.back.balbadak.domain.bbdPost;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface BbdPostRepository extends JpaRepository<BbdPost, Long> {

	List<BbdPost> findAllByOrderByCreateDtDesc();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE bbd_post p set p.post_content = :postContent where p.post_id = :postId", nativeQuery = true)
	int updatePost(@Param("postId") Long postId, @Param("postContent") String postContent);
}
