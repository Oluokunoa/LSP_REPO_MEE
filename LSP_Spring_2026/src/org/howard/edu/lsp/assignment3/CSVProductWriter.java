package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
	 * Name: Odunayo Oluokun
 * CSV writer for transformed product output file.
 */
public class CSVProductWriter {

    private final String outputPath;
    private BufferedWriter writer;

    /**
     * Creates a writer for the given output path.
     *
     * @param outputPath relative path to output CSV
     */
    public CSVProductWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    /** Opens the underlying file writer (overwrites existing output). */
    public void open() {
        try {
            this.writer = new BufferedWriter(new FileWriter(outputPath));
        } catch (IOException e) {
            this.writer = null;
        }
    }

    /** Writes the required header row. */
    public void writeHeader() {
        if (writer == null) return;
        try {
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();
        } catch (IOException e) {
            // no-op
        }
    }

    /**
     * Writes a transformed product record using required column order.
     *
     * @param product transformed product
     */
    public void writeProduct(Product product) {
        if (writer == null) return;
        try {
            writer.write(product.getProductId()
                    + "," + product.getName()
                    + "," + product.formatPriceTwoDecimals()
                    + "," + product.getCategory()
                    + "," + product.getPriceRange());
            writer.newLine();
        } catch (IOException e) {
            // no-op
        }
    }

    /** Closes the writer. */
    public void close() {
        if (writer == null) return;
        try {
            writer.close();
        } catch (IOException e) {
            // no-op
        }
    }
}