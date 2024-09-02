package ci.digitalacademy.MonEtabV14.repositories;

import ci.digitalacademy.MonEtabV14.models.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppSettingRepository extends JpaRepository<AppSetting, Long> {
    List<AppSetting> findBySmtpUsername(String smtpUsername);
}
