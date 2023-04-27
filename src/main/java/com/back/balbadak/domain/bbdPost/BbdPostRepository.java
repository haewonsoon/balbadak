package com.back.balbadak.domain.bbdPost;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BbdPostRepository extends JpaRepository<BbdPost, Long> {

	List<BbdPost> findAllByOrderByCreateDtDesc();

}
