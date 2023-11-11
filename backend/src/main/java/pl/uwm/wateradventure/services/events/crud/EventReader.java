package pl.uwm.wateradventure.services.events.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.dtos.EventEntityDTO;
import pl.uwm.wateradventure.models.events.dtos.EventFilterDTO;
import pl.uwm.wateradventure.models.events.dtos.EventFiltersDTO;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;
import pl.uwm.wateradventure.services.global.PageReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static pl.uwm.wateradventure.services.events.crud.EventCBHelper.*;

@Component
@RequiredArgsConstructor
class EventReader extends PageReader<EventEntity> {

    private final EventRepository repository;
    private final EntityManager em;
    private static Join<EventEntity, ParticipantEventEntity> joinParticipantEvents;

    public EventEntity getEventById(Long eventId) {
        return repository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("event", "Event with id: " + eventId + " does not exist!"));
    }

    public Page<EventEntityDTO> getAllEventsPageable() {
        return super
                .getAllSortedPageable(repository, "date", false)
                .map(EventEntity::toDTO);
    }

    public List<EventFilterDTO> getEventsByFilters(EventFiltersDTO filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EventFilterDTO> query = cb.createQuery(EventFilterDTO.class);
        Root<EventEntity> event = query.from(EventEntity.class);
        joinParticipantEvents = event.join("participantEvents");
        List<Predicate> predicates = new ArrayList<>();

        addTypePredicate(cb, event, predicates, filters.type());
        addCityPredicate(cb, event, predicates, filters.city());
        addOrdererEmailPredicate(cb, predicates, joinParticipantEvents, filters.ordererEmail());
        addOrdererLastNamePredicate(cb, predicates, joinParticipantEvents, filters.ordererLastName());

        query.select(
                cb.construct(
                    EventFilterDTO.class,
                    toSelection(event).toArray(new Selection<?>[0])
                )
        );

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        addSortBy(filters.sortBy(), query, cb, event, joinParticipantEvents);

        return em.createQuery(query).getResultList();
    }

    private List<Selection<?>> toSelection(Root<EventEntity> root) {
        return Arrays.asList(
                root.get("id"),
                root.get("type"),
                root.get("city"),
                root.get("maxParticipantsNumber"),
                root.get("duration"),
                joinParticipantEvents.get("ordererLastName"),
                joinParticipantEvents.get("ordererEmail")
        );
    }

}
