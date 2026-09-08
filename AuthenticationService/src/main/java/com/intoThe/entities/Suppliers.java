package com.intoThe.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
//Create and separate supplier microservice for suppliers management.
@Entity
@Table(name = "INTO_SUPPLIERS_DATA")
@Getter
@Setter
@AllArgsConstructor
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int suppliersId;
    private String supplierName;
    @Column(nullable = false)
    private String supplierEmail;
    @Column(nullable = false)
    private String supplierContactNumber;
    @ColumnDefault("'Y'")
    private String isActive;
    private String supplierType;
    private String supplierAddress;
    @CreationTimestamp
    private String createdDate;
    @UpdateTimestamp
    private String lastUpdatedDate;

    public Suppliers() {
        this("Y");
    }

    public Suppliers(String isActive) {
        this.isActive = isActive;
        System.out.println("Constructor Chaining!...");
    }

}
