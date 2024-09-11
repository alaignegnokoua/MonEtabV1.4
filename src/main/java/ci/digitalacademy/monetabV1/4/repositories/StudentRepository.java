package ci.digitalacademy.MonEtabV14.repositories;

import ci.digitalacademy.MonEtabV14.models.Student;
import ci.digitalacademy.MonEtabV14.models.enumeration.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(String lastName, String matricule, Gender gender);
}
