package org.howard.edu.lsp.midterm.strategy;

/**
 * Driver class demonstrating the Strategy Pattern implementation
 * for the price calculation system.
 *
 * @author Your Name
 */
public class Driver {

    /**
     * Main method that demonstrates all four pricing strategies
     * using a base purchase price of 100.0.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator(new RegularPricingStrategy());

        calculator.setStrategy(new RegularPricingStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(100.0));

        calculator.setStrategy(new MemberPricingStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(100.0));

        calculator.setStrategy(new VIPPricingStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(100.0));

        calculator.setStrategy(new HolidayPricingStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(100.0));
    }
}