package pl.uwm.wateradventure.services.events.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.uwm.wateradventure.exceptions.custom_exceptions.EntityNotFoundException;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.dtos.*;
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
        List<Predicate> predicates = new ArrayList<>();

        addTypePredicate(cb, event, predicates, filters.type());
        addCityPredicate(cb, event, predicates, filters.city());
        addCostPredicate(cb, event, predicates, filters.cost());

        addMaxParticipantsNumberPredicate(cb, event, predicates, filters.maxParticipantsNumber());
        if (!filters.adminSearch()) {
            addMaxParticipantsLimitNotCappedPredicate(cb, event, predicates, query);
        }

        query.select(
                cb.construct(
                        EventFilterDTO.class,
                        eventsToSelection(event, query, cb).toArray(new Selection<?>[0])
                )
        );

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        addSortBy(filters.sortBy(), query, cb, event, joinParticipantEvents);

        return em.createQuery(query).getResultList();
    }

    // TODO - move it to participant-events package
    public List<ParticipantEventFilterDTO> getParticipantEventsByFilters(ParticipantEventFiltersDTO filters) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ParticipantEventFilterDTO> query = cb.createQuery(ParticipantEventFilterDTO.class);
        Root<EventEntity> event = query.from(EventEntity.class);
        joinParticipantEvents = event.join("eventParticipants", JoinType.LEFT);
        List<Predicate> predicates = new ArrayList<>();

        addTypePredicate(cb, event, predicates, filters.type());
        addCityPredicate(cb, event, predicates, filters.city());
        addOrdererEmailPredicate(cb, predicates, joinParticipantEvents, filters.ordererEmail());
        addOrdererLastNamePredicate(cb, predicates, joinParticipantEvents, filters.ordererLastName());

        query.select(
                cb.construct(
                    ParticipantEventFilterDTO.class,
                    participantEventsToSelection(event, query, cb).toArray(new Selection<?>[0])
                )
        );

        query.where(predicates.toArray(new Predicate[predicates.size()]));

        addSortBy(filters.sortBy(), query, cb, event, joinParticipantEvents);

        return em.createQuery(query).getResultList();
    }

    private List<Selection<?>> eventsToSelection(Root<EventEntity> root,
                                                 CriteriaQuery<EventFilterDTO> cq,
                                                 CriteriaBuilder cb) {
        return Arrays.asList(
                root.get("id"),
                root.get("type"),
                root.get("city"),
                root.get("cost"),
                addAssignedParticipants(cb, root, cq),
                root.get("maxParticipantsNumber"),
                root.get("date"),
                root.get("duration")
        );
    }

    private List<Selection<?>> participantEventsToSelection(Root<EventEntity> root,
                                           CriteriaQuery<ParticipantEventFilterDTO> cq,
                                           CriteriaBuilder cb) {
        return Arrays.asList(
                root.get("id"),
                root.get("type"),
                root.get("city"),
                root.get("cost"),
                addAssignedParticipants(cb, root, cq),
                root.get("maxParticipantsNumber"),
                root.get("date"),
                root.get("duration"),
                joinParticipantEvents.get("ordererLastName"),
                joinParticipantEvents.get("ordererEmail")
        );
    }

}
