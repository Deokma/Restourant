package by.popolamov.restourant.util.security;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The class PasswordEncryptor.
 */
public final class PasswordEncryptor {
    private static final PasswordEncryptor INSTANCE = new PasswordEncryptor();

    private PasswordEncryptor() {

    }

    /**
     * Gets instance.
     *
     * @return the instance of password encryptor.
     */
    public static PasswordEncryptor getInstance() {
        return INSTANCE;
    }

    /**
     * Encrypt password.
     *
     * @param password the password to encrypt
     * @return the hashed password
     */
    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check password.
     *
     * @param password       the real password
     * @param hashedPassword the hashed password
     * @return the true if passwords are matched and false if not
     */
    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
