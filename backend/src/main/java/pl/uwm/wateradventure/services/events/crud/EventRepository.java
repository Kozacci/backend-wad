package pl.uwm.wateradventure.services.events.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.events.EventEntity;

@Repository
interface EventRepository extends JpaRepository<EventEntity, Long> {
}
