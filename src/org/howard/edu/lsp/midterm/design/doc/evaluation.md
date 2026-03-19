# OrderProcessor Design Evaluation

**Package:** org.howard.edu.lsp.midterm.design.doc

## Overview

The `OrderProcessor` class suffers from several significant object-oriented design
violations. Each issue is described below.

---

## Issue 1: Poor Encapsulation (Public Fields)

All four data fields — `customerName`, `email`, `item`, and `price` — are declared
`public`. This means any external class can read or modify them directly without
any validation or control. Good OO design requires that fields be kept private and
accessed only through methods, so the class can enforce rules about its own data.
This violates Riel's heuristic that data should be hidden inside its class and only
exposed through a well-defined interface.

---

## Issue 2: Single Class Does Too Many Things (Low Cohesion / SRP Violation)

The `processOrder()` method handles six distinct responsibilities in one place:

1. Tax calculation
2. Printing a receipt to the console
3. Saving the order to a file
4. Sending a confirmation email
5. Applying a discount
6. Logging the transaction

A well-designed class should have a single, clearly defined purpose. When one class
does everything, it becomes difficult to maintain, test, or extend. For example,
changing how orders are saved to a file requires modifying the same class that handles
email and discounts — unrelated concerns that should be separated.

---

## Issue 3: No Separation of Concerns

File I/O, business logic (tax, discounts), presentation (receipt printing), and
notifications (email) are all tangled together inside one method. These are fundamentally
different concerns that change for different reasons. If the tax rate changes, the
discount rule changes, or the receipt format changes, all edits happen in the same
method, increasing the risk of introducing bugs.

---

## Issue 4: Discount Applied After Total Is Already Printed

The discount logic runs *after* the receipt has already been printed and the order
has already been saved to the file. This means the customer sees one total on their
receipt but a different (discounted) total is calculated afterward and never displayed
or saved correctly. This is a logical error caused by poor ordering of responsibilities
within a single overloaded method.

---

## Issue 5: No Abstraction or Reusability

Because all behavior is hardcoded into one class, nothing is reusable. There is no
way to swap out the file-saving mechanism for a database, or replace console-based
email simulation with a real email service, without rewriting the entire class.
Good OO design uses abstraction so that components can be replaced independently.

---

## Issue 6: Missing Class-Level Data Representation (God Class Tendency)

`OrderProcessor` acts as both a data holder (storing customer and order info) and
a controller (processing, printing, saving, emailing). Riel's heuristics warn against
"God classes" that know too much and do too much. The order data itself (customer,
item, price) should be encapsulated in its own class, and the processing logic should
be separate.

---

## Summary

| Issue | Problem |
|---|---|
| Public fields | Breaks encapsulation |
| One method does everything | Low cohesion, hard to maintain |
| Mixed concerns | Unrelated logic tangled together |
| Discount after receipt | Logical correctness error |
| No abstraction | Nothing is reusable or replaceable |
| God class tendency | Data and behavior collapsed into one place |