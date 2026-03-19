# Development Log - Question 2

**Package:** org.howard.edu.lsp.midterm.design.doc

## External Resources Used

### AI Tool: Claude (Anthropic)

**Prompt 1:**
"What are common object-oriented design violations when a single class handles
data storage, file I/O, business logic, and notifications all at once?"

**Response:**
Claude outlined several issues including low cohesion, violation of the Single
Responsibility Principle, and Riel's heuristic against God classes that know
and do too much. This helped structure the evaluation into clearly separated,
well-explained issues rather than a vague general critique.

---

**Prompt 2:**
"In the OrderProcessor class, the discount is applied after the receipt is already
printed and saved. Is this a design issue worth mentioning?"

**Response:**
Claude confirmed this is a logical correctness problem caused by poor ordering
of responsibilities within a single overloaded method. It noted that separating
concerns into dedicated classes would eliminate this class of bug entirely, since
each class would only run its own logic at the appropriate time.

---

**Prompt 3:**
"How do I write CRC cards in Markdown format for a redesigned order processing system?"

**Response:**
Claude explained the Class-Responsibility-Collaborator format and helped identify
the natural class boundaries for the redesign: Order (data), TaxCalculator,
DiscountCalculator, ReceiptPrinter, OrderRepository, EmailNotifier, ActivityLogger,
and OrderProcessor as coordinator. It emphasized that collaborators should only
be listed where a genuine dependency exists.