//package com.intoThe.dto;
//
//import com.intoThe.entities.Users;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//public class AddressDTO {
//    private Long addressId;
//    @NotBlank(message = "Address type can't be null.")
//    private String addressType;
//    @NotBlank(message = "Home can't be null.")
//    private String home;
//    @NotBlank(message = "Pin code can't be null.")
//    private String pinCode;
//    @NotBlank(message = "Area can't be null.")
//    private String area;
//    @NotBlank(message = "City can't be null.")
//    private String city;
//    @NotBlank(message = "State can't be null.")
//    private String state;
//    @NotBlank(message = "Country can't be null.")
//    private String country;
//    @NotBlank(message = "Please select primary address.")
//    private String isPrimaryAddress;
//    private String isAddressActive;
//    private Users userInfo;
//
//    public AddressDTO() {
//        this("Y");
//    }
//
//    public AddressDTO(String isAddressActive) {
//        this.isAddressActive = isAddressActive;
//    }
//
//}
