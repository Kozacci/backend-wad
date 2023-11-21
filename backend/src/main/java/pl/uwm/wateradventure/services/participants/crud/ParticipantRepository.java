package pl.uwm.wateradventure.services.participants.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.participants.ParticipantEntity;

@Repository
interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {
}
