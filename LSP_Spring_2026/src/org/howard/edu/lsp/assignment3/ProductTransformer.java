package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Name: Odunayo Oluokun
 * Applies a sequence of transformations to a Product.
 * This is the "Transform" stage coordinator.
 */
public class ProductTransformer {

    private final List<Transformation> steps;

    /**
     * Constructs a transformer with ordered steps (order matters per spec).
     *
     * @param steps list of transformations in execution order
     */
    public ProductTransformer(List<Transformation> steps) {
        this.steps = steps;
    }

    /**
     * Applies all transformations to the product, in order.
     *
     * @param product product to transform
     */
    public void applyAll(Product product) {
        for (Transformation t : steps) {
            t.apply(product);
        }
    }
}