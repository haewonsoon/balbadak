package com.back.balbadak.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Column(name = "CREATE_DT", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createDt;

    @Column(name = "UPDATE_DT")
    @LastModifiedDate
    private LocalDateTime updateDt;
}
