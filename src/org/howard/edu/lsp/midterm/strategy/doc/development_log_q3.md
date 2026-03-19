# Development Log - Question 3

**Package:** org.howard.edu.lsp.midterm.strategy.doc

## External Resources Used

### AI Tool: Claude (Anthropic)

**Prompt 1:**
"How does the Strategy Pattern work in Java? What files do I need?"

**Response:**
Claude explained that the Strategy Pattern requires one interface defining the
common behavior and one concrete class per strategy implementation. The context
class holds a reference to the interface and delegates behavior to whichever
concrete strategy is currently set. It clarified that each class must be in its
own .java file and that the context class should accept the strategy through its
constructor or a setter method.

---

**Prompt 2:**
"Why is using if/else chains to select behavior considered a design problem in Java?"

**Response:**
Claude explained that if/else or chained if blocks violate the Open/Closed
Principle because adding new behavior requires modifying existing code. It also
noted that string-based dispatch is fragile since typos silently return wrong
results with no compile-time error. The Strategy Pattern solves both problems
by moving each behavior into its own class.

---

**Prompt 3:**
"How do I allow a Java class to switch strategies at runtime?"

**Response:**
Claude suggested adding a `setStrategy(PricingStrategy strategy)` method to
the context class alongside the constructor injection. This allows the same
`PriceCalculator` instance to be reused with different strategies, which was
used in the Driver class to demonstrate all four pricing strategies with a
single calculator instance.