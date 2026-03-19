# Proposed Redesign Using CRC Cards

**Package:** org.howard.edu.lsp.midterm.design.doc

---

## CRC Card 1

**Class:** Order

**Responsibilities:**
- Store customer name, email, item, and price
- Provide access to order data through getter methods
- Represent a single customer order

**Collaborators:**
- None

---

## CRC Card 2

**Class:** TaxCalculator

**Responsibilities:**
- Calculate the tax amount for a given price
- Calculate the total price including tax

**Collaborators:**
- Order

---

## CRC Card 3

**Class:** DiscountCalculator

**Responsibilities:**
- Determine whether an order qualifies for a discount
- Apply the appropriate discount to the total price

**Collaborators:**
- Order

---

## CRC Card 4

**Class:** ReceiptPrinter

**Responsibilities:**
- Format and display the order receipt to the console
- Show customer name, item, and final total

**Collaborators:**
- Order

---

## CRC Card 5

**Class:** OrderRepository

**Responsibilities:**
- Save order details to persistent storage (e.g., a file)
- Write customer name, item, and total in the required format

**Collaborators:**
- Order

---

## CRC Card 6

**Class:** EmailNotifier

**Responsibilities:**
- Send a confirmation notification to the customer's email address

**Collaborators:**
- Order

---

## CRC Card 7

**Class:** ActivityLogger

**Responsibilities:**
- Record the date and time an order was processed
- Log activity for auditing or debugging purposes

**Collaborators:**
- None

---

## CRC Card 8

**Class:** OrderProcessor

**Responsibilities:**
- Coordinate the full order processing workflow
- Delegate tax calculation, discount application, receipt printing,
  file saving, email notification, and logging to specialized classes

**Collaborators:**
- Order
- TaxCalculator
- DiscountCalculator
- ReceiptPrinter
- OrderRepository
- EmailNotifier
- ActivityLogger

---

## Design Rationale

In this redesign, each class has exactly one reason to exist and one reason to change.
`Order` holds data only. Each processing concern (tax, discount, printing, saving,
emailing, logging) is handled by its own dedicated class. `OrderProcessor` acts purely
as a coordinator, delegating to these specialists rather than implementing anything
itself. This makes each component independently testable, replaceable, and maintainable.