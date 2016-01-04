package org.rage.neptune.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rage.neptune.delegate.EventDataDelegate;
import org.rage.neptune.dto.EventDTO;
import org.rage.neptune.dto.ModificationResponse;
import org.rage.neptune.entity.Event;

@Path("event")
public class EventResource {
	@Inject
	private EventDataDelegate eventDataDelegate;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EventDTO getEventById(@PathParam("id") String id) {
		return eventDataDelegate.getEventById(id);
	}

	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ModificationResponse saveEntity(Event event) {
		return eventDataDelegate.saveEvent(event);
	}

	@GET
	@Path("/entity/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventDTO> getEntitiesByOwner(@PathParam("id") String entityId) {
		return eventDataDelegate.getEventsByEntity(entityId);
	}
	
	@POST
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ModificationResponse deleteEvent(@PathParam("id") String id){
		return eventDataDelegate.deleteEvent(id);
	}
}
