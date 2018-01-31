package com.petrushin.epam.auction.domain.dto;

import com.petrushin.epam.auction.domain.UserBet;

import java.math.BigDecimal;

public class UserBetDto {

    private String login;
    private BigDecimal bet;

    public UserBetDto(UserBet userBet){
        this.login = userBet.getUser().getLogin();
        this.bet = userBet.getBet();
    }

    public String getLogin() {
        return login;
    }

    public BigDecimal getBet() {
        return bet;
    }
}
