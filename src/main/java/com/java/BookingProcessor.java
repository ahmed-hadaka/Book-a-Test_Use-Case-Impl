package lab.booking;

/**
 * Booking processor — sits inside the "authentication" lifeline's activation bar
 * for the "book a test" message in the sequence diagram, then calls the DB.
 */
public class BookingProcessor {

    private final Database database;

    public BookingProcessor(Database database) {
        this.database = database;
    }

    /**
     * Processes the test booking.
     * Maps to:
     *   Frontend → Auth : book a test
     *   Auth     → DB   : update db
     *   DB       → Auth : success update  (dashed)
     *   Auth     → Frontend : return success book (dashed)
     *
     * @param patientId the patient's ID
     * @param testId    the test to book
     * @return BookingResponse with booking ID on success
     */
    public BookingResponse bookTest(String patientId, String testId) {
        System.out.println("[BookingProcessor] Processing booking: patient=" + patientId
                + ", test=" + testId);

        try {
            // ── update db ────────────────────────────────────────────────────
            String bookingId = database.updateDb(patientId, testId);

            // ── success update → return success book ─────────────────────────
            System.out.println("[BookingProcessor] DB update succeeded. bookingId=" + bookingId);
            return new BookingResponse(true, bookingId, "Booked successfully!");

        } catch (Exception e) {
            System.err.println("[BookingProcessor] DB update failed: " + e.getMessage());
            return new BookingResponse(false, null, "Booking failed: " + e.getMessage());
        }
    }
}
