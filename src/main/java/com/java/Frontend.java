package lab.booking;

/**
 * Frontend controller layer.
 * Corresponds to the "frontend" lifeline in the sequence diagram.
 *
 * Sequence implemented here:
 *   User  →  Frontend : request (book a test)
 *   Frontend  →  Auth  : check authentication
 *   Auth  →  Frontend  : authentication result   (dashed return)
 *   Frontend  →  Auth  : book a test
 *   Auth  →  DB        : update db
 *   DB    →  Auth      : success update          (dashed return)
 *   Auth  →  Frontend  : return success book     (dashed return)
 *   Frontend  →  User  : booked successfully     (dashed return)
 */
public class Frontend {

    private final AuthenticationService authService;
    private final BookingProcessor       bookingProcessor;

    public Frontend(AuthenticationService authService, BookingProcessor bookingProcessor) {
        this.authService      = authService;
        this.bookingProcessor = bookingProcessor;
    }

    /**
     * Entry point called by the User.
     * Maps to the first arrow: "request (book a test)".
     *
     * @param userId  ID of the requesting user
     * @param token   session token
     * @param testId  ID of the lab test to book
     * @return BookingResponse delivered back to the User
     */
    public BookingResponse requestBookTest(String userId, String token, String testId) {
        System.out.println("[Frontend] Received booking request from user: " + userId
                + " for test: " + testId);

        // ── Step 1: check authentication ────────────────────────────────────
        boolean authenticated = authService.checkAuthentication(userId, token);

        if (!authenticated) {
            System.out.println("[Frontend] Authentication failed — aborting booking.");
            return new BookingResponse(false, null, "Authentication failed.");
        }

        // ── Step 2: book a test (delegate to booking processor) ─────────────
        System.out.println("[Frontend] Authentication passed — proceeding to book test.");
        BookingResponse response = bookingProcessor.bookTest(userId, testId);

        // ── Step 3: return result to User ────────────────────────────────────
        System.out.println("[Frontend] Returning to user: " + response);
        return response;    // "booked successfully" dashed arrow in diagram
    }
}
