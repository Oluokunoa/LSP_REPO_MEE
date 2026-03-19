package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for regular customers.
 *
 * Name: Odunayo Oluokun
 */
public class RegularDiscountStrategy implements DiscountStrategy {

    /**
     * Returns the original price for a regular customer.
     *
     * @param price the original price
     * @return the unchanged price
     */
    @Override
    public double calculate(double price) {
        return price;
    }
}