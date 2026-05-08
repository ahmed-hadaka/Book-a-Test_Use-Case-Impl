package lab.booking;


public class Frontend {

    private final AuthenticationService authService;
    private final BookingProcessor       bookingProcessor;

    public Frontend(AuthenticationService authService, BookingProcessor bookingProcessor) {
        this.authService      = authService;
        this.bookingProcessor = bookingProcessor;
    }

  
    public BookingResponse requestBookTest(String userId, String token, String testId) {
        System.out.println("[Frontend] Received booking request from user: " + userId
                + " for test: " + testId);

      
        boolean authenticated = authService.checkAuthentication(userId, token);

        if (!authenticated) {
            System.out.println("[Frontend] Authentication failed — aborting booking.");
            return new BookingResponse(false, null, "Authentication failed.");
        }

      
        System.out.println("[Frontend] Authentication passed — proceeding to book test.");
        BookingResponse response = bookingProcessor.bookTest(userId, testId);

     
        System.out.println("[Frontend] Returning to user: " + response);
        return response;    
    }
}
