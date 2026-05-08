package lab.booking;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Database layer.
 * Corresponds to the "DB" lifeline in the sequence diagram.
 */
public class Database {

    // In-memory store simulating a real DB table
    private final Map<String, BookingRecord> bookings = new HashMap<>();

    /**
     * Persists a new booking to the database.
     * @param patientId the ID of the patient
     * @param testId    the ID of the test to book
     * @return the generated bookingId on success
     * @throws RuntimeException if the update fails
     */
    public String updateDb(String patientId, String testId) {
        String bookingId = UUID.randomUUID().toString();
        BookingRecord record = new BookingRecord(bookingId, patientId, testId, LocalDate.now(), "PENDING");
        bookings.put(bookingId, record);

        System.out.println("[DB] Booking saved: " + record);
        return bookingId;   // "success update" arrow in diagram
    }

    public BookingRecord findById(String bookingId) {
        return bookings.get(bookingId);
    }

    // ── Inner record ────────────────────────────────────────────────────────
    public record BookingRecord(
            String bookingId,
            String patientId,
            String testId,
            LocalDate date,
            String status) {

        @Override
        public String toString() {
            return String.format("BookingRecord{id=%s, patient=%s, test=%s, date=%s, status=%s}",
                    bookingId, patientId, testId, date, status);
        }
    }
}
