package com.petrushin.epam.auction.domain.dto;

import com.petrushin.epam.auction.domain.UserAddress;

public class UserAddressDto {

    private Long id;
    private UserDto user;
    private String firstName;
    private String secondName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String phone;
    private String postalCode;

    public UserAddressDto(UserAddress userAddress){
        this.id = userAddress.getId();
        this.user = new UserDto(userAddress.getUser());
        this.firstName = userAddress.getFirstName();
        this.secondName = userAddress.getSecondName();
        this.lastName = userAddress.getLastName();
        this.country = userAddress.getCountry();
        this.city = userAddress.getCity();
        this.street = userAddress.getStreet();
        this.phone = userAddress.getPhone();
        this.postalCode = userAddress.getPostalCode();
    }

    public Long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
