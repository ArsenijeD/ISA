package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Ad;

public interface AdRepository extends JpaRepository<Ad, Long>{

}
