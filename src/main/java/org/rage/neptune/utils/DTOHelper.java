package org.rage.neptune.utils;

import java.util.ArrayList;
import java.util.List;

import org.rage.neptune.dto.EntityDTO;
import org.rage.neptune.dto.EventDTO;
import org.rage.neptune.dto.OwnerDTO;
import org.rage.neptune.entity.Entity;
import org.rage.neptune.entity.Event;
import org.rage.neptune.entity.Owner;

public class DTOHelper {

	public static EntityDTO transform(Entity entity) {
		if (entity == null)
			return null;
		return new EntityDTO(entity.getId(), entity.getDescription(), entity.getCreation());
	}

	public static List<EntityDTO> transform(List<Entity> entities) {
		final List<EntityDTO> dtoEntities = new ArrayList<EntityDTO>(entities.size());
		for (Entity entity : entities) {
			dtoEntities.add(transform(entity));
		}
		return dtoEntities;
	}

	public static OwnerDTO transform(Owner owner) {
		if (owner == null)
			return null;
		return new OwnerDTO(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getEmail());
	}

	public static EventDTO transform(Event event) {
		if (event == null)
			return null;
		return new EventDTO(event.getId(),event.getTitle(), event.getDescription(), event.getCreation());
	}

}
