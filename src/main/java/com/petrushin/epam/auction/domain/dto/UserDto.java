package com.petrushin.epam.auction.domain.dto;

import com.petrushin.epam.auction.domain.User;
import com.petrushin.epam.auction.domain.UserRole;

public class UserDto {

    private String login;
    private UserRole role;
    private Long id;

    public UserDto(User user) {
        this.login = user.getLogin();
        this.role = user.getRole();
        this.id = user.getId();
    }

    public String getLogin() {
        return login;
    }

    public UserRole getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }
}
