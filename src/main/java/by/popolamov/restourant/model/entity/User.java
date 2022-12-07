package by.radzionau.imdb.model.entity;

/**
 * The User entity.
 */
public class User {
    private Long userId = -1L;
    private String login;
    private String email;
    private String name;
    private String surname;
    private UserRole role;
    private UserStatus status;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(Long userId) {
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
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

    /**
     * Gets status.
     *
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return userId.equals(user.userId)
                && login.equals(user.login)
                && email.equals(user.email)
                && name.equals(user.name)
                && surname.equals(user.surname)
                && role.equals(user.role)
                && status.equals(user.status);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = result * 31 + login.hashCode();
        result = result * 31 + email.hashCode();
        result = result * 31 + name.hashCode();
        result = result * 31 + surname.hashCode();
        result = result * 31 + role.hashCode();
        result = result * 31 + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{")
                .append("userId=").append(userId)
                .append(", login=").append(login)
                .append(", email=").append(email)
                .append(", name=").append(name)
                .append(", surname=").append(surname)
                .append(", role=").append(role)
                .append(", status=").append(status)
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
        public Builder setUserId(Long userId) {
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
         * Sets email.
         *
         * @param email the email
         * @return the email
         */
        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }

        /**
         * Sets name.
         *
         * @param name the name
         * @return the name
         */
        public Builder setName(String name) {
            user.setName(name);
            return this;
        }

        /**
         * Sets surname.
         *
         * @param surname the surname
         * @return the surname
         */
        public Builder setSurname(String surname) {
            user.setSurname(surname);
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
         * Sets status.
         *
         * @param userStatus the user status
         * @return the status
         */
        public Builder setStatus(UserStatus userStatus) {
            user.setStatus(userStatus);
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
