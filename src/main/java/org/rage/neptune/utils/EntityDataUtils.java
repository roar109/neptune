package org.rage.neptune.utils;

import java.util.Date;
import java.util.UUID;

import org.rage.neptune.entity.Entity;
import org.rage.neptune.entity.Owner;

public class EntityDataUtils {
	
	public static void setMissingEntityFields(final Entity entity){
		setEntityId(entity);
		if(entity.getCreation() == null){
			entity.setCreation(new Date());
		}
	}

	public static void setEntityId(final Entity entity){
		entity.setId(generateNewUUDI());
	}
	
	public static void setOwnerId(final Owner owner){
		owner.setId(generateNewUUDI());
	}
	
	public static String generateNewUUDI(){
		return UUID.randomUUID().toString();
	}
	
}
