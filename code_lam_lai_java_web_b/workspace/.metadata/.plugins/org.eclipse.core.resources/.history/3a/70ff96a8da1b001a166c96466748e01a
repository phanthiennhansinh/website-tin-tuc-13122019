package com.hocjavaweb.converter;

import org.springframework.stereotype.Component;

import com.hocjavaweb.dto.UserDTO;
import com.hocjavaweb.entity.UserEntity;

@Component
public class UserConverter {
	
	public UserDTO toDTO(UserEntity entity) {
		UserDTO dto = new UserDTO();
		dto.setFullName(entity.getFullName());
		dto.setUserName(entity.getUserName());
		dto.setPassword(entity.getPassword());
		dto.setStatus(entity.getStatus());
		return dto;
	}
	
	public UserEntity toEntity(UserDTO dto) {
		UserEntity entity = new UserEntity();
		entity.setFullName(dto.getFullName());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		entity.setStatus(dto.getStatus());
		return entity;
	}

}
