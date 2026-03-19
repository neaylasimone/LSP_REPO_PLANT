package org.howard.edu.lsp.midterm.strategy;

/**
 * Calculates the final price for a customer purchase using
 * an injected PricingStrategy. Delegates all discount logic
 * to the strategy, keeping this class free of conditionals.
 *
 * @author Your Name
 */
public class PriceCalculator {

    private PricingStrategy strategy;

    /**
     * Constructs a PriceCalculator with the specified pricing strategy.
     *
     * @param strategy the pricing strategy to use for calculations
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Sets a new pricing strategy, allowing the calculator's behavior
     * to be changed at runtime.
     *
     * @param strategy the new pricing strategy to apply
     */
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price by delegating to the current strategy.
     *
     * @param price the original price before discount
     * @return the final price after the strategy's discount is applied
     */
    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}