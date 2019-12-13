package com.hocjavaweb.converter;

import org.springframework.stereotype.Component;

import com.hocjavaweb.dto.MenuDTO;
import com.hocjavaweb.entity.MenuEntity;

@Component
public class MenuConverter {

	public MenuDTO toDto(MenuEntity entity) {
		MenuDTO dto = new MenuDTO();
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		return dto;
	}
	
	public MenuEntity toEntity(MenuDTO dto) {
		MenuEntity entity = new MenuEntity();
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		return entity;
	}
}
