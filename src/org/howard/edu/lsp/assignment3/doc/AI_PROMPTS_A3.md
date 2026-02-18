# AI_PROMPTS

This document lists the prompts I used with a generative AI assistant while redesigning Assignment 2 into a more object-oriented implementation for Assignment 3. I reviewed all responses carefully and modified the suggestions to ensure the final implementation strictly followed the Assignment 2 specification.

---

## Prompt 1

**Prompt:**
How can I redesign my single-class Java ETL pipeline into a more object-oriented structure without changing its behavior?

**AI Response (Excerpt):**
You can separate responsibilities into multiple classes such as:
- A main application class
- A class responsible for file input/output
- A class responsible for transformation logic

This would improve separation of concerns and make your code easier to maintain.

**How I Used It:**
I adopted this suggestion and created three classes:
- `ProductPipelineApp` (entry point)
- `CSVProductETL` (file reading, writing, and row-skipping rules)
- `ProductTransformer` (all transformation rules)

I ensured that this structural change did not modify any functional behavior.

---

## Prompt 2

**Prompt:**
How do I make sure my transformation order remains exactly the same when separating it into another class?

**AI Response (Excerpt):**
Keep all transformation rules inside a single method in the correct order. Clearly document the order and avoid splitting dependent rules across multiple locations.

**How I Used It:**
In `ProductTransformer`, I kept all five required transformation steps inside one `transform()` method in the exact order:
1. Uppercase name  
2. Electronics discount  
3. Round HALF_UP to two decimals  
4. Premium Electronics update  
5. PriceRange assignment  

This guarantees identical behavior to Assignment 2.

---

## Prompt 3

**Prompt:**
Where should I handle row-skipping logic in an object-oriented redesign?

**AI Response (Excerpt):**
Row validation and parsing should live in the file-handling class. Business logic should remain separate.

**How I Used It:**
I placed all row-skipping rules inside `CSVProductETL`. This includes:
- Blank line detection
- Incorrect field count
- Invalid ProductID parsing
- Invalid Price parsing

This keeps transformation logic independent from input validation.

---

## Prompt 4

**Prompt:**
How can I confirm my Assignment 3 redesign produces the same results as Assignment 2?

**AI Response (Excerpt):**
Use the same test cases as Assignment 2, including malformed rows and edge cases. Compare outputs directly to ensure they match exactly.

**How I Used It:**
I tested:
- The robust sample input with invalid rows
- Header-only input
- Missing input file

I verified that:
- Output file contents match exactly
- Summary counts are correct
- Error handling behavior remains identical

---

## Prompt 5

**Prompt:**
What object-oriented concepts does this redesign demonstrate?

**AI Response (Excerpt):**
The redesign demonstrates:
- Encapsulation (separating transformation logic)
- Separation of concerns
- Modular design
- Improved maintainability

**How I Used It:**
I structured my reflection to explain:
- How Assignment 2 mixed responsibilities
- How Assignment 3 separates them
- Why this improves clarity and maintainability
