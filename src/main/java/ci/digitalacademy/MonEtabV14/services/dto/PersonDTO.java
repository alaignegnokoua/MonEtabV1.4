package ci.digitalacademy.MonEtabV14.services.dto;

import ci.digitalacademy.MonEtabV14.models.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public abstract class PersonDTO {

    private Long id;

    private Instant birthday;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String urlPicture;

    private Gender gender;
}
