package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Name: Odunayo Oluokun
 * Transformation #4: Add PriceRange based on final rounded price.
 */
public class PriceRangeTransformation implements Transformation {

    /** Computes and sets PriceRange using final rounded price thresholds. */
    @Override
    public void apply(Product product) {
        BigDecimal p = product.getFinalRoundedPrice();

        if (p.compareTo(new BigDecimal("10.00")) <= 0) {
            product.setPriceRange("Low");
        } else if (p.compareTo(new BigDecimal("100.00")) <= 0) {
            product.setPriceRange("Medium");
        } else if (p.compareTo(new BigDecimal("500.00")) <= 0) {
            product.setPriceRange("High");
        } else {
            product.setPriceRange("Premium");
        }
    }
}