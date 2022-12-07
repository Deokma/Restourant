package by.popolamov.restourant.model.entity;

/**
 * The User entity.
 */
public class User {
    private int userId;
    private String login;
    private String firstName;
    private String lastName;
    private UserRole role;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets name.
     *
     * @param firstName the firstName
     */
    public void setName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets surname.
     *
     * @param lastName the lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public UserRole getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(UserRole role) {
        this.role = role;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        User user = (User) o;
//        return userId.equals(user.userId)
//                && login.equals(user.login)
//                && firstName.equals(user.firstName)
//                && lastName.equals(user.lastName)
//                && role.equals(user.role);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = userId.hashCode();
//        result = result * 31 + login.hashCode();
//        result = result * 31 + firstName.hashCode();
//        result = result * 31 + lastName.hashCode();
//        result = result * 31 + role.hashCode();
//        return result;
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{")
                .append("userId=").append(userId)
                .append(", login=").append(login)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", role=").append(role)
                .append('}');
        return sb.toString();
    }

    /**
     * Gets builder.
     *
     * @return the builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * The Builder for User.
     */
    public static class Builder {
        private final User user;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            user = new User();
        }

        /**
         * Sets user id.
         *
         * @param userId the user id
         * @return the user id
         */
        public Builder setUserId(int userId) {
            user.setUserId(userId);
            return this;
        }

        /**
         * Sets login.
         *
         * @param login the login
         * @return the login
         */
        public Builder setLogin(String login) {
            user.setLogin(login);
            return this;
        }

        /**
         * Sets name.
         *
         * @param firstName the firstName
         * @return the firstName
         */
        public Builder setFirstName(String firstName) {
            user.setName(firstName);
            return this;
        }

        /**
         * Sets surname.
         *
         * @param lastName the lastName
         * @return the lastName
         */
        public Builder setLastName(String lastName) {
            user.setLastName(lastName);
            return this;
        }

        /**
         * Sets role.
         *
         * @param userRole the user role
         * @return the role
         */
        public Builder setRole(UserRole userRole) {
            user.setRole(userRole);
            return this;
        }

        /**
         * Build user.
         *
         * @return the user
         */
        public User build() {
            return user;
        }
    }
}
