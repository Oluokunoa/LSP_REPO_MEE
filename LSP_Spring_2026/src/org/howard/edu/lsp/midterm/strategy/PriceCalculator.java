package org.howard.edu.lsp.midterm.strategy;

/**
 * Uses a discount strategy to calculate final prices.
 *
 * Names: Odunayo Oluokun
 */
public class PriceCalculator {
    private DiscountStrategy strategy;

    /**
     * Sets the pricing strategy to use.
     *
     * @param strategy the discount strategy
     */
    public void setStrategy(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price using the current strategy.
     *
     * @param price the original price
     * @return the calculated final price
     */
    public double calculatePrice(double price) {
        return strategy.calculate(price);
    }
}