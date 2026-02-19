package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Name: Odunayo Oluokun
 * Parses raw CSV lines into Product objects according to skip rules.
 * <p>
 * Skip any non-header row that:
 * - is blank
 * - does not contain exactly four comma-separated fields
 * - has a ProductID or Price that cannot be parsed
 */
public class ProductParser {

    /**
     * Parses a single CSV row line into a Product.
     *
     * @param line raw CSV line
     * @return parse result (valid product or invalid)
     */
    public static ProductParseResult parse(String line) {
        if (line == null || line.trim().isEmpty()) {
            return ProductParseResult.invalid();
        }

        String[] parts = line.split(",", -1);
        if (parts.length != 4) {
            return ProductParseResult.invalid();
        }

        String idStr = parts[0].trim();
        String name = parts[1].trim();
        String priceStr = parts[2].trim();
        String category = parts[3].trim();

        Integer id = tryParseInt(idStr);
        BigDecimal price = tryParseBigDecimal(priceStr);

        if (id == null || price == null) {
            return ProductParseResult.invalid();
        }

        return ProductParseResult.ok(new Product(id, name, price, category));
    }

    private static Integer tryParseInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (Exception ex) {
            return null;
        }
    }

    private static BigDecimal tryParseBigDecimal(String s) {
        try {
            return new BigDecimal(s);
        } catch (Exception ex) {
            return null;
        }
    }
}