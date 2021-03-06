package com.example.springbootquang.converter;

import com.example.springbootquang.dto.NewDTO;
import com.example.springbootquang.entity.NewEntity;
import org.springframework.stereotype.Component;
//Tạo ra 1 bean để quản lí DI => tạo bằng @Component
@Component
public class NewConverter {

    public NewEntity toEntity(NewDTO dto){
        NewEntity entity = new NewEntity();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setShortDescription(dto.getShortDescription());
        entity.setThumbnail(dto.getThumbnail());
        return entity;
    }

    public NewDTO toDTO(NewEntity entity) {
        NewDTO dto = new NewDTO();
        if(dto == null)
            return null;

        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setShortDescription(entity.getShortDescription());
        dto.setThumbnail(entity.getThumbnail());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setModifiedDate(entity.getModifiedDate());
        dto.setModifiedBy(entity.getModifiedBy());
        return dto;
    }

    //Sử dụng cho quan hệ n-n
    public NewEntity toEntity(NewDTO dto, NewEntity entity) {
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setShortDescription(dto.getShortDescription());
        entity.setThumbnail(dto.getThumbnail());
        return entity;
    }
}
