package org.howard.edu.lsp.assignment3;

/**
 * Name: Odunayo Oluokun
 * A transformation step applied to a Product.
 * This enables polymorphism: different concrete transformations share the same interface.
 */
public interface Transformation {

    /**
     * Applies the transformation to the provided product (in-place).
     *
     * @param product product to transform
     */
    void apply(Product product);
}