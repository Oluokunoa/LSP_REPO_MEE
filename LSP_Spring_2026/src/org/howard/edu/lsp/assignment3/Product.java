package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Name: Odunayo Oluokun
 * Represents a product record moving through the ETL pipeline.
 * Encapsulates parsing outcomes and transformation results.
 */
public class Product {

    private final int productId;
    private String name;
    private final BigDecimal originalPrice;
    private BigDecimal finalRoundedPrice;
    private final String originalCategory;
    private String category;
    private String priceRange;

    /**
     * Constructs a Product.
     *
     * @param productId product identifier
     * @param name product name
     * @param price product price (unrounded original input value)
     * @param category product category (trimmed)
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.originalPrice = price;
        this.originalCategory = category;
        this.category = category;

        // Initialize finalRoundedPrice as the rounded original price; transformations may update it.
        this.finalRoundedPrice = roundHalfUp(price);
        this.priceRange = "";
    }

    /** @return product ID */
    public int getProductId() {
        return productId;
    }

    /** @return current (possibly transformed) name */
    public String getName() {
        return name;
    }

    /** Sets the current name. */
    public void setName(String name) {
        this.name = name;
    }

    /** @return original price as parsed */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /** @return original category as parsed (before any changes) */
    public String getOriginalCategory() {
        return originalCategory;
    }

    /** @return current (possibly transformed) category */
    public String getCategory() {
        return category;
    }

    /** Sets the current category. */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return final rounded price (two decimals, HALF_UP),
     * used for Premium Electronics and PriceRange decisions.
     */
    public BigDecimal getFinalRoundedPrice() {
        return finalRoundedPrice;
    }

    /**
     * Sets finalRoundedPrice (will be normalized to 2 decimals HALF_UP).
     *
     * @param price new price (unrounded or rounded)
     */
    public void setFinalRoundedPrice(BigDecimal price) {
        this.finalRoundedPrice = roundHalfUp(price);
    }

    /** @return current PriceRange label */
    public String getPriceRange() {
        return priceRange;
    }

    /** Sets the PriceRange label. */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    /**
     * Formats the final rounded price with exactly two decimals.
     *
     * @return price string (e.g., "23.00")
     */
    public String formatPriceTwoDecimals() {
        return finalRoundedPrice.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private static BigDecimal roundHalfUp(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}