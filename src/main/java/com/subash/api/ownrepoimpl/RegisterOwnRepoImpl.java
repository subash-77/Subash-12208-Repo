package com.subash.api.ownrepoimpl;

import org.springframework.stereotype.Repository;

import com.subash.api.model.Register;
import com.subash.api.model.Token;
import com.subash.api.ownrepo.RegisterOwnRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class RegisterOwnRepoImpl implements RegisterOwnRepo {

	public RegisterOwnRepoImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	EntityManager entityManager;

	@Override
	public void save(Register register) {
		entityManager.persist(register);
	}

	@Override
	public Register findByEmail(String email) {
		String hql = "FROM Register WHERE email = :email";
		TypedQuery<Register> query = entityManager.createQuery(hql, Register.class);
		query.setParameter("email", email);
		return query.getSingleResult();
	}

	@Override
	public void saveToken(Token token) {
		entityManager.persist(token);

	}

	@Override
	public String findByRandomValue(String randomValue) {
		String hql = "SELECT t.token FROM Token t WHERE t.randomValue = :randomValue";
		TypedQuery<String> query = entityManager.createQuery(hql, String.class);
		query.setParameter("randomValue", randomValue);
		try {
			
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteTokenIfExpiried(String randomValue) {
		
		String hql = "DELETE FROM Token t WHERE t.randomValue = :randomValue";
	    Query query = entityManager.createQuery(hql);
	    query.setParameter("randomValue", randomValue);

	    try {
	       query.executeUpdate();
	    } catch (Exception e) { 
	    }
	}

}
