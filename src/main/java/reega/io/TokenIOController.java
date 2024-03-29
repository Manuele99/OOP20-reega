/**
 *
 */
package reega.io;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import reega.data.models.UserAuth;

/**
 *
 */
public interface TokenIOController extends IOController {

    /**
     * Get the file path of the token file.
     *
     * @return the file path of the token
     */
    String getTokenFilePath();

    /**
     * Store the user authentication.
     *
     * @param userAuth user authentication to store
     * @throws IOException
     */
    void storeUserAuthentication(UserAuth userAuth) throws IOException;

    /**
     * Delete the user authentication.
     *
     * @throws IOException
     */
    void deleteUserAuthentication() throws IOException;

    /**
     * Read the user authentication.
     *
     * @return an empty optional if no authentication has been found, a filled in optional otherwise
     * @throws IOException
     */
    Optional<UserAuth> readUserAuthentication() throws IOException;

    /**
     * Check if the token file exists.
     *
     * @return true if it exists, false otherwise
     */
    default boolean tokenFileExists() {
        return new File(this.getTokenFilePath()).exists();
    }
}
