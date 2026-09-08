//package com.intoThe.mapper;
//
//import com.intoThe.dto.SupplierDTO;
//import com.intoThe.entities.Suppliers;
//
//public class SuppliersModelMapper {
//
//    /**
//     * This method converts a {@link Suppliers} entity object to a {@link SupplierDTO} DTO object.
//     *
//     * @param supplierInfo the {@link Suppliers} entity object to be converted.
//     * @return a {@link SupplierDTO} DTO object containing the converted values.
//     */
//    public SupplierDTO transferEntityToDTO(Suppliers supplierInfo){
//        SupplierDTO supplierDTO = new SupplierDTO();
//        supplierDTO.setSuppliersId(supplierInfo.getSuppliersId());
//        supplierDTO.setSupplierName(supplierInfo.getSupplierName());
//        supplierDTO.setSupplierEmail(supplierInfo.getSupplierEmail());
//        supplierDTO.setSupplierContactNumber(supplierInfo.getSupplierContactNumber());
//        supplierDTO.setSupplierAddress(supplierInfo.getSupplierAddress());
//        supplierDTO.setSupplierType(supplierInfo.getSupplierType());
//        supplierDTO.setIsActive(supplierInfo.getIsActive());
//        return supplierDTO;
//    }
//
//    /**
//     * This method converts a {@link SupplierDTO} DTO object to a {@link Suppliers} entity object.
//     *
//     * @param supplierDTO the {@link SupplierDTO} DTO object to be converted.
//     * @return a {@link Suppliers} entity object containing the converted values.
//     */
//    public static Suppliers transferDtoToEntity(SupplierDTO supplierDTO){
//        Suppliers suppliers = new Suppliers();
//        suppliers.setSupplierName(supplierDTO.getSupplierName());
//        suppliers.setSupplierContactNumber(supplierDTO.getSupplierContactNumber());
//        suppliers.setSupplierEmail(supplierDTO.getSupplierEmail());
//        suppliers.setSupplierAddress(supplierDTO.getSupplierAddress());
//        suppliers.setSupplierType(supplierDTO.getSupplierType());
//        return suppliers;
//    }
//}
