package com.hocjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hocjavaweb.constant.SystemConstant;
import com.hocjavaweb.dto.MyUser;
import com.hocjavaweb.entity.RoleEntity;
import com.hocjavaweb.entity.UserEntity;
import com.hocjavaweb.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username,SystemConstant.ACTIVE_STATUS);
		if(userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity roleEntity : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(roleEntity.getCode()));
		}
		MyUser myUser = new  MyUser(userEntity.getUserName(),userEntity.getPassword(),true, true, true, true, authorities);
		
		myUser.setFullName(userEntity.getFullName());		

		return myUser;
	}
	
	

}
