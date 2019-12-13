package com.hocjavaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hocjavaweb.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity,Long>{
	UserEntity findOneByUserNameAndStatus(String username,int status);
}
