// File: src/test/java/AppTest.java

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

public class AppTest {
    
    private LocalDateTime now;
    
    @BeforeEach
    void setUp() {
        now = LocalDateTime.now();
    }
    
    // Test 1: Full refund (24+ hours)
    @Test
    void testFullRefund() {
        LocalDateTime departure = now.plusHours(48);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(100, refund, "Should get full refund");
    }
    
    // Test 2: 75% refund (12-24 hours)
    @Test
    void testSeventyFivePercentRefund() {
        LocalDateTime departure = now.plusHours(18);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(75, refund, "Should get 75% refund");
    }
    
    // Test 3: 50% refund (2-12 hours)
    @Test
    void testFiftyPercentRefund() {
        LocalDateTime departure = now.plusHours(5);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(50, refund, "Should get 50% refund");
    }
    
    // Test 4: No refund (less than 2 hours)
    @Test
    void testNoRefund() {
        LocalDateTime departure = now.plusHours(1);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(0, refund, "Should get no refund");
    }
    
    // Test 5: Edge case - exactly 24 hours
    @Test
    void testExactly24Hours() {
        LocalDateTime departure = now.plusHours(24);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(100, refund, "Exactly 24 hours should get full refund");
    }
    
    // Test 6: Edge case - exactly 12 hours
    @Test
    void testExactly12Hours() {
        LocalDateTime departure = now.plusHours(12);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(75, refund, "Exactly 12 hours should get 75% refund");
    }
    
    // Test 7: Edge case - exactly 2 hours
    @Test
    void testExactly2Hours() {
        LocalDateTime departure = now.plusHours(2);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(50, refund, "Exactly 2 hours should get 50% refund");
    }
    
    // Test 8: Different ticket price
    @Test
    void testDifferentPrice() {
        LocalDateTime departure = now.plusHours(48);
        double refund = App.calculateRefund(now, departure, 250);
        assertEquals(250, refund, "Full refund of $250");
    }
    
    // Test 9: Cancellation message test
    @Test
    void testCancellationMessageFullRefund() {
        LocalDateTime departure = now.plusHours(48);
        String message = App.cancelTicket(100, now, departure);
        assertTrue(message.contains("Full refund"));
    }
    
    // Test 10: Cancellation message partial refund
    @Test
    void testCancellationMessagePartialRefund() {
        LocalDateTime departure = now.plusHours(18);
        String message = App.cancelTicket(100, now, departure);
        assertTrue(message.contains("Partial refund"));
    }
    
    // Test 11: Cancellation message no refund
    @Test
    void testCancellationMessageNoRefund() {
        LocalDateTime departure = now.plusHours(1);
        String message = App.cancelTicket(100, now, departure);
        assertTrue(message.contains("No refund"));
    }
    
    // Test 12: After departure time
    @Test
    void testAfterDeparture() {
        LocalDateTime departure = now.minusHours(1);
        double refund = App.calculateRefund(now, departure, 100);
        assertEquals(0, refund, "After departure = no refund");
    }
    
    // Test 13: Zero price ticket
    @Test
    void testZeroPriceTicket() {
        LocalDateTime departure = now.plusHours(48);
        double refund = App.calculateRefund(now, departure, 0);
        assertEquals(0, refund, "Zero price = zero refund");
    }
    
    // Test 14: Large price ticket
    @Test
    void testLargePriceTicket() {
        LocalDateTime departure = now.plusHours(48);
        double refund = App.calculateRefund(now, departure, 1000);
        assertEquals(1000, refund, "Full refund of $1000");
    }
}