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

    // Vérifie l'existance et initialisation de l'objet
    AppSettingDTO initApp(AppSettingDTO appSettingDTO);

    // Vérification dans la BD. Si elle trouve un enregistrement de notre paramètre, elle ne fait rien dans le cas contraire on fait un enregistrement
    AppSettingDTO existingParameter();
}
