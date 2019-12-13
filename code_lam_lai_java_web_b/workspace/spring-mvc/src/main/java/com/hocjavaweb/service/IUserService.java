package com.hocjavaweb.service;

import java.util.List;

import com.hocjavaweb.dto.UserDTO;

public interface IUserService {
	List<UserDTO> findAll();

}
