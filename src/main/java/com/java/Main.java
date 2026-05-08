package lab.booking;

/**
 * Entry point — simulates the "User" actor from the sequence diagram
 * initiating a "book a test" request.
 */
public class Main {

    public static void main(String[] args) {

        // ── Wire up the components (dependency injection) ────────────────────
        Database             db               = new Database();
        AuthenticationService authService     = new AuthenticationServiceImpl();
        BookingProcessor      bookingProcessor = new BookingProcessor(db);
        Frontend              frontend         = new Frontend(authService, bookingProcessor);

        System.out.println("=== Scenario 1: Valid user — happy path ===");
        BookingResponse response1 = frontend.requestBookTest("user-001", "token-abc123", "test-CBC");
        System.out.println("USER RECEIVES: " + response1);

        System.out.println();

        System.out.println("=== Scenario 2: Invalid token — authentication fails ===");
        BookingResponse response2 = frontend.requestBookTest("user-001", "WRONG-TOKEN", "test-CBC");
        System.out.println("USER RECEIVES: " + response2);
    }
}
