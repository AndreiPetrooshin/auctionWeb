package com.petrushin.epam.auction.model.domain;


public class UserRole implements Identified {

    private static final long serialVersionUID = 5L;

    public static final String GET_ALL = "SELECT * FROM user_role";
    public static final String GET_BY_ID = "SELECT * FROM user_role WHERE role_id=?";
    public static final String ADD_ROLE = "INSERT INTO user_role(user_role, role_id) VALUES (?,?)";
    public static final String UPDATE_ROLE = "UPDATE user_role SET user_role=? WHERE role_id=?";
    public static final String DELETE_BY_ID = "DELETE FROM user_role WHERE role_id=?";

    private Long id;
    private String role;

    public UserRole(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

        if (id != null ? !id.equals(userRole.id) : userRole.id != null) {
            return false;
        }
        return role != null ? role.equals(userRole.role) : userRole.role == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
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
