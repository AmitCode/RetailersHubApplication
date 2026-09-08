package com.intoThe.utils;

import com.intoThe.entities.Suppliers;
import com.intoThe.exceptions.SuppliersOprException.SuppliersOprExceptionHandler;
import com.intoThe.repository.SuppliersRepository;
import org.springframework.stereotype.Component;

@Component
public class SuppliersUtils {

    private final SuppliersRepository suppliersRepository;

    public SuppliersUtils(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    public Suppliers isSupplierExist(int supplierId){
        Suppliers suppliers = suppliersRepository.findById(supplierId)
                .orElseThrow( () -> new SuppliersOprExceptionHandler("Supplier not found"));
        return suppliers;
    }
}
