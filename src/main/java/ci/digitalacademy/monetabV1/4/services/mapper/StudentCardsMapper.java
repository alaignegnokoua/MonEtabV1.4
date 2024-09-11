package ci.digitalacademy.MonEtabV14.services.mapper;

import ci.digitalacademy.MonEtabV14.models.StudentCards;
import ci.digitalacademy.MonEtabV14.services.dto.StudentCardsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentCardsMapper extends EntityMapper<StudentCardsDTO, StudentCards> {
}
