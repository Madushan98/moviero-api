package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webproject.api.user.UserModel;




@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	UserModel findByEmail(String Email);
	UserModel findByUserId(String userId);
}
