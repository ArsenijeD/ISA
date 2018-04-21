package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Chair;
import com.example.repository.ChairRepository;
import com.example.repository.SeatRepository;

@Service
public class ChairServiceImpl implements ChairService{

	
	@Autowired
	private ChairRepository chairRepository;
	
	@Override
	public List<Chair> getAll() {
		
		return chairRepository.findAll();
	}

	@Override
	public Chair getChairByID(Long id) {
		
		return chairRepository.findById(id);
	}

	@Override
	public boolean registerChair(Chair c) {
		
		chairRepository.save(c);
		return true;
	}

}
