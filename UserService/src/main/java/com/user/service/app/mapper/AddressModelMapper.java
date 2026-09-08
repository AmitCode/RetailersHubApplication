package com.user.service.app.mapper;

import com.user.service.app.dtos.UserAddressDto;
import com.user.service.app.entities.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressModelMapper {
    /**
     * Maps an Address object to an AddressDTO object.
     *
     * @param address The Address object to be mapped.
     * @return The mapped AddressDTO object.
     */
    public static UserAddressDto mapToAddressDTO(UserAddress address){
        UserAddressDto addressDTO = new UserAddressDto();
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setAddressType(address.getAddressType());
        addressDTO.setHome(address.getHome());
        addressDTO.setArea(address.getArea());
        addressDTO.setPinCode(address.getPinCode());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setCountry(address.getCountry());
        addressDTO.setIsPrimaryAddress(address.getIsPrimaryAddress());
        addressDTO.setIsAddressActive(address.getIsAddressActive());
        return addressDTO;
    }

    /**
     * Maps an AddressDTO object to an Address object.
     *
     * @param addressDTO The AddressDTO object to be mapped.
     * @return The mapped Address object.
     */
    public static UserAddress mapToAddress(UserAddressDto addressDTO){
        UserAddress address = new UserAddress();
        address.setAddressId(addressDTO.getAddressId());
        address.setAddressType(addressDTO.getAddressType());
        address.setHome(addressDTO.getHome());
        address.setArea(addressDTO.getArea());
        address.setPinCode(addressDTO.getPinCode());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());
        address.setIsPrimaryAddress(addressDTO.getIsPrimaryAddress());
        address.setIsAddressActive(addressDTO.getIsAddressActive());
        return address;
    }


    /**
     * Maps a List of Address objects to an ArrayList of AddressDTO objects.
     *
     * @param addressList The List of Address objects to be mapped.
     * @return The ArrayList of AddressDTO objects resulting from the mapping.
     */
    public ArrayList<UserAddressDto> mapToAddressDTOList(List<UserAddress> addressList){
        ArrayList<UserAddressDto> addressDTOList = new ArrayList<>();
        for(UserAddress address : addressList){
            addressDTOList.add(mapToAddressDTO(address));
        }
        return addressDTOList;
    }
}
