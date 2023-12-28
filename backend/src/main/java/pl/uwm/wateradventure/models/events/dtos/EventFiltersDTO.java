package pl.uwm.wateradventure.models.events.dtos;

import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventType;

public record EventFiltersDTO(EventType type,
                              EventCity city,
                              Double cost,
                              Integer maxParticipantsNumber,
                              String sortBy,
                              Boolean adminSearch) {

}