package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.models.StudentCards;
import ci.digitalacademy.MonEtabV14.repositories.StudentCardsRepository;
import ci.digitalacademy.MonEtabV14.services.StudentCardsService;
import ci.digitalacademy.MonEtabV14.services.dto.StudentCardsDTO;
import ci.digitalacademy.MonEtabV14.services.mapper.StudentCardsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class StudentCardsServiceImpl implements StudentCardsService {

    private final StudentCardsRepository studentCardsRepository;
    private final StudentCardsMapper studentCardsMapper;

    @Override
    public StudentCardsDTO save(StudentCardsDTO studentCardsDTO) {
        log.debug("Request to save studentCards: {}", studentCardsDTO);
        StudentCards studentCards = studentCardsMapper.toEntity(studentCardsDTO);
        studentCards = studentCardsRepository.save(studentCards);
        return studentCardsMapper.toDto(studentCards);
    }

    @Override
    public StudentCardsDTO update(StudentCardsDTO studentCardsDTO) {
        log.debug("Request to update studentCards: {}", studentCardsDTO);
        return findOne(studentCardsDTO.getId()).map(existingStudentCards -> {
            existingStudentCards.setReference(studentCardsDTO.getReference());
            existingStudentCards.setExpirationDate(studentCardsDTO.getExpirationDate());
            existingStudentCards.setIssueDate(studentCardsDTO.getIssueDate());
            return save(existingStudentCards);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<StudentCardsDTO> findOne(Long id) {
        log.debug("Request to get studentCards : {}", id);
        return studentCardsRepository.findById(id).map(studentCards -> {
            return studentCardsMapper.toDto(studentCards);
        });
    }

    @Override
    public List<StudentCardsDTO> findAll() {
        log.debug("Request to get all studentCards");
        return studentCardsRepository.findAll().stream().map(student -> {
            return studentCardsMapper.toDto(student);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete studentCards : {}", id);
        studentCardsRepository.deleteById(id);
    }
}
