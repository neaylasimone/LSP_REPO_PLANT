# PriceCalculator Design Evaluation

**Package:** org.howard.edu.lsp.midterm.strategy.doc

## Current Design Problems

### 1. Open/Closed Principle Violation
The `PriceCalculator` class must be directly modified every time a new customer
type is added. For example, adding a "STUDENT" discount requires opening this class
and adding another `if` block. A well-designed system should be open for extension
but closed for modification — new behavior should be addable without touching
existing, working code.

### 2. Grows Unboundedly (Scalability Problem)
Every new pricing rule adds another `if` statement to the same method. As the
business adds more customer types or seasonal promotions, this method becomes
longer and harder to read, test, and maintain. There is no natural stopping point.

### 3. No Reusability
Each discount rule is buried inside a conditional branch. There is no way to reuse,
share, or independently test a single pricing rule without invoking the entire method.

### 4. Single Class Bears All Responsibility
`PriceCalculator` owns every pricing strategy simultaneously. If two developers
need to add different discount types at the same time, they will conflict on edits
to the same file. Separating strategies into their own classes eliminates this problem.

### 5. String-Based Dispatch Is Fragile
Behavior is selected by comparing raw strings like "VIP" or "HOLIDAY". A typo in
the caller silently returns the wrong price (the original price) with no error.
This is error-prone and provides no compile-time safety.

## Recommended Fix
Apply the **Strategy Pattern**: define a common pricing interface and implement
each discount rule as its own class. `PriceCalculator` then delegates to whichever
strategy is injected, with no conditionals required.