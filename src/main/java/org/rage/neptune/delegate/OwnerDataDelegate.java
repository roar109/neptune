package org.rage.neptune.delegate;

import javax.inject.Inject;

import org.rage.neptune.dto.ModificationResponse;
import org.rage.neptune.dto.OwnerDTO;
import org.rage.neptune.entity.Owner;
import org.rage.neptune.persistence.OwnerDataManager;
import org.rage.neptune.utils.DTOHelper;
import org.rage.neptune.utils.EntityDataUtils;

public class OwnerDataDelegate {

	@Inject
	private OwnerDataManager ownerDataManager;

	public OwnerDTO getOwnerById(String id) {
		return DTOHelper.transform(ownerDataManager.getOwnerById(id));
	}

	public ModificationResponse saveOwner(final Owner owner) {
		final ModificationResponse modificationResponse = new ModificationResponse();
		EntityDataUtils.setOwnerId(owner);

		try {
			ownerDataManager.saveOwner(owner);
		} catch (Exception ex) {
			modificationResponse.setErrorMessage(ex.getMessage());
			modificationResponse.setValid(Boolean.FALSE);
		}
		return modificationResponse;
	}

	public OwnerDTO getOwnerByEmail(String email) {
		return DTOHelper.transform(ownerDataManager.getOwnerByEmail(email));
	}
}
