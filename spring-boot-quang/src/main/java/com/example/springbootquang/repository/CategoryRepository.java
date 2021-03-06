package com.example.springbootquang.repository;

import com.example.springbootquang.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findOneByCode(String code);//lấy single còn k có One là lấy list
}
