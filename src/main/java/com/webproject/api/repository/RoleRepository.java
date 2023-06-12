package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webproject.api.security.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
   Role	getRoleByName(String name);
}


