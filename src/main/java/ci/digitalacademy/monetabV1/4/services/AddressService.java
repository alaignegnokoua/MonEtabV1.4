package ci.digitalacademy.MonEtabV14.services;

import ci.digitalacademy.MonEtabV14.services.dto.AddressDTO;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    AddressDTO save(AddressDTO addressDTO);

    AddressDTO update(AddressDTO addressDTO);

    Optional<AddressDTO> findOne(Long id);

    List<AddressDTO> findAll();

    void delete(Long id);
}
