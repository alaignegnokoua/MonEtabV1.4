package ci.digitalacademy.MonEtabV14.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO extends PersonDTO{

    private String matricule;

    private String phoneNumberFather;

}
