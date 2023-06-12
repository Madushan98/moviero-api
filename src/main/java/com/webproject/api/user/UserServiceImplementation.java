package com.webproject.api.user;


import java.util.ArrayList;
import java.util.List;

import com.webproject.api.security.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.webproject.api.exceptions.UserServiceExeception;
import com.webproject.api.repository.RoleRepository;
import com.webproject.api.repository.UserRepository;
import com.webproject.api.shared.Utils;



@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) throws Exception{
		
		UserModel isUserAvailable = userRepository.findByEmail(user.getEmail());

		if(isUserAvailable != null) {
			throw new UserServiceExeception("E-mail is Already Taken");
		}

		UserModel userEntity = new UserModel();
		BeanUtils.copyProperties(user, userEntity);
		
		String userId = utils.generateUserId(30);

		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		

		if(roleRepository.count() == 0){
			Role customer = new Role();
			customer.setName("ROLE_CUSTOMER");

			Role admin = new Role();
			admin.setName("ROLE_ADMIN");
			roleRepository.save(admin);
			roleRepository.save(customer);	
		}
		
		
	
		List<Role> roleList = new ArrayList<Role>();
		
	

		roleList.add(roleRepository.getRoleByName("ROLE_CUSTOMER"));
		roleList.add(roleRepository.getRoleByName("ROLE_ADMIN"));  //only for development to show the admin side when initializing every user gets admin acess
		
		
		
		userEntity.setRoles(roleList);
		
		
		
		UserModel storedUser = userRepository.save(userEntity);

		UserDto returnUser = new UserDto();
		BeanUtils.copyProperties(storedUser, returnUser);

		return returnUser;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserModel userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), getAuthorities(userEntity));
	}

	private static ArrayList<GrantedAuthority> getAuthorities(UserModel user) {

		List<Role> roles = user.getRoles();

		ArrayList<GrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {

			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}

		return authorities;
	}

	@Override
	public UserDto getUser(String email) {
		UserModel userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		UserDto returnUser = new UserDto();
		BeanUtils.copyProperties(userEntity, returnUser);

		return returnUser;
	}

	@Override
	public UserDto getUserById(String id) {
	
		UserModel userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		
		UserDto returnUser = new UserDto();
		
		BeanUtils.copyProperties(userEntity, returnUser);
		
		List<Role> userRoles = userEntity.getRoles();
		
		List<String> roles = new ArrayList<String>();
		
		String roleName ;
		
		for(Role role : userRoles) {
			
			roleName = role.getName();
			
			
			
			
			roles.add(roleName);
		}
	
		
		returnUser.setUserRole(roles);
		
		return returnUser;
	}

	@Override
	public UserDto updateUser(UserDto userCopy, String userId) {
		
		UserModel usermodel = userRepository.findByUserId(userId);
		
		if(usermodel == null) {
			throw new UsernameNotFoundException(userId);
		}
		
		usermodel.setUserName(userCopy.getUserName()); 
		usermodel.setEmail(userCopy.getEmail());
		usermodel.setFirstName(userCopy.getFirstName());
		usermodel.setLastName(userCopy.getLastName());
		
		UserModel updatedDetails = userRepository.save(usermodel);
		
		
		UserDto returnUser = new UserDto();
		
		BeanUtils.copyProperties(updatedDetails, returnUser);
		
		List<Role> userRoles = usermodel.getRoles();
		
		List<String> roleName = new ArrayList<String>();
		
		for(Role role : userRoles) {
			
			roleName.add(role.getName());
			
			
			
		}
		
		
		returnUser.setUserRole(roleName);
		
		return returnUser;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<UserDto> userDtos = new ArrayList<UserDto>();
		
		List<UserModel> users = userRepository.findAll();
		
		
		for (UserModel user : users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			
			
			userDtos.add(userDto );
		}
	 
		
		
		return userDtos;
	}

}
