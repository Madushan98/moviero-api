package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webproject.api.purches.StreamPurcheses;

@Repository
public interface StreamPurchesRepository extends JpaRepository<StreamPurcheses, Long> {

}

