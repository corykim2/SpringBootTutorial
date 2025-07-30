package com.example.SpringBootBoard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//이걸 따로 분리한 이유는 사용자, 게시글, 댓글 모두 이 시간을 가짐. 그래서 이걸 따로 파놓고 상속해서 쓰는 거임

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false) //이 조건으로 업데이트시에는 아래거가 아래 옵션으로 insert 시에는 위에거가 사용되도록 함
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false) //얘가 아래 옵션
    private LocalDateTime updatedTime;
}
