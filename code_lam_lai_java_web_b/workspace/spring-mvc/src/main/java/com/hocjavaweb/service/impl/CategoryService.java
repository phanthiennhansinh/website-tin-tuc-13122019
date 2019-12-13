package com.hocjavaweb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hocjavaweb.converter.CategoryConverter;
import com.hocjavaweb.dto.CategoryDTO;
import com.hocjavaweb.entity.CategoryEntity;
import com.hocjavaweb.entity.NewsEntity;
import com.hocjavaweb.repository.ICategoryRepository;
import com.hocjavaweb.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Override
	public Map<String,String> findAll() {
		Map<String, String> map = new HashMap<>();
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (CategoryEntity entity : categoryEntities) {
//			CategoryDTO dto = new CategoryDTO();
//			dto = categoryConverter.toDto(entity);
//			categoryDTOs.add(dto);
			map.put(entity.getCode(), entity.getName());
		}
		return map;
	}

	@Override
	public List<CategoryDTO> findALlList() {
		List<CategoryDTO> dtos = new ArrayList<>();
		List<CategoryEntity> entities = categoryRepository.findAll();
		for (CategoryEntity entity : entities) {
			CategoryDTO dto = new CategoryDTO();
			dto = categoryConverter.toDto(entity);
			dtos.add(dto);
		}
		return dtos;
	}

//	@Override
//	@Transactional
//	public CategoryDTO insert(CategoryDTO dto) {
//		CategoryEntity entity = categoryConverter.toEntity(dto);
//		entity = categoryRepository.save(entity);
//		return categoryConverter.toDto(entity);
//	}
//
//	@Override
//	@Transactional
//	public CategoryDTO update(CategoryDTO dto) {
//		CategoryEntity oldCategory = categoryRepository.findOne(dto.getId());
//		CategoryEntity entity = new CategoryEntity();
//		entity.setCode(dto.getCode());
//		entity.setName(dto.getName());
//		entity = categoryRepository.save(entity);
//		return categoryConverter.toDto(entity);
//	}

	@Override
	public CategoryDTO save(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		
		if(dto.getId() != null) {
			// update
			CategoryEntity oldCategory = categoryRepository.findOne(dto.getId()); 
			entity = categoryConverter.toEntity(oldCategory, dto);
		}else {
			//add
			entity = categoryConverter.toEntity(dto);
		}
		entity = categoryRepository.save(entity);
		return categoryConverter.toDto(entity);
	}

	@Override
	public CategoryDTO findOneById(Long id) {
		CategoryEntity entity = categoryRepository.findOne(id);
		return categoryConverter.toDto(entity);
	}

	@Override
	public void delete(Long[] ids) {
		for (Long id : ids) {
			categoryRepository.delete(id);
		}
	}
	@Override
	public List<CategoryDTO> search(String keyword) {
		List<CategoryDTO> dtos = new ArrayList<>();
		List<CategoryEntity> entities = categoryRepository.findAllOrderByNameDesc(keyword);
		for (CategoryEntity entity : entities) {
			CategoryDTO dto = new CategoryDTO();
			dto = categoryConverter.toDto(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public CategoryDTO findOneByCode(String code) {
		CategoryEntity entity = categoryRepository.findOneByCode(code);
		return categoryConverter.toDto(entity);
	}
	
}
