package ci.digitalacademy.MonEtabV14.repositories;

import ci.digitalacademy.MonEtabV14.models.StudentCards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCardsRepository extends JpaRepository<StudentCards, Long> {
}
