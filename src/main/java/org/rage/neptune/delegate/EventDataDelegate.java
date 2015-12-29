package org.rage.neptune.delegate;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.rage.neptune.dto.EventDTO;
import org.rage.neptune.dto.ModificationResponse;
import org.rage.neptune.entity.Event;
import org.rage.neptune.persistence.EventDataManager;
import org.rage.neptune.utils.DTOHelper;
import org.rage.neptune.utils.EntityDataUtils;

public class EventDataDelegate {

	@Inject
	private EventDataManager eventDataManager;

	public EventDTO getEventById(String id) {
		return DTOHelper.transform(eventDataManager.getEventById(id));
	}

	public ModificationResponse saveEvent(Event event) {
		final ModificationResponse modificationResponse = new ModificationResponse();
		EntityDataUtils.setMissingEventFields(event);
		try {
			eventDataManager.saveEvent(event);
		} catch (Exception ex) {
			modificationResponse.setErrorMessage(ex.getMessage());
			modificationResponse.setValid(Boolean.FALSE);
		}
		return modificationResponse;
	}

	public ModificationResponse deleteEvent(String eventId) {
		final ModificationResponse modificationResponse = new ModificationResponse();
		try {
			eventDataManager.deleteEvent(eventId);
		} catch (Exception ex) {
			modificationResponse.setErrorMessage(ex.getMessage());
			modificationResponse.setValid(Boolean.FALSE);
		}
		return modificationResponse;
	}

	public List<EventDTO> getEventsByEntity(String entityId) {
		final List<Event> events = eventDataManager.getEventsByEntity(entityId);
		final List<EventDTO> eventsDTO = new ArrayList<EventDTO>(events.size());
		for (Event event : events) {
			eventsDTO.add(DTOHelper.transform(event));
		}
		return eventsDTO;
	}
}
