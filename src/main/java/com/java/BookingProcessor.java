package com.java;


public class BookingProcessor {

    private final Database database;

    public BookingProcessor(Database database) {
        this.database = database;
    }

  
    public BookingResponse bookTest(String patientId, String testId) {
        System.out.println("[BookingProcessor] Processing booking: patient=" + patientId
                + ", test=" + testId);

        try {
           
            String bookingId = database.updateDb(patientId, testId);

          
            System.out.println("[BookingProcessor] DB update succeeded. bookingId=" + bookingId);
            return new BookingResponse(true, bookingId, "Booked successfully!");

        } catch (Exception e) {
            System.err.println("[BookingProcessor] DB update failed: " + e.getMessage());
            return new BookingResponse(false, null, "Booking failed: " + e.getMessage());
        }
    }
}
