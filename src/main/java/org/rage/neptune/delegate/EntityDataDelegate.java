package org.rage.neptune.delegate;

import java.util.List;

import javax.inject.Inject;

import org.rage.neptune.dto.EntityDTO;
import org.rage.neptune.dto.ModificationResponse;
import org.rage.neptune.entity.Entity;
import org.rage.neptune.persistence.EntityDataManager;
import org.rage.neptune.utils.DTOHelper;
import org.rage.neptune.utils.EntityDataUtils;

public class EntityDataDelegate {
	
	@Inject
	private EntityDataManager entityDataManager;
	
	public EntityDTO getEntityById(String id){
		return DTOHelper.transform(entityDataManager.getEntityById(id));
	}
	
	public List<EntityDTO> getEntitiesByOwner(String ownerId){
		return DTOHelper.transform(entityDataManager.getEntitiesByOwnerId(ownerId));
	}
	
	public ModificationResponse saveEntity(Entity entity){
		final ModificationResponse modificationResponse = new ModificationResponse();
		EntityDataUtils.setMissingEntityFields(entity);
		try {
			entityDataManager.saveEntity(entity);
		} catch (Exception ex) {
			modificationResponse.setErrorMessage(ex.getMessage());
			modificationResponse.setValid(Boolean.FALSE);
		}
		return modificationResponse;
	}
	
	public ModificationResponse updateEntity(Entity entity){
		final ModificationResponse modificationResponse = new ModificationResponse();
		try {
			entityDataManager.updateEntity(entity);
		} catch (Exception ex) {
			modificationResponse.setErrorMessage(ex.getMessage());
			modificationResponse.setValid(Boolean.FALSE);
		}
		return modificationResponse;
	}
	
	public ModificationResponse deleteEntity(String id){
		final ModificationResponse modificationResponse = new ModificationResponse();
		try {
			entityDataManager.deleteEntity(id);
		} catch (Exception ex) {
			modificationResponse.setErrorMessage(ex.getMessage());
			modificationResponse.setValid(Boolean.FALSE);
		}
		return modificationResponse;
	}
}
