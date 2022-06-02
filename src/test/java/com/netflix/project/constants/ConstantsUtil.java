package com.netflix.project.constants;

import org.springframework.util.Base64Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConstantsUtil {
	
	public static String USER_ROLE = "Basic " + Base64Utils.encodeToString("user:1234".getBytes());
	public static String ADMIN_ROLE = "Basic " + Base64Utils.encodeToString("admin:1234".getBytes());

	public static String toJSON(Object objc) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(objc);
	}
}
