//package com.intoThe.service.impl;
//
//import com.intoThe.dto.SupplierDTO;
//import com.intoThe.entities.Suppliers;
//import com.intoThe.mapper.SuppliersModelMapper;
//import com.intoThe.repository.SuppliersRepository;
//import com.intoThe.service.SupplierService;
//import com.intoThe.utils.SuppliersUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SupplierServiceImpl implements SupplierService {
//
//
//    private final SuppliersRepository suppliersRepository;
//    private final SuppliersUtils suppliersUtils;
//
//    //@Autowired ---> No @Autowired needed — ONLY ONE constructor
//    public SupplierServiceImpl(SuppliersRepository suppliersRepository,SuppliersUtils suppliersUtils) {
//        this.suppliersRepository = suppliersRepository;
//        this.suppliersUtils = suppliersUtils;
//    }
//
//    /**
//     * This method is used to add new supplier into the database.
//     *
//     * @param supplierDTO is the DTO object that contains the details of the supplier.
//     * @return a string indicating that the supplier has been successfully saved.
//     */
//    public String addNewSuppliers(SupplierDTO supplierDTO){
//        Suppliers suppliers = new Suppliers();
//        suppliers = SuppliersModelMapper.transferDtoToEntity(supplierDTO);
//        suppliersRepository.save(suppliers);
//        return "saved";
//    }
//
//    /**
//     * This method is used to update the details of an existing supplier in the database.
//     *
//     * @param supplierDTO is the DTO object that contains the updated details of the supplier.
//     * @return a string indicating that the supplier details have been successfully updated.
//     */
//    public String updateSupplierInfo(SupplierDTO supplierDTO){
//        Suppliers suppliers = suppliersUtils.isSupplierExist(supplierDTO.getSuppliersId());
//
//        suppliersRepository.save(updateDetails(suppliers,supplierDTO));
//
//        return "Supplier details has been updated";
//    }
//
//    /**
//     * This method updates the details of a supplier with the details from the {@link SupplierDTO}.
//     *
//     * @param suppliers the {@link Suppliers} object that will be updated.
//     * @param supplierDTO the {@link SupplierDTO} object containing the new details.
//     * @return the updated {@link Suppliers} object.
//     */
//    public Suppliers updateDetails(Suppliers suppliers,SupplierDTO supplierDTO){
//        suppliers.setSupplierName(supplierDTO.getSupplierName());
//        suppliers.setSupplierContactNumber(supplierDTO.getSupplierContactNumber());
//        suppliers.setSupplierEmail(supplierDTO.getSupplierEmail());
//        suppliers.setSupplierAddress(supplierDTO.getSupplierAddress());
//        suppliers.setSupplierType(supplierDTO.getSupplierType());
//        suppliers.setIsActive(supplierDTO.getIsActive());
//        return suppliers;
//
//    }
//
//
//    /**
//     * This method is used to deactivate or inactivate a supplier by setting the isActive field to either "Y" or "N".
//     *
//     * @param supplierId the ID of the supplier to be deactivated or inactivated.
//     * @param activeStatus a string that is converted to uppercase and used to set the isActive field.
//     *                     Only "Y" or "N" are accepted values.
//     * @return a string indicating that the supplier has been successfully deactivated or inactivated.
//     * @throws IllegalArgumentException if the activeStatus is not "Y" or "N".
//     */
//    public String inActiveOrDeactivate(int supplierId, String activeStatus) {
//        Suppliers suppliers = suppliersUtils.isSupplierExist(supplierId);
//
//        suppliers.setIsActive(activeStatus.toUpperCase());
//        suppliersRepository.save(suppliers);
//        return "Suppliers deactivated.";
//    }
//
//    /**
//     * This method is used to delete a supplier by its ID.
//     *
//     * @param supplierId the ID of the supplier to be deleted.
//     * @return a string indicating that the supplier info has been removed.
//     * @throws //SuppliersOprExceptionHandler if the supplier with the given ID does not exist.
//     */
//    public String deleteSupplier(int supplierId) {
//        Suppliers suppliers = suppliersUtils.isSupplierExist(supplierId);
//        suppliersRepository.deleteById(supplierId);
//        return "Supplier info has been removed!...";
//    }
//}
