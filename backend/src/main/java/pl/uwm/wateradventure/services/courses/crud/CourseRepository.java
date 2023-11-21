package pl.uwm.wateradventure.services.courses.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uwm.wateradventure.models.courses.CourseEntity;

@Repository
interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
