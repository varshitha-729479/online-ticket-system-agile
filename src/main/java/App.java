// File: src/main/java/App.java

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class App {
    
    // Simple refund calculation
    public static double calculateRefund(LocalDateTime cancellationTime, LocalDateTime departureTime, double price) {
        long hoursLeft = ChronoUnit.HOURS.between(cancellationTime, departureTime);
        
        if (hoursLeft >= 24) {
            return price;  // Full refund
        } else if (hoursLeft >= 12) {
            return price * 0.75;  // 75% refund
        } else if (hoursLeft >= 2) {
            return price * 0.50;  // 50% refund
        } else {
            return 0;  // No refund
        }
    }
    
    // Simple cancellation message
    public static String cancelTicket(double price, LocalDateTime cancellationTime, LocalDateTime departureTime) {
        double refund = calculateRefund(cancellationTime, departureTime, price);
        
        if (refund == price) {
            return "CANCELLED: Full refund of $" + price;
        } else if (refund > 0) {
            return "CANCELLED: Partial refund of $" + refund;
        } else {
            return "CANCELLED: No refund";
        }
    }
    
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        
        // Test cases
        System.out.println("=== TICKET CANCELLATION SYSTEM ===\n");
        
        // Test 1: 48 hours before - Full refund
        LocalDateTime departure1 = now.plusHours(48);
        System.out.println("Test 1 - 48 hours before:");
        System.out.println(cancelTicket(100, now, departure1));
        
        // Test 2: 18 hours before - 75% refund
        LocalDateTime departure2 = now.plusHours(18);
        System.out.println("\nTest 2 - 18 hours before:");
        System.out.println(cancelTicket(100, now, departure2));
        
        // Test 3: 5 hours before - 50% refund
        LocalDateTime departure3 = now.plusHours(5);
        System.out.println("\nTest 3 - 5 hours before:");
        System.out.println(cancelTicket(100, now, departure3));
        
        // Test 4: 1 hour before - No refund
        LocalDateTime departure4 = now.plusHours(1);
        System.out.println("\nTest 4 - 1 hour before:");
        System.out.println(cancelTicket(100, now, departure4));
        
        System.out.println("\n✅ System running successfully!");
    }
}