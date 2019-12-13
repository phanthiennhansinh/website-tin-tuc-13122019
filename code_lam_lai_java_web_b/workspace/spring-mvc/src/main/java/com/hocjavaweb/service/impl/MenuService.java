package com.hocjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hocjavaweb.converter.MenuConverter;
import com.hocjavaweb.dto.MenuDTO;
import com.hocjavaweb.entity.MenuEntity;
import com.hocjavaweb.repository.IMenuRepository;
import com.hocjavaweb.service.IMenuService;

@Service
public class MenuService implements IMenuService{

	@Autowired
	private IMenuRepository menuRepository;
	
	@Autowired
	private MenuConverter menuConverter;
	
	@Override
	public List<MenuDTO> findAll() {
		List<MenuEntity> entities = menuRepository.findAll();
		List<MenuDTO> dtos = new ArrayList<>();
		for (MenuEntity entity : entities) {
			MenuDTO dto = menuConverter.toDto(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	
}
