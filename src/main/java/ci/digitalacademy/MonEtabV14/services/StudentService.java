package ci.digitalacademy.MonEtabV14.services;

import ci.digitalacademy.MonEtabV14.services.dto.StudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentDTO save(StudentDTO studentDTO);

    StudentDTO update(StudentDTO studentDTO);

    List<StudentDTO> findAll();

    Optional<StudentDTO> findOne(Long id);

    void delete(Long id);
}
