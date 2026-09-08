//package com.intoThe.controller;
//
////import com.intoThe.dto.SupplierDTO;
//import com.intoThe.service.SupplierService;
////import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/SupplierService") // 👈 This sets the base URL path
//public class SupplierController {
//
//    //@Autowired
//    private final SupplierService supplierService;
//
//    public SupplierController(SupplierService supplierService) {
//        this.supplierService = supplierService;
//    }
//
//    /**
//     * This method handles a GET request to the "/getMessage" endpoint
//     * and returns a simple message string.
//     *
//     * @return A string containing the message "This is your message"
//     */
//    @GetMapping("/getMessage")
//    public String getMessage(){
//        System.out.println("This is your message!....");
//        return "This is your message";
//    }
//
//    /**
//     * This method handles a POST request to the "/addSuppliers" endpoint and
//     * adds a new supplier to the database using the provided {@link SupplierDTO}
//     * object.
//     *
//     * @param supplierDTO The {@link SupplierDTO} object containing the details of
//     *                    the supplier to be added.
//     * @return A string indicating that the supplier has been successfully saved.
//     */
//    @PostMapping("/addSuppliers")
//    public String addSuppliers(@RequestBody SupplierDTO supplierDTO){
//
//        return supplierService.addNewSuppliers(supplierDTO);
//    }
//
//    /**
//     * This method handles a PUT request to the "/updateSupplierInfo" endpoint and
//     * updates the details of an existing supplier in the database using the provided
//     * {@link SupplierDTO} object.
//     *
//     * @param supplierDTO The {@link SupplierDTO} object containing the updated details of
//     *                    the supplier.
//     * @return A string indicating that the supplier details have been successfully updated.
//     */
//    @PutMapping("/updateSupplierInfo")
//    public String updateSupplierInfo(@RequestBody SupplierDTO supplierDTO){
//        return supplierService.updateSupplierInfo(supplierDTO);
//    }
//
//    /**
//     * This method handles a PATCH request to the "/activateOrDeactivateSuppliers" endpoint and
//     * activates or deactivates a supplier by setting the isActive field to either "Y" or "N".
//     *
//     * @param suppliersId The ID of the supplier to be activated or deactivated.
//     * @param activateStatus A string that is converted to uppercase and used to set the isActive field.
//     *                       Only "Y" or "N" are accepted values.
//     * @return A ResponseEntity containing a string indicating that the supplier has been successfully
//     *         activated or deactivated, with a status code of HttpStatus.OK.
//     */
//    @PatchMapping("/activateOrDeactivateSuppliers")
//    public ResponseEntity<String> activateOrDeactivateSuppliers(@RequestHeader int suppliersId,
//                                                        @RequestHeader String activateStatus){
//        return new ResponseEntity<>(supplierService.inActiveOrDeactivate(
//                suppliersId,activateStatus), HttpStatus.OK);
//    }
//
//
//    /**
//     * This method handles a DELETE request to the "/removeSupplier" endpoint and
//     * deletes a supplier by its ID.
//     *
//     * @param supplierId The ID of the supplier to be deleted.
//     * @return A string indicating that the supplier info has been removed.
//     */
//    @DeleteMapping("/removeSupplier")
//    public String removeSupplier(@RequestHeader int supplierId){
//        return supplierService.deleteSupplier(supplierId);
//    }
//}
