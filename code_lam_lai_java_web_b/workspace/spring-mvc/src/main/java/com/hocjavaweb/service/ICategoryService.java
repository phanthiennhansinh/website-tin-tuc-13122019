package com.hocjavaweb.service;

import java.util.List;
import java.util.Map;

import com.hocjavaweb.dto.CategoryDTO;
import com.hocjavaweb.entity.CategoryEntity;

public interface ICategoryService {
	//List<CategoryDTO> findAllL();
	Map<String,String> findAll(); // output thành map để sử dụng spring form
	List<CategoryDTO> findALlList();

//	CategoryDTO insert(CategoryDTO dto);
//	CategoryDTO update(CategoryDTO dto);
	
	CategoryDTO save(CategoryDTO dto);
	
	CategoryDTO findOneById(Long id);
	
	void delete(Long[] ids);
	
	List<CategoryDTO> search(String keyword);
	
	CategoryDTO findOneByCode(String code);
}
