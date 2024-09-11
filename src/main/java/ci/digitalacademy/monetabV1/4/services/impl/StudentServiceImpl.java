package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.models.Student;
import ci.digitalacademy.MonEtabV14.models.enumeration.Gender;
import ci.digitalacademy.MonEtabV14.repositories.StudentRepository;
import ci.digitalacademy.MonEtabV14.services.StudentService;
import ci.digitalacademy.MonEtabV14.services.dto.StudentDTO;
import ci.digitalacademy.MonEtabV14.services.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentDTO save(StudentDTO studentDTO) {
        log.debug("Request to save Student : {}", studentDTO);
        Student student = studentMapper.toEntity(studentDTO);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO) {
        log.debug("Request to update Student : {}", studentDTO);
        return findOne(studentDTO.getId()).map(existingStudent -> {
            existingStudent.setLastName(studentDTO.getLastName());
            existingStudent.setFirstName(studentDTO.getFirstName());
            existingStudent.setMatricule(studentDTO.getMatricule());
            existingStudent.setPhoneNumberFather(studentDTO.getPhoneNumberFather());
            existingStudent.setBirthday(studentDTO.getBirthday());
            existingStudent.setGender(studentDTO.getGender());
            existingStudent.setPhoneNumber(studentDTO.getPhoneNumber());
            return save(existingStudent);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public StudentDTO update(StudentDTO studentDTO, Long id) {
        return findOne(id).map(student -> {
            student.setLastName(studentDTO.getLastName());
            student.setFirstName(studentDTO.getFirstName());
            student.setMatricule(studentDTO.getMatricule());
            student.setPhoneNumberFather(studentDTO.getPhoneNumberFather());
            student.setBirthday(studentDTO.getBirthday());
            student.setGender(studentDTO.getGender());
            student.setPhoneNumber(studentDTO.getPhoneNumber());
            return save(student);
        }).orElseThrow(() -> new IllegalArgumentException("No student found with id " + id));
    }

    @Override
    public List<StudentDTO> findAll() {
        log.debug("Request to get all Students");
        return studentRepository.findAll().stream().map(student -> {
            return studentMapper.toDto(student);
        }).toList();
    }

    @Override
    public Optional<StudentDTO> findOne(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id).map(student -> {
            return studentMapper.toDto(student);
        });
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentDTO> findByLastNameOrGenderOrMatricule(String query, String gender) {
        log.debug("Request to ");
        return studentRepository.findByLastNameIgnoreCaseOrMatriculeIgnoreCaseAndGender(query, query, Gender.valueOf(gender)).stream().map(student -> {
            return studentMapper.toDto(student);
        }).toList();
    }
}
