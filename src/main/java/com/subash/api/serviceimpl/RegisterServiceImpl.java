package com.subash.api.serviceimpl;

import java.time.Instant;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.subash.api.model.Register;
import com.subash.api.model.Token;
import com.subash.api.ownrepo.RegisterOwnRepo;
import com.subash.api.service.RegisterService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Service
public class RegisterServiceImpl implements RegisterService {
	private static final String SECRET_KEY = "SUBASH";

	public RegisterServiceImpl(RegisterOwnRepo ownrepo) {
		super();
		this.ownrepo = ownrepo;
	}

	RegisterOwnRepo ownrepo;

	@Override
	public String addRegister(Register register) {
		if(register != null) {
			ownrepo.save(register);
			return "AddRegister Success";
		} else {
			return "AddRegister Fail";
		}
	}

	@Override
	public boolean checkCredentials(String email, String password) {
		Register register = ownrepo.findByEmail(email);
		return register != null && register.getPassword().equals(password);
	}

	@Override
	public void addToken(Token token) {
		ownrepo.saveToken(token);
		
	}

	@Override
	 public boolean isTokenValid(String randomValue) {
            String tokenString = ownrepo.findByRandomValue(randomValue);
            if (tokenString != null) {
               
                Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(tokenString)
                    .getBody();
                
                Instant now = Instant.now();
                Date expiryDate = claims.getExpiration();
                
                return !now.isAfter(expiryDate.toInstant());
            }
        
        
        return false;
    }

	@Override
	public void deleteToken(String randomvalue) {
		ownrepo.deleteTokenIfExpiried(randomvalue);
			
		
	}

}
