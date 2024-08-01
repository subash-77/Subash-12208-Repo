package com.subash.api.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.subash.api.model.Register;
import com.subash.api.model.Token;
import com.subash.api.service.RegisterService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/register")
@CrossOrigin("http://localhost:3000")

public class RegisterController {
	
	
	public RegisterController(RegisterService service) {
		super();
		this.service = service;
	}


	RegisterService service;
	
    private static final String SECRET_KEY = "SUBASH";

    private static final long EXPIRATION_TIME = 30000; 
	
	
	@PostMapping
	public String insertRegister(@RequestBody Register register) {
		String msg = "";

		try {
			service.addRegister(register);
			msg += "addSuccess";

		} catch (Exception e) {
			msg += "addFailure";
		}
		return msg;

	}
	
	 @PostMapping("/checkCredentials")
	    public String checkCredentials(@RequestBody Register register) {
	        boolean isValid = service.checkCredentials(register.getEmail(), register.getPassword());
	        if (isValid) {

	            String token = generateToken(register.getEmail());
	            return "validCredentials:" + token;
	        } else {
	            return "invalidCredentials";
	        }
	    }
	 @PostMapping("/addToken")
		public String insertToken(@RequestBody Token token) {
			String msg = "";

			try {
				service.addToken(token);
				msg += "tokenAddSuccess";

			} catch (Exception e) {
				msg += "tokenaddFailure";
			}
			return msg;

		}
	 @GetMapping("/gettokendetails")
	    public String getTokenDetails(@RequestParam String randomvalue) {
		 
	        try {
	            boolean isTokenValid = service.isTokenValid(randomvalue);
	            if (isTokenValid) {
	            	System.out.print("Token is valid and not expired");
	            	
	                return "Token is valid and not expired";
	            } else {
	            	service.deleteToken(randomvalue);
	                return "Token is expired or not found";
	            }
	        } catch (Exception e) {
	            service.deleteToken(randomvalue);
	        	return "Token Deleted and Session Expired Good Bye";
	        }
	    }
	 
	    private String generateToken(String username) {
	        Instant now = Instant.now();
	        Instant expiryTime = now.plus(EXPIRATION_TIME, ChronoUnit.MILLIS);

	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(Date.from(now))
	                .setExpiration(Date.from(expiryTime))
	                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
	                .compact();
	    }
}
