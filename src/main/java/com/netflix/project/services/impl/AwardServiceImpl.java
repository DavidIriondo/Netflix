package com.netflix.project.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.project.entities.Award;
import com.netflix.project.json.AwardRest;
import com.netflix.project.repositories.AwardRepository;
import com.netflix.project.services.AwardService;

@Service
public class AwardServiceImpl implements AwardService{
	
	@Autowired
	private AwardRepository awardRepository;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public AwardRest getAwardById(Long id) {
		
		return modelMapper.map(awardRepository.getOne(id), AwardRest.class);
	}

}
