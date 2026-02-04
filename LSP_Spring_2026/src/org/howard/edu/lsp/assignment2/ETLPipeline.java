package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {
	/**
	 * Name: Odunayo Oluokun
	 * @param args
	 */

    private static final String INPUT_PATH  = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {

        File inputFile = new File(INPUT_PATH);

        // Case C: Missing input file
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("ERROR: Input file not found at relative path: " + INPUT_PATH);
            System.out.println("Program exited without processing.");
            return; // clean exit, no stack trace
        }

        int rowsRead = 0;        // non-header lines encountered (including bad ones)
        int rowsSkipped = 0;     // invalid rows
        int rowsTransformed = 0; // rows written to output

        // Ensure output directory exists
        File outputFile = new File(OUTPUT_PATH);
        File outDir = outputFile.getParentFile();
        if (outDir != null && !outDir.exists()) {
            outDir.mkdirs();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            // Read header
            String header = br.readLine();

            // If file is truly empty (no header line), treat as "header only" output requirement:
            // still create output with the required header.
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            if (header == null) {
                // Empty file: output already created header-only; print summary and exit.
                printSummary(rowsRead, rowsTransformed, rowsSkipped, outputFile.getPath());
                return;
            }

            // Process lines after header
            String line;
            while ((line = br.readLine()) != null) {

                // Skip blank lines (but count as read because encountered as a non-header line)
                if (line.trim().isEmpty()) {
                    rowsRead++;
                    rowsSkipped++;
                    continue;
                }

                rowsRead++;

                // Split by comma; fields won't contain commas or quotes (per spec)
                String[] parts = line.split(",", -1); // keep empty fields if any
                if (parts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                String productIdStr = parts[0].trim();
                String name = parts[1].trim();
                String priceStr = parts[2].trim();
                String categoryOriginal = parts[3].trim();

                Integer productId = tryParseInt(productIdStr);
                BigDecimal price = tryParseBigDecimal(priceStr);

                if (productId == null || price == null) {
                    rowsSkipped++;
                    continue;
                }


                // 1) Convert all product names to UPPERCASE.
                String nameUpper = name.toUpperCase();

                // 2) If category is "Electronics", apply 10% discount to the price.
                BigDecimal transformedPrice = price;
                boolean originalWasElectronics = "Electronics".equals(categoryOriginal);
                if (originalWasElectronics) {
                    transformedPrice = transformedPrice.multiply(new BigDecimal("0.90"));
                }

                // Round HALF_UP to exactly 2 decimal places (required).
                BigDecimal finalRoundedPrice = transformedPrice.setScale(2, RoundingMode.HALF_UP);

                // 3) If final rounded price > 500.00 AND original category was "Electronics",
                //    change category to "Premium Electronics".
                String finalCategory = categoryOriginal;
                if (originalWasElectronics && finalRoundedPrice.compareTo(new BigDecimal("500.00")) > 0) {
                    finalCategory = "Premium Electronics";
                }

                // 4) Add PriceRange based on final rounded price.
                String priceRange = determinePriceRange(finalRoundedPrice);

                
                // Output order: ProductID, Name, Price, Category, PriceRange
                bw.write(productId + "," + nameUpper + "," + formatTwoDecimals(finalRoundedPrice)
                        + "," + finalCategory + "," + priceRange);
                bw.newLine();

                rowsTransformed++;
            }

        } catch (IOException e) {
            // Graceful-ish: not requested explicitly, but avoid stack trace.
            System.out.println("ERROR: An I/O error occurred while processing files.");
            System.out.println("Details: " + e.getMessage());
            return;
        }

        printSummary(rowsRead, rowsTransformed, rowsSkipped, new File(OUTPUT_PATH).getPath());
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
            // BigDecimal(String) is exact for decimal strings; avoids double issues.
            return new BigDecimal(s);
        } catch (Exception ex) {
            return null;
        }
    }

    private static String determinePriceRange(BigDecimal finalRoundedPrice) {
        // Use final rounded price for thresholds (required)
        if (finalRoundedPrice.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (finalRoundedPrice.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (finalRoundedPrice.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }

    private static String formatTwoDecimals(BigDecimal bd) {
        // Always write exactly two decimals
        return bd.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private static void printSummary(int rowsRead, int rowsTransformed, int rowsSkipped, String outputPath) {
        System.out.println("=== ETL Run Summary ===");
        System.out.println("Rows read (non-header lines encountered): " + rowsRead);
        System.out.println("Rows transformed (written): " + rowsTransformed);
        System.out.println("Rows skipped: " + rowsSkipped);
        System.out.println("Output file written to: " + outputPath);
    }
}
