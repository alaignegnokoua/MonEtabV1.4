package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.models.Teacher;
import ci.digitalacademy.MonEtabV14.models.enumeration.Gender;
import ci.digitalacademy.MonEtabV14.repositories.TeacherRepository;
import ci.digitalacademy.MonEtabV14.services.TeacherService;
import ci.digitalacademy.MonEtabV14.services.dto.TeacherDTO;
import ci.digitalacademy.MonEtabV14.services.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        log.debug("Request to save teacher: {}", teacherDTO);
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        teacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO) {
        log.debug("Request to update teacher: {}", teacherDTO);
        return findOne(teacherDTO.getId()).map(existingTeacher -> {
            existingTeacher.setFirstName(teacherDTO.getFirstName());
            existingTeacher.setLastName(teacherDTO.getLastName());
            existingTeacher.setSpecialty(teacherDTO.getSpecialty());
            existingTeacher.setAvailable(teacherDTO.getAvailable());
            existingTeacher.setGender(teacherDTO.getGender());
            existingTeacher.setBirthday(teacherDTO.getBirthday());
            existingTeacher.setPhoneNumber(teacherDTO.getPhoneNumber());
            return save(existingTeacher);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public TeacherDTO update(TeacherDTO teacherDTO, Long id) {
        log.debug("Request to update teacherDTO: {}", id);
        teacherDTO.setId(id);
         return update(teacherDTO);
    }

    @Override
    public Optional<TeacherDTO> findOne(Long id) {
        log.debug("Request to get teacher: {}", id);
        return teacherRepository.findById(id).map(teacher -> {
            return teacherMapper.toDto(teacher);
        });
    }

    @Override
    public List<TeacherDTO> findAll() {
        log.debug("Request to get all teachers");
        return teacherRepository.findAll().stream().map(teacher -> {
            return teacherMapper.toDto(teacher);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete teacher: {}", id);
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherDTO> findByLastNameOrSpecialtyAndGender(String query, String gender) {
        return teacherRepository.findByLastNameIgnoreCaseOrSpecialtyAndGender(query, query, Gender.valueOf(gender)).stream().map(teacher -> {
            return teacherMapper.toDto(teacher);
        }).toList();
    }
}
