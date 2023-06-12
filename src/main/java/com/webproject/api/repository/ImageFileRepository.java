package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.api.imageFileManager.ImageFileData;

public interface ImageFileRepository extends JpaRepository<ImageFileData, String> {
}
