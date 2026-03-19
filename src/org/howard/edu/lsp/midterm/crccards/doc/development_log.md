# Development Log

**Package:** org.howard.edu.lsp.midterm.crccards.doc

## External Resources Used

### AI Tool: Claude (Anthropic)

**Question 1 - Task Management System**

Prompt: "What Java data structure is best for storing objects by a unique string key
while preventing duplicates?"

Response: Claude suggested using a `HashMap` or `LinkedHashMap`, explaining that
both prevent duplicate keys natively. `LinkedHashMap` was recommended if insertion
order needed to be preserved, which was useful for returning tasks in the order
they were added.

---

**Question 2 - Design Evaluation**

Prompt: "What are common object-oriented design violations when a single class
handles data storage, file I/O, business logic, and notifications all at once?"

Response: Claude outlined issues including low cohesion, violation of the Single
Responsibility Principle, and Riel's heuristic against God classes. This helped
structure the evaluation into clearly separated issues.

---

**Question 3 - Strategy Pattern**

Prompt: "How does the Strategy Pattern work in Java? What files do I need?"

Response: Claude explained that the Strategy Pattern requires one interface and
one concrete class per behavior. It clarified that each class must be in its own
.java file and that the context class (PriceCalculator) should accept the strategy
through its constructor or a setter method.