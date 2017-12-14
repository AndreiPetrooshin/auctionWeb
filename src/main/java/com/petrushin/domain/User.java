package com.petrushin.domain;

public class User {

    public static final String GET_BY_ID = "SELECT * FROM user WHERE user_id=?";
    public static final String GET_ALL = "SELECT * FROM user";
    public static final String ADD_USER = "INSERT INTO user (role_id, u_login," +
            "u_password, u_email,user_id) VALUES (?,?,MD5(?),?,?)";
    public static final String GET_BY_LOGIN = "SELECT * FROM user WHERE u_login=?";
    public static final String GET_LOGIN = "SELECT u_login FROM user WHERE u_login=?";
    public static final String GET_EMAIL = "SELECT u_email FROM user WHERE u_email=?";
    public static final String DELETE_BY_ID = "DELETE FROM user WHERE user_id=?" ;
    public static final String UPDATE_USER = "UPDATE user SET role_id=?, u_login=?," +
            " u_password=?, u_email=? WHERE user_id=?";

    private int id;
    private int roleId;
    private String login;
    private String password;
    private String email;


    public User() {
    }

    public User(int id, int roleId, String login, String password, String email) {
        this.id = id;
        this.roleId = roleId;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (roleId != user.roleId) {
            return false;
        }
        if (login != null ?
                !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (password != null ?
                !password.equals(user.password) : user.password != null) {
            return false;
        }
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
