package org.howard.edu.lsp.midterm.strategy;

/**
 * Pricing strategy for VIP customers.
 *
 * Name:Odunayo Oluokun
 */
public class VipDiscountStrategy implements DiscountStrategy {

    /**
     * Applies a 20% discount.
     *
     * @param price the original price
     * @return the discounted price
     */
    @Override
    public double calculate(double price) {
        return price * 0.80;
    }
}