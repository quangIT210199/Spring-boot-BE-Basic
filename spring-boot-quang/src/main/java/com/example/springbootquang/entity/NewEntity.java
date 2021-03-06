package com.example.springbootquang.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "new")
public class NewEntity extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "shortdescription")
    private String shortDescription;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
