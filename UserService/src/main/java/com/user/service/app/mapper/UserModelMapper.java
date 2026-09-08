package com.user.service.app.mapper;

import com.user.service.app.dtos.UserAddressDto;
import com.user.service.app.dtos.UserDto;
import com.user.service.app.dtos.request.UserCreationRequest;
import com.user.service.app.entities.User;
import com.user.service.app.entities.UserAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserModelMapper {

    public static User mapToUserV1(UserCreationRequest request){

        User users = new User();
        users.setAuthUserId(request.getAuthUserId());
        users.setUserName(request.getUserName());
        users.setPassword(request.getPassword());
        users.setConfirmPassword(request.getPassword());

        return users;
    }

    /**
     * This method maps a {@link UserDto} object to a {@link User} entity object.
     *
     * @param userDTO The {@link UserDto} object to be mapped.
     * @return The mapped {@link User} entity object.
     */
    public static User mapToUser(UserDto userDTO){

        User users = getUser(userDTO);
        System.out.println(users.getIsEmailVerified() + "," + users.getIsMobileVerified());
        List<UserAddress> addresses = userDTO.getAddresses().stream()
                //.map(addressDTO -> AddressModelMapper.mapToAddress(addressDTO))writing it to method reference
                .map(AddressModelMapper::mapToAddress)//Method reference
                .collect(Collectors.toList());

        addresses.forEach(address -> address.setUserInfo(users));
        users.setAddresses(addresses);

        return users;
    }

    private static User getUser(UserDto userDTO) {
        User users = new User();
        users.setAuthUserId(userDTO.getAuthUserId());
        users.setUserName(userDTO.getUserName());
        users.setUserFirstName(userDTO.getUserFirstName());
        users.setUserMiddleName(userDTO.getUserMiddleName());
        users.setUserLastName(userDTO.getUserLastName());
        users.setUserContactNumber(userDTO.getUserContactNumber());
        users.setUserEmailId(userDTO.getUserEmailId());
        users.setIsEmailVerified(userDTO.getIsEmailVerified());
        users.setIsMobileVerified(userDTO.getIsMobileVerified());
        users.setPassword(userDTO.getUserPassword());
        users.setConfirmPassword(userDTO.getConfirmPassword());
        return users;
    }

    /**
     * This method maps a {@link User} entity object to a {@link UserDto} object.
     *
     * @param users The {@link User} entity object to be mapped.
     * @return The mapped {@link UserDto} object.
     */
    public static UserDto mapToUserDTO(User users){

        UserDto userDTO = new UserDto();
        userDTO.setUserId(users.getUserId());
        userDTO.setAuthUserId(users.getAuthUserId());
        userDTO.setUserName(users.getUserName());
        userDTO.setUserFirstName(users.getUserFirstName());
        userDTO.setUserMiddleName(users.getUserMiddleName());
        userDTO.setUserLastName(users.getUserLastName());
        userDTO.setIsUserActive(users.getIsUserActive());
        userDTO.setUserEmailId(users.getUserEmailId());
        userDTO.setUserContactNumber(users.getUserContactNumber());
        userDTO.setIsMobileVerified(users.getIsMobileVerified());
        userDTO.setIsEmailVerified(users.getIsEmailVerified());
        userDTO.setUserPassword(users.getPassword());
        userDTO.setConfirmPassword(users.getConfirmPassword());

        List<UserAddressDto> addresses = users.getAddresses().stream()
                .map(AddressModelMapper::mapToAddressDTO)
                .collect(Collectors.toList());

        addresses.forEach(address -> address.setUserInfo(userDTO));

        userDTO.setAddresses(addresses);

        return userDTO;
    }

    /**
     * This method maps a list of {@link User} entity objects to a list of {@link UserDto} objects.
     *
     * @param users The list of {@link User} entity objects to be mapped.
     * @return The mapped list of {@link UserDto} objects.
     */
    public ArrayList<UserDto> mapToListOfUserDTO(List<User> users){
        ArrayList<UserDto> userDTOS = new ArrayList<>();
        for (User user : users){
            userDTOS.add(mapToUserDTO(user));
        }
        return userDTOS;
    }

    public static User mapToUser(User users, UserDto userDTO) {
        users.setUserFirstName(userDTO.getUserFirstName());
        users.setUserMiddleName(userDTO.getUserMiddleName());
        users.setUserLastName(userDTO.getUserLastName());
        users.setUserContactNumber(userDTO.getUserContactNumber());
        users.setIsEmailVerified(userDTO.getIsEmailVerified());
        users.setIsMobileVerified(userDTO.getIsMobileVerified());
        users.setPassword(userDTO.getUserPassword());
        users.setConfirmPassword(userDTO.getConfirmPassword());
        List<UserAddress> addresses = userDTO.getAddresses().stream()
                .map(AddressModelMapper::mapToAddress)
                .collect(Collectors.toList());
        addresses.forEach(address -> address.setUserInfo(users));
        users.setAddresses(addresses);
        return users;
    }
}
