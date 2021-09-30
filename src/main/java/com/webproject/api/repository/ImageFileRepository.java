package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.api.entity.ImageFileData;

public interface ImageFileRepository extends JpaRepository<ImageFileData, String> {

	

}
