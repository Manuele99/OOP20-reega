package reega.data;

import java.io.IOException;

import reega.data.models.UserAuth;
import reega.users.User;

/**
 * This controller handles all the operations on users and authentication.
 */
public interface AuthController {
    /**
     * Login using email and password.
     *
     * @param email
     * @param password the password
     * @return the user if login succeeded or null if email not found or wrong password
     * @See GenericUser
     */
    User emailLogin(String email, String password) throws IOException;

    /**
     * Login using fiscal code and password.
     *
     * @param fiscalCode ths user's fiscal code
     * @param password   the password
     * @return the user if login succeeded or null if fiscal code not found or wrong password
     */
    User fiscalCodeLogin(String fiscalCode, String password) throws IOException;

    /**
     * Login using the credentials locally stored.
     *
     * @param credentials credentials
     * @return the user if login succeeded or null if no credentials have been found
     */
    User tokenLogin(UserAuth credentials) throws IOException;

    /**
     * Store selector and validator to enable the remind-me functionality.
     *
     * @param selector  random aphanumerical string up to 12 characters
     * @param validator SHA256 encryption of a random alphanumerical string (64 characters)
     * @throws IOException
     */
    void storeUserCredentials(String selector, String validator) throws IOException;

    /**
     * Remove selector and validator of the user from the DB. The user must prompt in the credentials again to login
     */
    void userLogout() throws IOException;
}
