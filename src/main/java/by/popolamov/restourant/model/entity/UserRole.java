package by.popolamov.restourant.model.entity;

import org.apache.logging.log4j.core.jackson.ListOfMapEntrySerializer;

/**
 * The enum User role entity.
 */
public enum UserRole {
    /**
     * User role.
     */
    USER(1),
    /**
     * Admin role.
     */
    ADMIN(2);
    private final int id;

    public static UserRole getById(int id) {
        UserRole userRole = UserRole.USER;
        switch (id) {
            case 1:
                userRole = UserRole.USER;
            break;
            case 2:
                userRole = UserRole.ADMIN;
                break;
        };
        return userRole;
    }

    UserRole(int id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id of user role
     */
    public int getId() {
        return id;
    }
}