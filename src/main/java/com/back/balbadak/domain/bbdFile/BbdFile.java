package com.back.balbadak.domain.bbdFile;

import com.back.balbadak.domain.bbdPost.BbdPost;
import com.back.balbadak.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = { "fileId", "fileName", "filePath" })
public class BbdFile extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId;
	@Column(length = 500, nullable = false)
	private String fileOriginName;
	@Column(length = 500, nullable = false)
	private String fileName;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String filePath;
	private String fileExtsn;
	private Long fileSize;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	@JsonIgnore
	private BbdPost bbdPost;

	@Builder
	public BbdFile(Long fileId,String fileOriginName,String fileName, String filePath, String fileExtsn, Long fileSize, 
			BbdPost bbdPost) {
		this.fileId = fileId;
		this.fileOriginName = fileOriginName;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileExtsn = fileExtsn;
		this.fileSize = fileSize;
		this.bbdPost = bbdPost;
	}
	
	public void updatePostId(BbdPost bbdPost) {
		this.bbdPost = bbdPost;
	}
}
