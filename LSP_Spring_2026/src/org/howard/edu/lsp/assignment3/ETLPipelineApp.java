package org.howard.edu.lsp.assignment3;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class ETLPipelineApp {
	/**
	 * Name: Odunayo Oluokun
	
	 * Entry point for the Assignment 3 ETL pipeline.
	 * <p>
	 * Reads {@code data/products.csv}, applies transformations in the required order,
	 * and writes {@code data/transformed_products.csv}. Behavior matches Assignment 2.
	 */

    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    /**
     * Runs the ETL pipeline.
     *
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        File inputFile = new File(INPUT_PATH);

        // Missing input file (must be graceful, no stack trace)
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("ERROR: Input file not found at relative path: " + INPUT_PATH);
            System.out.println("Program exited without processing.");
            return;
        }

        File outputFile = new File(OUTPUT_PATH);
        File outDir = outputFile.getParentFile();
        if (outDir != null && !outDir.exists()) {
            outDir.mkdirs();
        }

        ETLReport report = new ETLReport();

        // Transformations (exact required order)
        List<Transformation> transformations = Arrays.asList(
                new UppercaseNameTransformation(),
                new ElectronicsDiscountTransformation(),
                new PremiumElectronicsCategoryTransformation(),
                new PriceRangeTransformation()
        );

        ProductTransformer transformer = new ProductTransformer(transformations);
        CSVProductReader reader = new CSVProductReader(INPUT_PATH);
        CSVProductWriter writer = new CSVProductWriter(OUTPUT_PATH);

        // Always write header row even if input is header-only.
        writer.open();
        writer.writeHeader();

        reader.open();

        // Read header line (not transformed)
        String header = reader.readHeader();
        if (header == null) {
            // Truly empty file: still keep header-only output.
            reader.close();
            writer.close();
            printSummary(report, outputFile);
            return;
        }

        // Extract → Transform → Load
        String line;
        while ((line = reader.readLine()) != null) {
            report.incrementRowsRead();

            ProductParseResult parsed = ProductParser.parse(line);
            if (!parsed.isValid()) {
                report.incrementRowsSkipped();
                continue;
            }

            Product product = parsed.getProduct();
            try {
                transformer.applyAll(product);
            } catch (Exception ex) {
                // Defensive: no row should crash the program; skip if anything unexpected.
                report.incrementRowsSkipped();
                continue;
            }

            writer.writeProduct(product);
            report.incrementRowsTransformed();
        }

        reader.close();
        writer.close();

        printSummary(report, outputFile);
    }

    private static void printSummary(ETLReport report, File outputFile) {
        System.out.println("=== ETL Run Summary ===");
        System.out.println("Rows read (non-header lines encountered): " + report.getRowsRead());
        System.out.println("Rows transformed (written): " + report.getRowsTransformed());
        System.out.println("Rows skipped: " + report.getRowsSkipped());
        System.out.println("Output file written to: " + outputFile.getPath());
    }
}
