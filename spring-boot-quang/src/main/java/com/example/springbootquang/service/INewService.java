package com.example.springbootquang.service;

import com.example.springbootquang.dto.NewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INewService {
    NewDTO save(NewDTO newDTO);

    void delete(long[] ids);

    List<NewDTO> findAll(Pageable pageable);

    NewDTO findById(Long id);

    NewDTO deleteOne(long id);

    int totalItem();
}

