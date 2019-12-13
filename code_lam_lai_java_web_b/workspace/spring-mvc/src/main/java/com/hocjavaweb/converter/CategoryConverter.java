package com.hocjavaweb.converter;

import org.springframework.stereotype.Component;

import com.hocjavaweb.dto.CategoryDTO;
import com.hocjavaweb.entity.CategoryEntity;

@Component
public class CategoryConverter {

	public CategoryDTO toDto(CategoryEntity entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setName(entity.getName());
		return dto;
	}
	
	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		entity.setCode(dto.getCode());
		entity.setName(dto.getName());
		return entity;
	}
	
	public CategoryEntity toEntity(CategoryEntity oldEntity,CategoryDTO dto) {
		oldEntity.setCode(dto.getCode());
		oldEntity.setName(dto.getName());
		return oldEntity;
	}
	
}
