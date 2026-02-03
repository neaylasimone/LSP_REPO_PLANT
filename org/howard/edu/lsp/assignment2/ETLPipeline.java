package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 MY program reads product data from a CSV file, applies a series of data
 transformations, and writes the updated results to a new CSV file.

 For each row, the program:
 - Converts the product name to uppercase
 - Applies a 10% discount to Electronics items
 - Rounds prices to two decimal places
 - Updates the category to "Premium Electronics" if the discounted price exceeds $500
 - Assigns a price range label (Low, Medium, High, Premium)

 The program also tracks how many rows are read, written, and skipped,
 and prints a summary after processing.
*/

public class ETLPipeline {

    private static final String INPUT_PATH = "data/products.csv";
    private static final String OUTPUT_PATH = "data/transformed_products.csv";


    public static void main(String[] args) {
        int rowsRead = 0;
        int rowsWritten = 0;
        int rowsSkipped = 0;

        File inputFile = new File(INPUT_PATH);

        if (!inputFile.exists()) {
            System.out.println("Error: Input file not found at " + INPUT_PATH);
            return;
        }

        new File("data").mkdirs();

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_PATH))
        ) {
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            String line = reader.readLine();
            if (line == null) {
                printSummary(rowsRead, rowsWritten, rowsSkipped);
                return;
            }

            while ((line = reader.readLine()) != null) {
                rowsRead++;

                line = line.trim();
                if (line.isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",", -1);
                if (fields.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    String idStr = fields[0].trim();
                    String name = fields[1].trim();
                    String priceStr = fields[2].trim();
                    String category = fields[3].trim();

                    int productId = Integer.parseInt(idStr);
                    BigDecimal price = new BigDecimal(priceStr);

                    String originalCategory = category;

                    name = name.toUpperCase();

                    if (originalCategory.equals("Electronics")) {
                        price = price.multiply(new BigDecimal("0.90"));
                    }

                    price = price.setScale(2, RoundingMode.HALF_UP);

                    if (price.compareTo(new BigDecimal("500.00")) > 0 &&
                        originalCategory.equals("Electronics")) {
                        category = "Premium Electronics";
                    }

                    String priceRange;
                    if (price.compareTo(new BigDecimal("10.00")) <= 0) {
                        priceRange = "Low";
                    } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
                        priceRange = "Medium";
                    } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
                        priceRange = "High";
                    } else {
                        priceRange = "Premium";
                    }

                    writer.write(productId + "," +
                                 name + "," +
                                 price.toString() + "," +
                                 category + "," +
                                 priceRange);
                    writer.newLine();

                    rowsWritten++;

                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
            return;
        }

        printSummary(rowsRead, rowsWritten, rowsSkipped);
        System.out.println("Output file written to: " + OUTPUT_PATH);
    }

    private static void printSummary(int read, int written, int skipped) {
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + skipped);
    }
}
