//package com.intoThe.utils;
//
//import com.intoThe.entities.Address;
//import com.intoThe.exceptions.SuppliersOprException.ResourceNotFound;
//import com.intoThe.repository.AddressRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//import java.util.NoSuchElementException;
//
//@Component
//public class AddressUtils {
//
//    /**
//     * This method is used to check if an address exists in the database.
//     *
//     * @param addressId The ID of the address to be checked.
//     * @param addressRepository The repository to use for the check.
//     * @return The address object if the address exists, null otherwise.
//     * @throws ResourceNotFound if the address does not exist.
//     */
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public static Address isAddressExist(Long addressId, AddressRepository addressRepository){
//        return addressRepository.findById(addressId)
//                .orElseThrow(() -> new ResourceNotFound("Address with this id :" +
//                        addressId + " not found!..."));
//
//    }
//}
