package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CSVProductETL manages the Extract and Load phases of the ETL pipeline.
 *
 * Responsibilities:
 * - Read input data from data/products.csv
 * - Apply row-skipping rules (blank rows, malformed rows, invalid IDs/prices)
 * - Write transformed results to data/transformed_products.csv
 * - Track run summary metrics
 *
 * Transformation logic is delegated to ProductTransformer.
 * Behavior matches Assignment 2 exactly, including required
 * file paths, rounding rules, error handling, and summary output.
 */
public class CSVProductETL {

    private final String inputPath;
    private final String outputPath;
    private final ProductTransformer transformer;

    public static class RunSummary {
        public int rowsRead;
        public int rowsWritten;
        public int rowsSkipped;
        public String outputPathWritten;
    }

    public CSVProductETL(String inputPath, String outputPath, ProductTransformer transformer) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.transformer = transformer;
    }

    public RunSummary run() {

        RunSummary summary = new RunSummary();

        File inputFile = new File(inputPath);

        if (!inputFile.exists()) {
            System.out.println("Error: Input file not found at " + inputPath);
            return summary;
        }

        new File("data").mkdirs();

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))
        ) {

            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            String header = reader.readLine();

            if (header == null) {
                summary.outputPathWritten = outputPath;
                return summary;
            }

            String line;

            while ((line = reader.readLine()) != null) {

                summary.rowsRead++;

                line = line.trim();

                if (line.isEmpty()) {
                    summary.rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",", -1);

                if (fields.length != 4) {
                    summary.rowsSkipped++;
                    continue;
                }

                String idStr = fields[0].trim();
                String name = fields[1].trim();
                String priceStr = fields[2].trim();
                String category = fields[3].trim();

                int productId;

                try {
                    productId = Integer.parseInt(idStr);
                } catch (NumberFormatException e) {
                    summary.rowsSkipped++;
                    continue;
                }

                try {

                    ProductTransformer.TransformedRow result =
                            transformer.transform(productId, name, priceStr, category);

                    writer.write(result.toCsvRow());
                    writer.newLine();

                    summary.rowsWritten++;

                } catch (NumberFormatException e) {
                    summary.rowsSkipped++;
                }
            }

            summary.outputPathWritten = outputPath;

        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }

        return summary;
    }
}
