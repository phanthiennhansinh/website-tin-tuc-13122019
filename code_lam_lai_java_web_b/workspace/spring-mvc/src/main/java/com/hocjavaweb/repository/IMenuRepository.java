package com.hocjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hocjavaweb.entity.MenuEntity;

public interface IMenuRepository extends JpaRepository<MenuEntity, Long>{

}
