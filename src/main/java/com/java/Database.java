package com.java;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Database {

   
    private final Map<String, BookingRecord> bookings = new HashMap<>();

   
    public String updateDb(String patientId, String testId) {
        String bookingId = UUID.randomUUID().toString();
        BookingRecord record = new BookingRecord(bookingId, patientId, testId, LocalDate.now(), "PENDING");
        bookings.put(bookingId, record);

        System.out.println("[DB] Booking saved: " + record);
        return bookingId;  
    }

    public BookingRecord findById(String bookingId) {
        return bookings.get(bookingId);
    }

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
