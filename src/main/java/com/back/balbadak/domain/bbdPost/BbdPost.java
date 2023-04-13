package com.back.balbadak.domain.bbdPost;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class BbdPost {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    @Column(length = 500, nullable = false)
    private String postUserId;
    private String postTitle;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String postContent;
    private Long postLike;

    @Builder
    public BbdPost(Long postId, String postUserId, String postTitle, String postContent, Long postLike) {
        this.postId = postId;
        this.postUserId = postUserId;
        this.postTitle = postTitle;
        this.postContent = postContent;
        this.postLike = postLike;
    }
}
