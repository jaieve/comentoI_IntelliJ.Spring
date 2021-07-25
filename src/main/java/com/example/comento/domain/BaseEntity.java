package com.example.comento.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass   // 모든 엔터티가 상속받게 해주는 어노테이션
public abstract class BaseEntity {

    //insertable : 엔티티 저장시 선언된 필드도 같이 저장. false로 설정시 데이터베이스에 저장하지 않음.
    //updateable : 엔티티 수정시 선언된 필드도 같이 저장. false로 설정시 데이터베이스에 저장하지 않음.
    @Column(name="created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updateAt;

}
