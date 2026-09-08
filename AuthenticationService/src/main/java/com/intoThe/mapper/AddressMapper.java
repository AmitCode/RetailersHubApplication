//package com.intoThe.mapper;
//
//import com.intoThe.dto.AddressDTO;
//import com.intoThe.entities.Address;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//public interface AddressMapper {
//
//    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
//
//    /**
//     * Maps an Address entity to an AddressDTO
//     */
//    AddressDTO toDto(Address address);
//
//    /**
//     * Maps an AddressDTO to an Address entity
//     */
//    Address toEntity(AddressDTO addressDTO);
//
//    /**
//     * Updates an existing Address entity with values from AddressDTO
//     */
//    @Mapping(target = "addressId", ignore = true) // Prevent ID from being overridden
//    void updateAddressFromDto(AddressDTO dto, @org.mapstruct.MappingTarget Address entity);
//}
