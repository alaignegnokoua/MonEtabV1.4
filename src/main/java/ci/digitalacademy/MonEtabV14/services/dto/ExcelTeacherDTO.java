package ci.digitalacademy.MonEtabV14.services.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExcelTeacherDTO {

    private Boolean available;

    private String specialty;

    private String firstName;

    private String lastName;

    private String numbers;
}
