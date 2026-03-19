package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for holiday pricing.
 *
 * Name: Odunayo Oluokun
 */
public class HolidayDiscountStrategy implements DiscountStrategy {

    /**
     * Applies a 15% discount.
     *
     * @param price the original price
     * @return the discounted price
     */
    @Override
    public double calculate(double price) {
        return price * 0.85;
    }
}