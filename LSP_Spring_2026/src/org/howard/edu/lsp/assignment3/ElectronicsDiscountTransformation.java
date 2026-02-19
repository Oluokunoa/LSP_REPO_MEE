package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
	 * Name: Odunayo Oluokun
 * Transformation #2: If original category is "Electronics", apply a 10% discount to price.
 * Rounding to two decimals HALF_UP is enforced through Product#setFinalRoundedPrice.
 */
public class ElectronicsDiscountTransformation implements Transformation {

    /** Applies 10% discount if original category is Electronics. */
    @Override
    public void apply(Product product) {
        if ("Electronics".equals(product.getOriginalCategory())) {
            BigDecimal discounted = product.getOriginalPrice().multiply(new BigDecimal("0.90"));
            product.setFinalRoundedPrice(discounted);
        } else {
            // Keep original price, but ensure it's rounded exactly as required.
            product.setFinalRoundedPrice(product.getOriginalPrice());
        }
    }
}