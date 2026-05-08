package lab.booking;

/**
 * Response object returned to the User after a booking attempt.
 * Represents the final "booked successfully" message in the sequence diagram.
 */
public class BookingResponse {

    private final boolean success;
    private final String bookingId;
    private final String message;

    public BookingResponse(boolean success, String bookingId, String message) {
        this.success   = success;
        this.bookingId = bookingId;
        this.message   = message;
    }

    public boolean isSuccess()    { return success;   }
    public String  getBookingId() { return bookingId; }
    public String  getMessage()   { return message;   }

    @Override
    public String toString() {
        return String.format("BookingResponse{success=%b, bookingId=%s, message='%s'}",
                success, bookingId, message);
    }
}
