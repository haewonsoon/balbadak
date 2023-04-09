package com.back.balbadak.domain.bbdFile;

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
public class BbdFile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    @Column(length = 500, nullable = false)
    private String fileName;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String filePath;
    private String fileExtsn;
    private Long fileSize;

    @Builder
    public BbdFile(Long fileId, String fileName, String filePath, String fileExtsn, Long fileSize) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileExtsn = fileExtsn;
        this.fileSize = fileSize;
    }
}
