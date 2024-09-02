package ci.digitalacademy.MonEtabV14.services.mapper;

import ci.digitalacademy.MonEtabV14.models.RoleUser;
import ci.digitalacademy.MonEtabV14.services.dto.RoleUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleUserMapper extends EntityMapper<RoleUserDTO, RoleUser> {
}
