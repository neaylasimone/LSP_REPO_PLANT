package org.howard.edu.lsp.assignment3;

/**
 * Assignment 3 – Object-Oriented ETL Pipeline
 *
 * ProductPipelineApp serves as the entry point for the redesigned ETL system.
 * It coordinates the Extract–Transform–Load process but does not directly
 * perform parsing, transformation logic, or file writing.
 *
 * Responsibilities:
 * - Initialize ETL components
 * - Execute the pipeline
 * - Print the required run summary
 *
 * Functional behavior remains identical to Assignment 2.
 */
public class ProductPipelineApp {

    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    public static void main(String[] args) {

        CSVProductETL etl = new CSVProductETL(
                INPUT_PATH,
                OUTPUT_PATH,
                new ProductTransformer()
        );

        CSVProductETL.RunSummary summary = etl.run();

        System.out.println("Rows read: " + summary.rowsRead);
        System.out.println("Rows transformed: " + summary.rowsWritten);
        System.out.println("Rows skipped: " + summary.rowsSkipped);

        if (summary.outputPathWritten != null) {
            System.out.println("Output file written to: " + summary.outputPathWritten);
        }
    }
}
