package com.hocjavaweb.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.hocjavaweb.dto.NewsDTO;

public interface INewsService {
	List<NewsDTO> findAll(Pageable pageable);
	List<NewsDTO> findAll();
	int getTotalItem();
	NewsDTO findById(long id);
//	NewsDTO insert(NewsDTO dto);
//	NewsDTO update(NewsDTO dto);
	NewsDTO save(NewsDTO dto);
	void delete(long[] ids);
	List<NewsDTO> search(String keyword);
	List<NewsDTO> finaAllByCategoryId(Long id);
}
