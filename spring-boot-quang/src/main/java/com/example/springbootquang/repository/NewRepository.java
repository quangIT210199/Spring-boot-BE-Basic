package com.example.springbootquang.repository;

import com.example.springbootquang.entity.NewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
