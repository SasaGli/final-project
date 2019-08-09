package com.iktpreobuka.zavrsniProjekat.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class Encryption {
	public static String getPassEncoded(String pass) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(pass);
	}
	
	

}
