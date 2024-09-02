package ci.digitalacademy.MonEtabV14.services;

import ci.digitalacademy.MonEtabV14.services.dto.AppSettingDTO;

import java.util.List;
import java.util.Optional;

public interface AppSettingService {

    AppSettingDTO save(AppSettingDTO appSettingDTO);

    AppSettingDTO update(AppSettingDTO appSettingDTO);

    Optional<AppSettingDTO> findOne(Long id);

    List<AppSettingDTO> findAll();

    void delete(Long id);

    List<AppSettingDTO> findAllBySmtpUsername(String smtpUsername);
}
