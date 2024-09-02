package ci.digitalacademy.MonEtabV14.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppSettingDTO {

    private Long id;

    private String smtpServer;

    private Long smtpPort;

    private String smtpUsername;

    private String smtpPassword;
}
