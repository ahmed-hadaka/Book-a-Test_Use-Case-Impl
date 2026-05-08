package lab.booking;

/**
 * Authentication service interface.
 * Corresponds to the "authentication" lifeline in the sequence diagram.
 */
public interface AuthenticationService {

    /**
     * Checks whether the given user session/token is valid.
     * @param userId  the ID of the user making the request
     * @param token   the session token to validate
     * @return true if authenticated, false otherwise
     */
    boolean checkAuthentication(String userId, String token);
}
