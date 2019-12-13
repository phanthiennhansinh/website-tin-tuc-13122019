package com.hocjavaweb.converter;

import org.springframework.stereotype.Component;

import com.hocjavaweb.dto.NewsDTO;
import com.hocjavaweb.entity.NewsEntity;

@Component
public class NewsConverter {
	public NewsDTO toDto(NewsEntity entity) {
		NewsDTO dto = new NewsDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setThumbnail(entity.getThumbnail());
		dto.setShortDescription(entity.getShortDescription());
		dto.setContent(entity.getContent());
		dto.setCategoryCode(entity.getCategoryEntity().getCode());
		dto.setBase64(entity.getBase64());
		dto.setName(entity.getName());
		return dto; 
	}

	public NewsEntity toEntity(NewsDTO dto) {
		NewsEntity entity = new NewsEntity();
		entity.setTitle(dto.getTitle());
		entity.setThumbnail(dto.getThumbnail());
		entity.setShortDescription(dto.getShortDescription());
		entity.setContent(dto.getContent());
		entity.setBase64(dto.getBase64());
		entity.setName(dto.getName());
		return entity;
	}
	public NewsEntity toEntity(NewsEntity oldNews, NewsDTO dto) {
		oldNews.setTitle(dto.getTitle());
		oldNews.setThumbnail(dto.getThumbnail());
		oldNews.setShortDescription(dto.getShortDescription());
		oldNews.setContent(dto.getContent());
		oldNews.setBase64(dto.getBase64());
		oldNews.setName(dto.getName());
		return oldNews;
	}
}
