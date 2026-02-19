package org.howard.edu.lsp.assignment3;

/**
 * Name: Odunayo Oluokun
 * Transformation #1: Convert all product names to UPPERCASE.
 */
public class UppercaseNameTransformation implements Transformation {

    /** Applies uppercase conversion to the product name. */
    @Override
    public void apply(Product product) {
        product.setName(product.getName().toUpperCase());
    }
}