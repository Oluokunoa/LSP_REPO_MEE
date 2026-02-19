package org.howard.edu.lsp.assignment3;

/**
 * Name: Odunayo Oluokun
 * Represents the outcome of parsing a CSV row into a Product.
 */
public class ProductParseResult {

    private final boolean valid;
    private final Product product;

    private ProductParseResult(boolean valid, Product product) {
        this.valid = valid;
        this.product = product;
    }

    /** @return a valid parse result wrapping the product */
    public static ProductParseResult ok(Product product) {
        return new ProductParseResult(true, product);
    }

    /** @return an invalid parse result */
    public static ProductParseResult invalid() {
        return new ProductParseResult(false, null);
    }

    /** @return true if parsing succeeded and the row should be transformed */
    public boolean isValid() {
        return valid;
    }

    /** @return parsed Product; null if invalid */
    public Product getProduct() {
        return product;
    }
}