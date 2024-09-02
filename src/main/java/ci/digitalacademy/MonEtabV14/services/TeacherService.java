package ci.digitalacademy.MonEtabV14.services;

import ci.digitalacademy.MonEtabV14.services.dto.TeacherDTO;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    TeacherDTO save(TeacherDTO teacherDTO);

    TeacherDTO update(TeacherDTO teacherDTO);

    Optional<TeacherDTO> findOne(Long id);

    List<TeacherDTO> findAll();

    void delete(Long id);
}
