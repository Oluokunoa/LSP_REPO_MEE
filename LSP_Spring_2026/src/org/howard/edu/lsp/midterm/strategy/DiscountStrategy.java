package org.howard.edu.lsp.midterm.strategy;

/**
 * Defines a pricing strategy for calculating a final price.
 *
 * Name:Odunayo Oluokun
 */
public interface DiscountStrategy {

    /**
     * Calculates the final price from the original price.
     *
     * @param price the original price
     * @return the final price after applying the strategy
     */
    double calculate(double price);
}