//package com.intoThe.controller;
//
//import com.intoThe.dto.AddressDTO;
//import com.intoThe.errorResponse.RetailerExceptionResponse;
//import com.intoThe.exceptions.SuppliersOprException.ResourceNotFound;
//import com.intoThe.service.AddressService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.request.WebRequest;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/AddressService")/**/
//public class AddressController {
//
//    //@Autowired No @Autowired needed — ONLY ONE constructor
//    private final AddressService addressService;
//    public AddressController(AddressService addressService) {
//        this.addressService = addressService;
//    }
//
//    /**
//     * This method is used to get the list of addresses associated with a user
//     *
//     * @param addressUserId The userId of the user whose addresses are to be fetched
//     * @return A ResponseEntity containing a list of {@link AddressDTO} objects,
//     *         with a status code of HttpStatus.FOUND if the addresses are found, else
//     *         HttpStatus.NOT_FOUND
//     */
//
//    /**
//     * This method is used to get the list of addresses associated with a user
//     *
//     * @param addressUserId The userId of the user whose addresses are to be fetched
//     * @return A ResponseEntity containing a list of {@link AddressDTO} objects,
//     *         with a status code of HttpStatus.FOUND if the addresses are found, else
//     *         HttpStatus.NOT_FOUND
//     */
//    @GetMapping("/getAddressByUserId")
//    public ResponseEntity<ArrayList<AddressDTO>> getAddressByUserId(@RequestHeader Long addressUserId){
//        return new ResponseEntity<>(addressService.getAddressByUserId(addressUserId),
//                HttpStatus.FOUND);
//    }
//
//    /**
//     * This method is used to add a new address to the database.
//     *
//     * @param addressToBeAdded The {@link AddressDTO} object containing the details of the address to be added.
//     * @return A ResponseEntity containing a string indicating that the address has been created successfully.
//     *         The status code of the ResponseEntity is HttpStatus.CREATED.
//     */
//    @PostMapping("/addNewAddress")
//    public ResponseEntity<String> addNewAddress(@RequestBody AddressDTO addressToBeAdded){
//        return new ResponseEntity<>(addressService.addNewAddress(addressToBeAdded),
//                HttpStatus.CREATED);
//    }
//
//    /**
//     * This method is used to update an existing address in the database.
//     *
//     * @param addressToBeUpdated The {@link AddressDTO} object containing the updated details of the address.
//     * @return A ResponseEntity containing an {@link AddressDTO} object,
//     *         with a status code of HttpStatus. ACCEPTED if the address is updated successfully, else
//     *         HttpStatus.INTERNAL_SERVER_ERROR.
//     */
//    @PutMapping("/updateExistingAddress")
//    public ResponseEntity<AddressDTO> updateExistingAddress(@RequestBody AddressDTO addressToBeUpdated){
//        return new ResponseEntity<>(addressService.updateExistingAddress(addressToBeUpdated),HttpStatus.ACCEPTED);
//    }
//
//    /**
//     * This method is used to mark an address as the primary address for a user.
//     *
//     * @param addressIdToMarkedAsPrimary The ID of the address to be marked as primary.
//     * @return A ResponseEntity containing a list of {@link AddressDTO} objects,
//     *         with a status code of HttpStatus. ACCEPTED if the address is marked as primary successfully, else
//     *         HttpStatus.INTERNAL_SERVER_ERROR.
//     */
//    @PatchMapping("/makeAddressAsPrimary")
//    public ResponseEntity<ArrayList<AddressDTO>> markAddressAsPrimary(@RequestHeader Long addressIdToMarkedAsPrimary){
//        return new ResponseEntity<>(addressService.makeAddressAsPrimary(addressIdToMarkedAsPrimary),HttpStatus.ACCEPTED);
//    }
//
//    /**
//     * This method is used to delete an existing address from the database.
//     *
//     * @param addressIdToBeDeleted The ID of the address to be deleted.
//     * @return A ResponseEntity containing a string indicating that the address has been deleted successfully,
//     *         with a status code of HttpStatus.OK.
//     */
//    @DeleteMapping("/deleteExistingAddress")
//    public ResponseEntity<String> deleteExistingAddress(@RequestHeader Long addressIdToBeDeleted){
//        return new ResponseEntity<>(addressService.deleteExistingAddress(addressIdToBeDeleted),HttpStatus.OK);
//    }
//
//    /**
//     * This method is used to change the active status of an address
//     *
//     * @param addressId The ID of the address whose active status is to be changed
//     * @param isActive The new active status of the address. Must be either "Y" or "N"
//     * @return A ResponseEntity containing the updated {@link AddressDTO} object,
//     *         with a status code of HttpStatus.OK if the active status is successfully changed,
//     *         else HttpStatus.BAD_REQUEST
//     */
//    @PatchMapping("/changeActiveStatusOfAddress")
//    public ResponseEntity<AddressDTO> changeActiveStatusOfAddress(@RequestHeader Long addressId, @RequestHeader String isActive){
//        return new ResponseEntity<>(addressService.
//                modifyActiveStatusOfAddress(addressId,isActive),HttpStatus.OK);
//    }
//
//    /**
//     * This method is used to get the details of an address by its ID
//     *
//     * @param addressId The ID of the address to be fetched
//     * @return A ResponseEntity containing an {@link AddressDTO} object,
//     *         with a status code of HttpStatus. FOUND if the address is found, else
//     *         HttpStatus.NOT_FOUND
//     */
//    @GetMapping("/getAddressDetailsById")
//    public ResponseEntity<AddressDTO> getAddressDetailsById(@RequestHeader Long addressId){
//        return new ResponseEntity<>(addressService.getAddressById(addressId),HttpStatus.FOUND);
//    }
//
//    @ExceptionHandler(ResourceNotFound.class)
//    public ResponseEntity<RetailerExceptionResponse> addressNotFound(ResourceNotFound resourceNotFound,
//                                                                     WebRequest request){
//        return new ResponseEntity<>(new RetailerExceptionResponse(HttpStatus.NOT_FOUND.value(),
//                resourceNotFound.getMessage(),
//                request.getDescription(false)),HttpStatus.NOT_FOUND);
//    }
//}
