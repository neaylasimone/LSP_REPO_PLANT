package org.howard.edu.lsp.midterm.strategy;

/**
 * Interface defining the strategy for calculating a final price.
 * Each concrete strategy implements a specific discount behavior.
 *
 * @author Neayla Jones
 */
public interface PricingStrategy {

    /**
     * Calculates the final price after applying the strategy's discount.
     *
     * @param price the original price before discount
     * @return the final price after applying the discount
     */
    double calculatePrice(double price);
}