package com.example.springbootquang.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
//phải có annonation này thì mới cho các lớp con kế thừa dc
@Data
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Của người đang đăng nhập
    @CreatedBy
    private String createdBy;
    //Giusp lấy thời gian của hệ thống
    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private String modifiedBy;

    @LastModifiedDate
    private Date modifiedDate;

}
