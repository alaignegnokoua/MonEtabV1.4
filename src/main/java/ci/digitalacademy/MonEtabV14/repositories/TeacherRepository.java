package ci.digitalacademy.MonEtabV14.repositories;

import ci.digitalacademy.MonEtabV14.models.Teacher;
import ci.digitalacademy.MonEtabV14.models.enumeration.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByLastNameIgnoreCaseOrSpecialtyAndGender(String lastName, String specialty, Gender gender);
}
