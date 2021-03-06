package com.example.springbootquang.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity extends BaseEntity{

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category")// ánh xạ vào Table là 1 bảng new
    private List<NewEntity> news = new ArrayList<>();
}
