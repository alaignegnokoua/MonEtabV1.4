package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.models.Absence;
import ci.digitalacademy.MonEtabV14.repositories.AbsenceRepository;
import ci.digitalacademy.MonEtabV14.services.AbsenceService;
import ci.digitalacademy.MonEtabV14.services.dto.AbsenceDTO;
import ci.digitalacademy.MonEtabV14.services.mapper.AbsenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;

    private final AbsenceMapper absenceMapper;

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) {
        log.debug("Request to save Absence: {}", absenceDTO);
        Absence absence = absenceMapper.toEntity(absenceDTO);
        absence = absenceRepository.save(absence);
        return absenceMapper.toDto(absence);
    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO) {
        log.debug("Request to update Absence: {}", absenceDTO);
        return findOne(absenceDTO.getId()).map(existingAbsence -> {
            existingAbsence.setAbsenceDate(absenceDTO.getAbsenceDate());
            existingAbsence.setAbsenceNumber(absenceDTO.getAbsenceNumber());
            return save(existingAbsence);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<AbsenceDTO> findOne(Long id) {
        log.debug("Request to find Absence: {}", id);
        return absenceRepository.findById(id).map(absence -> {
            return absenceMapper.toDto(absence);
        });
    }

    @Override
    public List<AbsenceDTO> findAll() {
        log.debug("Request to find all Absences");
        return absenceRepository.findAll().stream().map(student -> {
            return absenceMapper.toDto(student);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Absence: {}", id);
        absenceRepository.deleteById(id);
    }
}
