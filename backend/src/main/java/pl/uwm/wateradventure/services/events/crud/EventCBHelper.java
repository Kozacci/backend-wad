package pl.uwm.wateradventure.services.events.crud;

import jakarta.persistence.criteria.*;
import pl.uwm.wateradventure.models.events.EventCity;
import pl.uwm.wateradventure.models.events.EventEntity;
import pl.uwm.wateradventure.models.events.EventType;
import pl.uwm.wateradventure.models.events.dtos.EventFilteredDTO;
import pl.uwm.wateradventure.models.participant_events.ParticipantEventEntity;

import java.util.List;

/**
 * Class created in the needs of EventReader class
 */
public class EventCBHelper {


    public static void addSortBy(String sort, CriteriaQuery<EventFilteredDTO> query,
                                 CriteriaBuilder cb, Root<EventEntity> event,
                                 Join<EventEntity, ParticipantEventEntity> joinParticipantEvents
                                 ) {
        if (sort == null) { // default sortBy value
            query.orderBy(cb.asc(event.get("date")));
            return;
        }
        if (sort.equals("type") || sort.equals("city")) {
            query.orderBy(cb.asc(event.get(sort)));
            return;
        }
        if (sort.equals("ordererLastName") || sort.equals("ordererEmail")) {
            query.orderBy(cb.asc(joinParticipantEvents.get(sort)));
            return;
        }
    }

    public static void addTypePredicate(CriteriaBuilder cb, Root<EventEntity> event,
                                        List<Predicate> predicates, EventType type) {
        if (type != null) {
            predicates.add(cb.equal(event.get("type"), type));
        }
    }

    public static void addCityPredicate(CriteriaBuilder cb, Root<EventEntity> event,
                                        List<Predicate> predicates, EventCity city) {
        if (city != null) {
            predicates.add(cb.equal(event.get("city"), city));
        }
    }

    public static void addOrdererLastNamePredicate(CriteriaBuilder cb,
                                                   List<Predicate> predicates,
                                                   Join<EventEntity, ParticipantEventEntity> joinParticipantEvents,
                                                   String ordererLastName) {
        if (ordererLastName != null) {
           predicates.add(cb.equal(joinParticipantEvents.get("ordererLastName"), ordererLastName));
        }
    }

    public static void addOrdererEmailPredicate(CriteriaBuilder cb,
                                                List<Predicate> predicates,
                                                Join<EventEntity, ParticipantEventEntity> joinParticipantEvents,
                                                String ordererEmail) {
        if (ordererEmail != null) {
            predicates.add(cb.equal(joinParticipantEvents.get("ordererEmail"), ordererEmail));
        }
    }

}
