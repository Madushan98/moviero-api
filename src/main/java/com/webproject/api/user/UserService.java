package com.webproject.api.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user) throws Exception;
	
	UserDto getUser(String email);
	
	UserDto getUserById(String id);

	UserDto updateUser(UserDto userCopy,String userId);

	List<UserDto> getAllUsers();
}
