package ci.digitalacademy.MonEtabV14.repositories;

import ci.digitalacademy.MonEtabV14.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}
