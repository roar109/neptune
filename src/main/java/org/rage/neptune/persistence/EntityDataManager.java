package org.rage.neptune.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.rage.neptune.entity.Entity;

@Stateless
public class EntityDataManager {

	@PersistenceContext
	private EntityManager em;

	public Entity getEntityById(String id) {
		return em.find(Entity.class, id);
	}

	public void saveEntity(Entity entity){
		em.persist(entity);
	}
	
	public void updateEntity(Entity entity){
		em.merge(entity);
	}
	
	public void deleteEntity(String id){
		em.remove(getEntityById(id));
	}
	
	public List<Entity> getEntitiesByOwnerId(String ownerId){
		Query query = em.createQuery("from Entity as e where e.creator = :creator");
		query.setParameter("creator", ownerId);
		return query.getResultList();
	}
}
