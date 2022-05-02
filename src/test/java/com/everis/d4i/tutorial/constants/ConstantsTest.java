package com.everis.d4i.tutorial.constants;

import org.springframework.util.Base64Utils;

public class ConstantsTest {
	
	public static String USER_ROLE = "Basic " + Base64Utils.encodeToString("user:1234".getBytes());
	public static String ADMIN_ROLE = "Basic " + Base64Utils.encodeToString("admin:1234".getBytes());

}
