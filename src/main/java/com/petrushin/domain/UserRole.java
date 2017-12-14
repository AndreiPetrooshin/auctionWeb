package com.petrushin.domain;

public class UserRole {

    public static final String GET_ALL = "SELECT * FROM user_role";
    public static final String GET_BY_ID = "SELECT * FROM user_role WHERE role_id=?";
    public static final String ADD_ROLE = "INSERT INTO user_role(user_role, role_id) VALUES (?,?)";
    public static final String UPDATE_ROLE = "UPDATE user_role SET user_role=? WHERE role_id=?";
    public static final String DELETE_BY_ID = "DELETE FROM user_role WHERE role_id=?";

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserRole userRole = (UserRole) o;

        if (id != userRole.id) {
            return false;
        }
        return role != null ? role.equals(userRole.role) : userRole.role == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
