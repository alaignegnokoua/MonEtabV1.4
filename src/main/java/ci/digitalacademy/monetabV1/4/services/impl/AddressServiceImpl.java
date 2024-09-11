package ci.digitalacademy.MonEtabV14.services.impl;

import ci.digitalacademy.MonEtabV14.models.Address;
import ci.digitalacademy.MonEtabV14.repositories.AddressRepository;
import ci.digitalacademy.MonEtabV14.services.AddressService;
import ci.digitalacademy.MonEtabV14.services.dto.AddressDTO;
import ci.digitalacademy.MonEtabV14.services.mapper.AddressMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        log.debug("Request to save Address : {}", addressDTO);
        Address address = addressMapper.toEntity(addressDTO);
        address = addressRepository.save(address);
        return addressMapper.toDto(address);
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        log.debug("Request to update Address : {}", addressDTO);
        return findOne(addressDTO.getId()).map(existingAddress -> {
            existingAddress.setCity(addressDTO.getCity());
            existingAddress.setCountry(addressDTO.getCountry());
            existingAddress.setStreet(addressDTO.getStreet());
            return save(existingAddress);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public Optional<AddressDTO> findOne(Long id) {
        log.debug("Request to get Address : {}", id);
        return addressRepository.findById(id).map(addressMapper::toDto);
    }

    @Override
    public List<AddressDTO> findAll() {
        log.debug("Request to get all Addresses");
        return addressRepository.findAll().stream().map(addressMapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Address : {}", id);
        addressRepository.deleteById(id);
    }
}
