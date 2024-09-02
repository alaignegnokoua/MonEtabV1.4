package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.models.AppSetting;
import ci.digitalacademy.MonEtabV14.repositories.AppSettingRepository;
import ci.digitalacademy.MonEtabV14.services.AppSettingService;
import ci.digitalacademy.MonEtabV14.services.dto.AppSettingDTO;
import ci.digitalacademy.MonEtabV14.services.mapper.AppSettingMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppSettingServiceImpl implements AppSettingService {

    private final AppSettingRepository appSettingRepository;
    private final AppSettingMapper appSettingMapper;

    @Override
    public AppSettingDTO save(AppSettingDTO appSettingDTO) {
        log.debug("Request to save AppSetting: {}", appSettingDTO);
        AppSetting appSetting = appSettingMapper.toEntity(appSettingDTO);
        appSetting = appSettingRepository.save(appSetting);
        return appSettingMapper.toDto(appSetting);
    }

    @Override
    public AppSettingDTO update(AppSettingDTO appSettingDTO) {
        log.debug("Request to update AppSetting: {}", appSettingDTO);
        return findOne(appSettingDTO.getId()).map(existingAppSetting -> {
            existingAppSetting.setSmtpPassword(appSettingDTO.getSmtpPassword());
            existingAppSetting.setSmtpServer(appSettingDTO.getSmtpServer());
            existingAppSetting.setSmtpPort(appSettingDTO.getSmtpPort());
            existingAppSetting.setSmtpUsername(appSettingDTO.getSmtpUsername());
            return save(existingAppSetting);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<AppSettingDTO> findOne(Long id) {
        log.debug("Request to get AppSetting: {}", id);
        return appSettingRepository.findById(id).map(appSetting -> {
            return appSettingMapper.toDto(appSetting);
        });
    }

    @Override
    public List<AppSettingDTO> findAll() {
        log.debug("Request to get all AppSettings");
        return appSettingRepository.findAll().stream().map(appSetting -> {
            return appSettingMapper.toDto(appSetting);
        }).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AppSetting: {}", id);
        appSettingRepository.deleteById(id);
    }

    @Override
    public List<AppSettingDTO> findAllBySmtpUsername(String smtpUsername) {
        log.debug("Request to find all by smtpUsername: {}", smtpUsername);
        return appSettingRepository.findBySmtpUsername(smtpUsername)
                .stream().map(appSetting -> {
                    return appSettingMapper.toDto(appSetting);
                }).toList();
    }
}
