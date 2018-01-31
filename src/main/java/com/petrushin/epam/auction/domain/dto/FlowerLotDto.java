package com.petrushin.epam.auction.domain.dto;

import com.petrushin.epam.auction.domain.FlowerLot;

import java.math.BigDecimal;

public class FlowerLotDto {

    private Long id;
    private UserDto user;
    private String type;
    private String name;
    private String description;
    private BigDecimal startPrice;
    private String state;

    public FlowerLotDto(FlowerLot lot) {
        this.id = lot.getId();
        this.user = new UserDto(lot.getUser());
        this.type = lot.getType();
        this.name = lot.getName();
        this.description = lot.getDescription();
        this.startPrice = lot.getStartPrice();
        this.state = lot.getState();
    }

    public Long getId() {
        return id;
    }

    public UserDto getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public String getState() {
        return state;
    }
}
