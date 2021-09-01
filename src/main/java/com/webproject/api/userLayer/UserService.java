package com.webproject.api.userLayer;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user);
	
	UserDto getUser(String email);
	
	UserDto getUserById(String id);

	UserDto updateUser(UserDto userCopy,String userId);
}
