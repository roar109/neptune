package org.rage.neptune.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.rage.neptune.delegate.EntityDataDelegate;
import org.rage.neptune.dto.EntityDTO;
import org.rage.neptune.dto.ModificationResponse;
import org.rage.neptune.entity.Entity;

@Path("entity")
public class EntityResource {
	
	@Inject
	private EntityDataDelegate entityDataDelegate;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EntityDTO getEntityById(@PathParam("id") String id){
		return entityDataDelegate.getEntityById(id);
	}
	
	@GET
	@Path("/owner/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntityDTO> getEntitiesByOwner(@PathParam("id") String ownerId, 
				@QueryParam("start") Integer startPosition, 
				@QueryParam("end") Integer endPosition){
		//TODO fix the pagination
		return entityDataDelegate.getEntitiesByOwner(ownerId);
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ModificationResponse saveEntity(Entity entity){
		return entityDataDelegate.saveEntity(entity);
	}
	
	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ModificationResponse updateEntity(Entity entity){
		return entityDataDelegate.updateEntity(entity);
	}
	
	@POST
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ModificationResponse deleteEntity(@PathParam("id") String id){
		return entityDataDelegate.deleteEntity(id);
	}
	
}
