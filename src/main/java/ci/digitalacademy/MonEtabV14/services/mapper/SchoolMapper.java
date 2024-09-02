package ci.digitalacademy.MonEtabV14.services.mapper;

import ci.digitalacademy.MonEtabV14.models.School;
import ci.digitalacademy.MonEtabV14.services.dto.SchoolDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper extends EntityMapper<SchoolDTO, School> {
}
