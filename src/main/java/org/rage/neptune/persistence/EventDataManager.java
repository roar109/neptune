package org.rage.neptune.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.rage.neptune.entity.Event;

@Stateless
public class EventDataManager {
	@PersistenceContext
	private EntityManager em;

	public Event getEventById(String id) {
		return em.find(Event.class, id);
	}

	public void saveEvent(Event event) {
		em.persist(event);
	}

	public List<Event> getEventsByEntity(String entityId) {
		final Query query = em.createQuery("from Event as e where e.entityId = :entityId order by creation asc");
		query.setParameter("entityId", entityId);
		return query.getResultList();
	}

	public void deleteEvent(String id) {
		em.remove(getEventById(id));
	}
}
