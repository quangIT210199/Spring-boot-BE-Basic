package com.example.springbootquang.service.impl;

import com.example.springbootquang.converter.NewConverter;
import com.example.springbootquang.dto.NewDTO;

import com.example.springbootquang.entity.CategoryEntity;
import com.example.springbootquang.entity.NewEntity;
import com.example.springbootquang.repository.CategoryRepository;
import com.example.springbootquang.repository.NewRepository;
import com.example.springbootquang.service.INewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Service là nơi xử lí logic và DTO các Object trước khi chuyển đến Controller
@Service
public class NewService implements INewService {
//Tôi đánh dấu thuộc tính newRepository của NewService bởi Annotation @Autowired.
// Điều này nói với Spring Boot hãy tự inject (tiêm) một instance của newRepository vào thuộc tính này khi khởi tạo NewService.
    @Autowired
    private NewRepository newRepository;
    //Inversion of Control: tiêm sự phụ thuộc và quản lý
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NewConverter newConverter;

    @Override
    public NewDTO save(NewDTO newDTO) {
        NewEntity newEntity = new NewEntity();
        //2 TH tạo và cập nhật
        if(newDTO.getId() != null){//TH cập nhật
            Optional<NewEntity> oldNewEntity = newRepository.findById(newDTO.getId());
            newEntity = newConverter.toEntity(newDTO, oldNewEntity.get());
            System.out.println("Sửa đi chứ?");
        }
        else {//TH tạo
            System.out.println("vc");
            newEntity = newConverter.toEntity(newDTO);
        }
        //Xét vào khóa ngoại
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(newDTO.getCategoryCode());

        newEntity.setCategory(categoryEntity);
        newEntity = newRepository.save(newEntity);

        return newConverter.toDTO(newEntity);
    }

    @Override
    public void delete(long[] ids) {
        for(long item: ids) {
            newRepository.deleteById(item);
        }
    }

    @Override
    public NewDTO findById(Long id) {
        NewDTO newDTO = newConverter.toDTO(newRepository.findById(id).orElse(null));
        return newDTO;
    }

    @Override
    public NewDTO deleteOne(long id) {
        NewEntity newEntity = newRepository.findById(id).orElse(null);
        if (newEntity != null){
            newRepository.delete(newEntity);

            return newConverter.toDTO(newEntity);
        }
        else return null;
    }

    @Override
    public List<NewDTO> findAll(Pageable pageable) {
        List<NewDTO> results = new ArrayList<>();
        List<NewEntity> entities = newRepository.findAll(pageable).getContent();
        for (NewEntity item: entities) {
            NewDTO newDTO = newConverter.toDTO(item);
            results.add(newDTO);
        }

        return results;
    }

    @Override
    public int totalItem() {
        return (int) newRepository.count();
    }
}
