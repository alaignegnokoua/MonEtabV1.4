package ci.digitalacademy.MonEtabV14.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String pseudo;

    private String password;

    private Instant creationDate;

}
