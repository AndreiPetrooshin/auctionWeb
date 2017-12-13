package com.petrushin.domain;

public class UserRole {

    public static final String GET_ALL = "SELECT * FROM user_role";
    public static final String GET_BY_ID = "SELECT * FROM user_role WHERE role_id=?";
    public static final String ADD_ROLE = "INSERT INTO user_role(user_role) VALUES (?)";

    public UserRole(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserRole() {
    }

    private int id;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
