package ci.digitalacademy.MonEtabV14.services.mapper;

import ci.digitalacademy.MonEtabV14.models.Address;
import ci.digitalacademy.MonEtabV14.services.dto.AddressDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
}
