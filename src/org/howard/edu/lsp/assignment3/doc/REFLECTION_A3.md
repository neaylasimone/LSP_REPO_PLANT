# Assignment 3 Reflection – Object-Oriented Redesign

## Overview

In Assignment 2, I implemented the ETL pipeline using a single class (`ETLPipeline`) that handled file input, parsing, transformation logic, error handling, output writing, and summary reporting all within one file. While the program functioned correctly, it combined multiple responsibilities into one large method.

In Assignment 3, I redesigned the program using object-oriented decomposition. The functionality remains exactly the same — same inputs, same outputs, same transformation rules, same rounding behavior, same error handling, and same relative file paths — but the structure of the code is significantly improved.

---

## What Is Different About the Design?

In Assignment 2:
- All logic existed inside one class.
- The `main()` method contained parsing, transformation, file writing, and summary logic.
- Business logic and file I/O were tightly coupled.

In Assignment 3:
- Responsibilities are separated across three classes:
  - `ProductPipelineApp` (entry point and coordinator)
  - `CSVProductETL` (extract and load responsibilities)
  - `ProductTransformer` (business transformation logic)
- `main()` now only initializes objects and executes the pipeline.
- File handling and transformation logic are clearly separated.

The new structure reduces coupling and improves clarity. Each class now has a single, focused responsibility.

---

## How Assignment 3 Is More Object-Oriented

### 1. Objects and Classes

The redesign models the system using separate classes with clear responsibilities:
- `ProductPipelineApp` represents the application controller.
- `CSVProductETL` represents the ETL execution process.
- `ProductTransformer` represents the transformation rules.

Instead of procedural logic inside one method, the program now operates through interacting objects.

---

### 2. Encapsulation

Encapsulation is demonstrated by isolating transformation logic inside `ProductTransformer`.  
File parsing and row-skipping rules are encapsulated inside `CSVProductETL`.

This means:
- Transformation rules can change without modifying file I/O code.
- File-handling logic can change without modifying transformation rules.

Each class protects its own responsibilities and exposes only necessary methods.

---

### 3. Separation of Concerns

Assignment 3 clearly separates:
- Coordination (ProductPipelineApp)
- File input/output and validation (CSVProductETL)
- Business logic and transformation rules (ProductTransformer)

In Assignment 2, these concerns were mixed together. The new structure improves readability and maintainability.

---

### 4. Improved Maintainability

If new transformation rules were added in the future, they would only require changes inside `ProductTransformer`.  
If the file format changed, only `CSVProductETL` would need modification.

The redesign makes the system easier to extend and modify without breaking unrelated functionality.

---

## How I Used Generative AI

I used a generative AI assistant to brainstorm how to decompose the original single-class solution into multiple classes. The AI suggested separating:

- A main application class
- A file-handling class
- A transformation class

I reviewed and adjusted the suggestions to ensure:
- The transformation order exactly matches Assignment 2.
- Round-half-up behavior is preserved.
- Row-skipping rules remain identical.
- Relative file paths remain unchanged.
- Summary output format remains exactly the same.

I verified every rule against the original Assignment 2 specification before finalizing the design.

---

## How I Tested That Assignment 3 Matches Assignment 2

To ensure functional equivalence, I tested the following cases:

### 1. Robust Sample Input
I used the same sample file from Assignment 2 that includes:
- Blank rows
- Rows with too few fields
- Rows with too many fields
- Invalid ProductID values
- Invalid Price values

The output file from Assignment 3 exactly matched the expected output from Assignment 2.

---

### 2. Empty Input (Header Only)

When `products.csv` contains only the header row:
- The program still creates `data/transformed_products.csv`.
- The output file contains only the header row.
- Summary counts are correct.

---

### 3. Missing Input File

When `data/products.csv` does not exist:
- The program prints a clear error message.
- The program exits cleanly.
- No stack trace is printed.

This confirms that Assignment 3 preserves the required error-handling behavior.

---

## Conclusion

Assignment 3 improves the structure of the program without changing its functionality. The redesign demonstrates object-oriented principles such as encapsulation, separation of concerns, and modular design. The system is now easier to understand, maintain, and extend, while still producing identical results to Assignment 2.
