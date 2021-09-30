package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.webproject.api.entity.VideoFileData;

public interface VideoFileRepository extends JpaRepository<VideoFileData, String> {

}
