package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Name: Odunayo Oluokun
 * Transformation #3:
 * If final rounded price is strictly > 500.00 AND original category was "Electronics",
 * set category to "Premium Electronics".
 */
public class PremiumElectronicsCategoryTransformation implements Transformation {

    /** Applies Premium Electronics categorization rule. */
    @Override
    public void apply(Product product) {
        boolean originalWasElectronics = "Electronics".equals(product.getOriginalCategory());
        if (!originalWasElectronics) {
            return;
        }

        BigDecimal price = product.getFinalRoundedPrice();
        if (price.compareTo(new BigDecimal("500.00")) > 0) {
            product.setCategory("Premium Electronics");
        }
    }
}