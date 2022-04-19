package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.AwardRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface AwardController {

	
	NetflixResponse<AwardRest>getAwardById(Long id) throws NetflixException;
	
}
