package com.webproject.api.controllers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.entity.*;
import com.webproject.api.repository.*;
import com.webproject.api.userLayer.*;





@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService ;
	
	@Autowired
	UserRepository userRepository ;
	
	@Autowired
	MovieRepository movieRepository ;
	
	@Autowired
	CartRepository cartRepository ;
	
@GetMapping	
public String GetUser() {
	
	return"users" ;
}



@PostMapping
public UserDetailResponse CreateUser(@RequestBody UserDetailRequestModel userDetail) {
	
	UserDetailResponse userResponse = new UserDetailResponse();	
	
	UserDto userDto = new UserDto() ; 
	BeanUtils.copyProperties(userDetail, userDto);
	
	if(userRepository.findByEmail(userDetail.getEmail()) != null) {
		
	}
	
	UserDto returnUser = userService.createUser(userDto);
	
	
	
	BeanUtils.copyProperties(returnUser, userResponse);

	return userResponse ;
}


@PutMapping(path = "/{userId}/cart/{movieId}")
 public Cart AddToCart(@PathVariable (value = "userId") String userId,@PathVariable (value = "movieId") String movieId ) {
	
	Movie movie = movieRepository.findByMovieId(movieId) ;
	
	UserModel user = userRepository.findByUserId(userId);
	
	Cart cart = cartRepository.getById(user.getCardId());
	
	cart.addToCart(movie);
	
	
	
	
	
	
	return cartRepository.save(cart);
	
}

@GetMapping(path="/{id}")
public UserDetailResponse  GetUser(@PathVariable String id) {
	
	UserDetailResponse returnUser = new UserDetailResponse();
	UserDto user = userService.getUserById(id) ;
	
	
	BeanUtils.copyProperties( user,returnUser);
	
	return returnUser;
	
}

@PutMapping(path="/{id}")
public UserDetailResponse UserDetailUpdate(@PathVariable  String id, @RequestBody UserDetailRequestModel userDetail) {
	
	UserDetailResponse returnUser = new UserDetailResponse();
	UserDto userCopy = new UserDto() ;
	
	BeanUtils.copyProperties( userDetail,userCopy );
	
	UserDto updateUser = userService.updateUser(userCopy,id); 
	
	BeanUtils.copyProperties( updateUser,returnUser );
	
	
	return returnUser;
	
}


}
