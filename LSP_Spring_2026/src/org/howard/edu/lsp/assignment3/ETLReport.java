package org.howard.edu.lsp.assignment3;

/**
	 * Name: Odunayo Oluokun
 * Tracks run statistics for the ETL pipeline.
 */
public class ETLReport {
    private int rowsRead;
    private int rowsTransformed;
    private int rowsSkipped;

    /** Creates an empty report. */
    public ETLReport() {
        this.rowsRead = 0;
        this.rowsTransformed = 0;
        this.rowsSkipped = 0;
    }

    /** Increments the count of non-header lines encountered. */
    public void incrementRowsRead() {
        rowsRead++;
    }

    /** Increments the count of rows written to output. */
    public void incrementRowsTransformed() {
        rowsTransformed++;
    }

    /** Increments the count of rows skipped due to validation/parsing errors. */
    public void incrementRowsSkipped() {
        rowsSkipped++;
    }

    /** @return number of rows read (non-header lines encountered) */
    public int getRowsRead() {
        return rowsRead;
    }

    /** @return number of rows transformed (written to output) */
    public int getRowsTransformed() {
        return rowsTransformed;
    }

    /** @return number of rows skipped */
    public int getRowsSkipped() {
        return rowsSkipped;
    }
}