package com.back.balbadak.domain.bbdFile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BbdFileRepository extends JpaRepository<BbdFile, Long> {
	
	List<BbdFile> findAllByOrderByCreateDtDesc();

}
