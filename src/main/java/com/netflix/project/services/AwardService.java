package com.netflix.project.services;

import com.netflix.project.entities.Award;
import com.netflix.project.json.AwardRest;

public interface AwardService {

	AwardRest getAwardById(Long id);

	//AwardRest postAward(Award award, Long id);
}
