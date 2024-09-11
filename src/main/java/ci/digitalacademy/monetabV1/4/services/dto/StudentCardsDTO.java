package ci.digitalacademy.MonEtabV14.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StudentCardsDTO {

    private Long id;

    private String reference;

    private Instant issueDate;

    private Instant expirationDate;

}
