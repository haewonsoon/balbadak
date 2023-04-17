package com.back.balbadak.domain.bbdPost;

import java.util.ArrayList;
import java.util.List;

import com.back.balbadak.domain.bbdFile.BbdFile;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = { "postId", "postUserId", "postContent" })
public class BbdPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private String postUserId;
	private String postTitle;
	private String postContent;
	private Long postLike;

	@OneToMany(mappedBy = "bbdPost")
	private List<BbdFile> files = new ArrayList<>();

	@Builder
	public BbdPost(Long postId, String postUserId, String postTitle, String postContent, Long postLike,
			List<BbdFile> files) {
		this.postId = postId;
		this.postUserId = postUserId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postLike = postLike;

		if (files != null) {
			this.files = files;
		}
	}
	
	public void updateFile(BbdFile bbdFile) {
		this.files.add(bbdFile);
		bbdFile.updatePostId(this);
	}
}
