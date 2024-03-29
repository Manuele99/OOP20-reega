package reega.users;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * Application specific implementation of User. This object is used to add a new user to the DB. The password is
 * cyphered using BCrypt algorithm with 12 rounds
 */
public final class NewUser extends GenericUser {
    private static final int HASHING_LOG_ROUNDS = 12;
    private final String passwordHash;

    public NewUser(final Role role, final String name, final String surname, final String email,
            final String fiscalCode, final String password) {
        super(role, name, surname, email, fiscalCode);
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt(NewUser.HASHING_LOG_ROUNDS));
    }

    @Override
    public String getPasswordHash() {
        return this.passwordHash;
    }

}
