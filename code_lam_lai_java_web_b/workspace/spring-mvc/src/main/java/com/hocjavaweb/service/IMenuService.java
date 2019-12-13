package com.hocjavaweb.service;

import java.util.List;

import com.hocjavaweb.dto.MenuDTO;

public interface IMenuService {
	List<MenuDTO> findAll();
}
