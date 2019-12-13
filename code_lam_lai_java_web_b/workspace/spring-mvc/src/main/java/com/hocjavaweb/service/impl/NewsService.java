package com.hocjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hocjavaweb.converter.NewsConverter;
import com.hocjavaweb.dto.NewsDTO;
import com.hocjavaweb.entity.CategoryEntity;
import com.hocjavaweb.entity.NewsEntity;
import com.hocjavaweb.repository.ICategoryRepository;
import com.hocjavaweb.repository.INewsRepository;
import com.hocjavaweb.service.INewsService;

@Service
public class NewsService implements INewsService{

	@Autowired
	private INewsRepository newsRepository;
	
	@Autowired
	private NewsConverter newsConverter;
	
	@Autowired
	private ICategoryRepository categoryRopesitory;

	@Override
	public List<NewsDTO> findAll(Pageable pageable) {
		List<NewsEntity> newsEntities = newsRepository.findAll(pageable).getContent();
		List<NewsDTO> newsDTOs = new ArrayList<>();
		for (NewsEntity entity : newsEntities) {
			NewsDTO newsDTO = new NewsDTO();
			newsDTO = newsConverter.toDto(entity);
			newsDTOs.add(newsDTO);
		}
		return newsDTOs;
	}

	@Override
	public int getTotalItem() {
		return (int) newsRepository.count();
	}

	@Override
	public NewsDTO findById(long id) {
		NewsEntity entity = newsRepository.findOne(id);
		return newsConverter.toDto(entity);
	}

//	@Override
//	@Transactional
//	public NewsDTO insert(NewsDTO dto) {
//		CategoryEntity categoryEntity = categoryRopesitory.findOneByCode(dto.getCategoryCode());
//		
//		NewsEntity newsEntity = newsConverter.toEntity(dto);
//		newsEntity.setCategoryEntity(categoryEntity);
//		
//		newsEntity = newsRepository.save(newsEntity);
//		return newsConverter.toDto(newsEntity);
//	}
//
//	@Override
//	@Transactional
//	public NewsDTO update(NewsDTO dto) {
//		CategoryEntity categoryEntity = categoryRopesitory.findOneByCode(dto.getCategoryCode());
//		
//		NewsEntity oldNews = newsRepository.findOne(dto.getId());
//		
//		oldNews.setCategoryEntity(categoryEntity);
//		NewsEntity newsEntity = newsConverter.toEntity(oldNews, dto);
//		
//		newsEntity = newsRepository.save(newsEntity);
//		return newsConverter.toDto(newsEntity);
//	}

	@Override
	@Transactional
	public NewsDTO save(NewsDTO dto) {
		CategoryEntity categoryEntity = categoryRopesitory.findOneByCode(dto.getCategoryCode());
		NewsEntity newsEntity = new NewsEntity();
		if(dto.getId() != null) { // update
			NewsEntity oldNews = newsRepository.findOne(dto.getId());
			oldNews.setCategoryEntity(categoryEntity);
			newsEntity = newsConverter.toEntity(oldNews, dto);
		}else { // add
			newsEntity = newsConverter.toEntity(dto);
			newsEntity.setCategoryEntity(categoryEntity);
		}
		newsEntity = newsRepository.save(newsEntity);
		return newsConverter.toDto(newsEntity);
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (Long id : ids) {
			newsRepository.delete(id);
		}
	}

	@Override
	public List<NewsDTO> findAll() {
		List<NewsDTO> dtos = new ArrayList<>();
		List<NewsEntity> entities = newsRepository.findAll();
		for (NewsEntity newsEntity : entities) {
			NewsDTO dto = new NewsDTO();
			dtos.add(newsConverter.toDto(newsEntity));
		}
		return dtos;
	}

	@Override
	public List<NewsDTO> search(String keyword) {
		List<NewsDTO> dtos = new ArrayList<>();
		List<NewsEntity> entities = newsRepository.findAllByKeyword(keyword);
		for (NewsEntity entity : entities) {
			NewsDTO dto = new NewsDTO();
			dto = newsConverter.toDto(entity);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<NewsDTO> finaAllByCategoryId(Long id) {
		List<NewsDTO> dtos = new ArrayList<>();
		List<NewsEntity> entities = new ArrayList<>();
		try {
			entities = newsRepository.findAllByCategoryId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (NewsEntity entity : entities) {
			NewsDTO dto = new NewsDTO();
			dto = newsConverter.toDto(entity);
			dtos.add(dto);
		}
		return dtos;
	}
}
