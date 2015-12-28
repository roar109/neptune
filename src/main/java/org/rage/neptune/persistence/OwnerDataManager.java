package org.rage.neptune.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.rage.neptune.entity.Owner;

@Stateless
public class OwnerDataManager {
	@PersistenceContext
	private EntityManager em;

	public Owner getOwnerById(String id) {
		return em.find(Owner.class, id);
	}

	public void saveOwner(Owner owner) {
		em.persist(owner);
	}
	
	public Owner getOwnerByEmail(String email){
		final Query query = em.createQuery("from Owner as o where o.email = :email");
		query.setParameter("email", email);
		final List<Owner> results = query.getResultList();
		if(results.size() > 0)return results.get(0);
		return null;
	}
}
