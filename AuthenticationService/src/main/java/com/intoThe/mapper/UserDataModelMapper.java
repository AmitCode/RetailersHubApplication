package com.intoThe.mapper;

import com.intoThe.dto.UserDTO;
import com.intoThe.dto.request.UserRegistrationRequest;
import com.intoThe.entities.Users;

import java.util.ArrayList;
import java.util.List;

public class UserDataModelMapper {

    /**
     * This method maps a {@link UserDTO} object to a {@link Users} entity object.
     *
     * @param userDTO The {@link UserDTO} object to be mapped.
     * @return The mapped {@link Users} entity object.
     */
    public static Users mapToUser(UserDTO userDTO){

        Users users = new Users();
        users.setUserName(userDTO.getUserName());
        users.setPassword(userDTO.getUserPassword());
        users.setIsUserActive(users.getIsUserActive());
        users.setUserEmail(userDTO.getUserEmail());

//        List<Address> addresses = userDTO.getAddresses().stream()
//                //.map(addressDTO -> AddressModelMapper.mapToAddress(addressDTO))writing it to method reference
//                .map(AddressModelMapper::mapToAddress)//Method reference
//                .collect(Collectors.toList());
//
//        addresses.forEach(address -> address.setUserInfo(users));
//        users.setAddresses(addresses);

        return users;
    }

    /**
     * This method maps a {@link Users} entity object to a {@link UserDTO} object.
     *
     * @param users The {@link Users} entity object to be mapped.
     * @return The mapped {@link UserDTO} object.
     */
    public static UserDTO mapToUserDTO(Users users){

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(users.getUserId());
        userDTO.setUserName(users.getUserName());
        userDTO.setIsUserActive(users.getIsUserActive());
        userDTO.setUserPassword(users.getPassword());
        userDTO.setUserEmail(users.getUserEmail());

//        List<AddressDTO> addresses = users.getAddresses().stream()
//                .map(AddressModelMapper::mapToAddressDTO)
//                .collect(Collectors.toList());
//
//        //addresses.forEach(address -> address.setUserInfo(users));
//
//        userDTO.setAddresses(addresses);

        return userDTO;
    }

    /**
     * This method maps a list of {@link Users} entity objects to a list of {@link UserDTO} objects.
     *
     * @param users The list of {@link Users} entity objects to be mapped.
     * @return The mapped list of {@link UserDTO} objects.
     */
    public ArrayList<UserDTO> mapToListOfUserDTO(List<Users> users){
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (Users user : users){
            userDTOS.add(mapToUserDTO(user));
        }
        return userDTOS;
    }

    public static Users mapToUser(UserRegistrationRequest registrationRequest){

        Users users = new Users();
        users.setUserName(registrationRequest.getUserName());
        users.setPassword(registrationRequest.getUserPassword());
        users.setUserEmail(registrationRequest.getUserEmail());

//        List<Address> addresses = userDTO.getAddresses().stream()
//                //.map(addressDTO -> AddressModelMapper.mapToAddress(addressDTO))writing it to method reference
//                .map(AddressModelMapper::mapToAddress)//Method reference
//                .collect(Collectors.toList());
//
//        addresses.forEach(address -> address.setUserInfo(users));
//        users.setAddresses(addresses);

        return users;
    }

}
