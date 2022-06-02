package com.netflix.project.controllers;

import com.netflix.project.entities.Award;
import com.netflix.project.exceptions.NetflixException;
import com.netflix.project.json.AwardRest;
import com.netflix.project.responses.NetflixResponse;

public interface AwardController {

	
	NetflixResponse<AwardRest>getAwardById(Long id) throws NetflixException;
	
	//NetflixResponse<AwardRest>postAward(Award award) throws NetflixException;
}
