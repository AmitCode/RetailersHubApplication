//package com.intoThe.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//import org.springframework.cglib.core.Local;
//
//import java.time.LocalDateTime;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "INTO_ADDRESS_DETAILS")
//@AllArgsConstructor
//public class Address {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long addressId;
//    private String addressType;
//    private String home;
//    private String pinCode;
//    private String area;
//    private String city;
//    private String state;
//    private String country;
//    private String isPrimaryAddress;
//    private String isAddressActive;
//    @CreationTimestamp
//    private LocalDateTime creationDate;
//    @UpdateTimestamp
//    private LocalDateTime lastUpdatedAt;
//
//    @ManyToOne
//    @JoinColumn(name = "userId")
//    private Users userInfo;
//
//    public Address() {
//        this("Y");
//    }
//
//    public Address(String isAddressActive) {
//        this.isAddressActive = isAddressActive;
//    }
//
//}
