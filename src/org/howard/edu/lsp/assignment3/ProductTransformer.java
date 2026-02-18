package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ProductTransformer contains all transformation rules required
 * by the ETL specification.
 *
 * Transformations are applied in the exact required order:
 * 1. Convert product name to uppercase.
 * 2. Apply 10% discount if original category is "Electronics".
 * 3. Round price to exactly two decimals using HALF_UP.
 * 4. Change category to "Premium Electronics" if final rounded
 *    price is strictly greater than 500.00 and original category was Electronics.
 * 5. Assign PriceRange based on final rounded price.
 *
 * This class encapsulates business logic separately from
 * file input/output responsibilities.
 */
public class ProductTransformer {

    public static class TransformedRow {

        private final int productId;
        private final String name;
        private final BigDecimal price;
        private final String category;
        private final String priceRange;

        public TransformedRow(int productId, String name, BigDecimal price, String category, String priceRange) {
            this.productId = productId;
            this.name = name;
            this.price = price;
            this.category = category;
            this.priceRange = priceRange;
        }

        public String toCsvRow() {
            String priceString = price.setScale(2, RoundingMode.HALF_UP).toString();
            return productId + "," + name + "," + priceString + "," + category + "," + priceRange;
        }
    }

    public TransformedRow transform(int productId, String name, String priceStr, String category) {

        String originalCategory = category;

        String outName = name.toUpperCase();

        BigDecimal price = new BigDecimal(priceStr);

        if ("Electronics".equals(originalCategory)) {
            price = price.multiply(new BigDecimal("0.90"));
        }

        price = price.setScale(2, RoundingMode.HALF_UP);

        String outCategory = category;

        if ("Electronics".equals(originalCategory)
                && price.compareTo(new BigDecimal("500.00")) > 0) {
            outCategory = "Premium Electronics";
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

        return new TransformedRow(productId, outName, price, outCategory, priceRange);
    }
}
