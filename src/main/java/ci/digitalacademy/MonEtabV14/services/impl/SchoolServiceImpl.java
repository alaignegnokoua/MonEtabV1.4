package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.models.School;
import ci.digitalacademy.MonEtabV14.repositories.SchoolRepository;
import ci.digitalacademy.MonEtabV14.services.SchoolService;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import ci.digitalacademy.MonEtabV14.services.mapper.SchoolMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Override
    public SchoolDTO save(SchoolDTO schoolDTO) {
        log.debug("Request to save School : {}", schoolDTO);
        School school = schoolMapper.toEntity(schoolDTO);
        school = schoolRepository.save(school);
        return schoolMapper.toDto(school);
    }

    @Override
    public SchoolDTO update(SchoolDTO schoolDTO) {
        log.debug("Request to update School : {}", schoolDTO);
        return findOne(schoolDTO.getId()).map(schoolExisting -> {
            schoolExisting.setName(schoolDTO.getName());
            schoolExisting.setUrlLogo(schoolDTO.getUrlLogo());
            return save(schoolExisting);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<SchoolDTO> findOne(Long id) {
        log.debug("Request to get School : {}", id);
        return schoolRepository.findById(id).map(school -> {
            return schoolMapper.toDto(school);
        });
    }

    @Override
    public List<SchoolDTO> findAll() {
        log.debug("Request to get all Schools");
        return schoolRepository.findAll().stream().map(school -> {
            return schoolMapper.toDto(school);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete School : {}", id);
        schoolRepository.deleteById(id);
    }

    @Override
    public SchoolDTO initSchool(SchoolDTO schoolDTO) {
        log.debug("Request to init school {}", schoolDTO);
        SchoolDTO school = existingSchool();
        if (Objects.isNull(school)){
            return save(schoolDTO);
        }
        return school;
    }

    @Override
    public SchoolDTO existingSchool() {
        log.debug("Request to check existing school");
        List<SchoolDTO> schoolDTO = findAll();
        return schoolDTO.stream().findFirst().orElse(null);
    }
}
