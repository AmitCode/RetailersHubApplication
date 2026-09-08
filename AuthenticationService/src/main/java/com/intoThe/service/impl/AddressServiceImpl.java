//package com.intoThe.service.impl;
//
//import com.intoThe.dto.AddressDTO;
//import com.intoThe.entities.Address;
//import com.intoThe.mapper.AddressModelMapper;
//import com.intoThe.repository.AddressRepository;
//import com.intoThe.service.AddressService;
//import com.intoThe.utils.AddressUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//@Service
//public class AddressServiceImpl implements AddressService {
//
//    private final AddressRepository addressRepository;
//    private final AddressModelMapper addressModelMapper;
//
//    public AddressServiceImpl(AddressRepository addressRepository, AddressModelMapper addressModelMapper) {
//        this.addressRepository = addressRepository;
//        this.addressModelMapper = addressModelMapper;
//    }
//
//
//    /**
//     * This method is used to add a new address to the database.
//     *
//     * @param addressDTO The {@link AddressDTO} object containing the details of the address to be added.
//     * @return A string indicating that the address has been created successfully.
//     */
//    @Override
//    public String addNewAddress(AddressDTO addressDTO){
//        Address address = addressRepository.save(addressModelMapper.
//                mapToAddress(addressDTO));
//        return "Address has been created!...";
//    }
//
//    /**
//     * This method is used to update an existing address in the database.
//     *
//     * @param addressDTO The {@link AddressDTO} object containing the updated details of the address.
//     * @return An {@link AddressDTO} object representing the updated address.
//     */
//    @Override
//    public AddressDTO updateExistingAddress(AddressDTO addressDTO) {
//
//        Address exitingAddress = AddressUtils.isAddressExist(addressDTO.getAddressId(),addressRepository);
//
//        exitingAddress.setAddressType(addressDTO.getAddressType());
//        exitingAddress.setHome(addressDTO.getHome());
//        exitingAddress.setArea(addressDTO.getArea());
//        exitingAddress.setPinCode(addressDTO.getPinCode());
//        exitingAddress.setCity(addressDTO.getCity());
//        exitingAddress.setState(addressDTO.getState());
//        exitingAddress.setCountry(addressDTO.getCountry());
//        exitingAddress.setIsPrimaryAddress(addressDTO.getIsPrimaryAddress());
//        exitingAddress.setIsAddressActive(addressDTO.getIsAddressActive());
//
//        return addressModelMapper.mapToAddressDTO(exitingAddress);
//    }
//
//    /**
//     * This method is used to delete an existing address from the database.
//     *
//     * @param addressId The ID of the address to be deleted.
//     * @return A string indicating that the address has been deleted successfully.
//     */
//    @Override
//    public String deleteExistingAddress(Long addressId) {
//        addressRepository.deleteById(addressId);
//        return "address has been deleted!...";
//    }
//
//    /**
//     * This method is used to modify the active status of an address.
//     *
//     * @param addressId The ID of the address whose active status is to be modified.
//     * @param isActive The new active status of the address. Must be either "Y" or "N".
//     * @return An {@link AddressDTO} object representing the updated address with its active status changed.
//     */
//    @Override
//    public AddressDTO modifyActiveStatusOfAddress(Long addressId, String isActive) {
//        Address address = AddressUtils.isAddressExist(addressId,addressRepository);
//        address.setIsAddressActive("Y");
//        Address newAddress = addressRepository.save(address);
//        return addressModelMapper.mapToAddressDTO(newAddress);
//    }
//
//    /**
//     * This method is used to get the list of addresses associated with a user.
//     *
//     * @param userId The userId of the user whose addresses are to be fetched.
//     * @return An ArrayList of {@link AddressDTO} objects representing the addresses associated with the user.
//     */
//    @Override
//    public ArrayList<AddressDTO> getAddressByUserId(Long userId) {
//        List<Address> addressList = new ArrayList<>();
//        return addressModelMapper.mapToAddressDTOList(addressList);
//    }
//
//    /**
//     * This method is used to get the details of an address by its ID.
//     *
//     * @param addressId The ID of the address to be fetched.
//     * @return An {@link AddressDTO} object representing the address with all its details.
//     */
//    @Override
//    public AddressDTO getAddressById(Long addressId) {
//        Address address = AddressUtils.isAddressExist(addressId,addressRepository);
//        return addressModelMapper.mapToAddressDTO(address);
//
//    }
//
//    /**
//     * This method is used to make an address as primary address.
//     *
//     * @param addressId The ID of the address to be made as primary.
//     * @return An ArrayList of {@link AddressDTO} objects representing the updated address
//     *         of the user.
//     */
//    @Override
//    public ArrayList<AddressDTO> makeAddressAsPrimary(Long addressId) {
//        Address address = AddressUtils.isAddressExist(addressId,addressRepository);
//        address.setIsPrimaryAddress("Y");
//        address = addressRepository.save(address);
//        List<Address> addressList = new ArrayList<>();
//        for (Address address1: addressList){
//            if(!Objects.equals(address1.getAddressId(), address.getAddressId())){
//                address1.setIsPrimaryAddress("N");
//                addressRepository.save(address1);
//            }
//        }
//        return addressModelMapper.mapToAddressDTOList(addressList);
//    }
//}
