package org.rage.neptune.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rage.neptune.delegate.OwnerDataDelegate;
import org.rage.neptune.dto.ModificationResponse;
import org.rage.neptune.dto.OwnerDTO;
import org.rage.neptune.entity.Owner;

@Path("owner")
public class OwnerResource {
	
	@Inject
	private OwnerDataDelegate ownerDataDelegate;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public OwnerDTO getOwnerById(@PathParam("id") String id){
		return ownerDataDelegate.getOwnerById(id);
	}
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ModificationResponse saveOwner(Owner owner){
		return ownerDataDelegate.saveOwner(owner);
	}
	
	@GET
	@Path("/search/{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public OwnerDTO getOwnerByEmail(@PathParam("email") String email){
		return ownerDataDelegate.getOwnerByEmail(email);
	}

}
