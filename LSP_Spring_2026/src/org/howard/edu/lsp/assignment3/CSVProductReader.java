package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
	 * Name: Odunayo Oluokun
 * CSV reader for product input file using relative paths.
 */
public class CSVProductReader {

    private final String inputPath;
    private BufferedReader reader;

    /**
     * Creates a reader for the given path.
     *
     * @param inputPath relative path to input CSV
     */
    public CSVProductReader(String inputPath) {
        this.inputPath = inputPath;
    }

    /** Opens the underlying file reader. */
    public void open() {
        try {
            this.reader = new BufferedReader(new FileReader(inputPath));
        } catch (IOException e) {
            // Handled at higher level in a "no stack trace" way by preventing entry;
            // If something happens here, we still keep it safe.
            this.reader = null;
        }
    }

    /**
     * Reads the header line (first row) from the CSV.
     *
     * @return header line, or null if file could not be read / empty
     */
    public String readHeader() {
        if (reader == null) {
            return null;
        }
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Reads the next line from the CSV.
     *
     * @return next line, or null at EOF / error
     */
    public String readLine() {
        if (reader == null) {
            return null;
        }
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    /** Closes the reader. */
    public void close() {
        if (reader == null) {
            return;
        }
        try {
            reader.close();
        } catch (IOException e) {
            // no-op (graceful)
        }
    }
}