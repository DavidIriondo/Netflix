package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.entities.Award;
import com.everis.d4i.tutorial.json.AwardRest;

public interface AwardService {

	AwardRest getAwardById(Long id);

	//AwardRest postAward(Award award, Long id);
}
