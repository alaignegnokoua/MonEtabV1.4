package ci.digitalacademy.MonEtabV14.repositories;

import ci.digitalacademy.MonEtabV14.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
