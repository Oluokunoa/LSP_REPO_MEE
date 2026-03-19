package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for member customers.
 *
 * Name: Odunayo Oluokun
 */
public class MemberDiscountStrategy implements DiscountStrategy {

    /**
     * Applies a 10% discount.
     *
     * @param price the original price
     * @return the discounted price
     */
    @Override
    public double calculate(double price) {
        return price * 0.90;
    }
}