package ci.digitalacademy.MonEtabV14.services.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
public class AbsenceDTO {

    private Long id;

    private Instant absenceDate;

    private Integer absenceNumber;
}
