package org.howard.edu.lsp.midterm.strategy;

/**
 * Demonstrates the Strategy Pattern implementation
 * for customer pricing.
 *
 * Name:Odunayo Oluokun
 */
public class Driver {

    /**
     * Runs the pricing examples.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();
        double price = 100.0;

        calculator.setStrategy(new RegularDiscountStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(price));

        calculator.setStrategy(new MemberDiscountStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(price));

        calculator.setStrategy(new VipDiscountStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(price));

        calculator.setStrategy(new HolidayDiscountStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(price));
    }
}