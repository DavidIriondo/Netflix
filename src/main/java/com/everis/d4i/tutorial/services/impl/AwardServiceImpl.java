package com.everis.d4i.tutorial.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.repositories.AwardRepository;
import com.everis.d4i.tutorial.services.AwardService;

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
