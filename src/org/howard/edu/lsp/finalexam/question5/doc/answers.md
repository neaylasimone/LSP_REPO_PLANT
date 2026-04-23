Heuristic 1:
Name: A class should capture one and only one key abstraction
Each class should represent one clear concept. Do not combine unrelated responsibilities into one class.
Explanation:
Improves readability because the purpose of the class is immediately clear. Improves maintainability because changes to one concept do not accidentally break unrelated behavior. This was illustrated through CRC cards in lecture — each card represents exactly one class with its own specific responsibilities and collaborators, making it easy to see if a class is doing too much.
Heuristic 2:
Name: Minimize the public interface of a class
Only expose methods that outside classes truly need. Implementation details should be private.
Explanation:
Improves readability by making it clear what a class is meant to do from the outside. Improves maintainability because internal implementation can change without breaking other classes that depend on it. This connects to CRC cards — the Responsibilities column only lists what the class owes to the outside world, not how it does it internally.
Heuristic 3:
Name: Keep related data and behavior together
A class should own both its data and the methods that operate on that data. Do not scatter related logic across multiple classes.
Explanation:
Improves readability because everything about an object lives in one place. Improves maintainability because updates only need to happen in one class. This was demonstrated through OOP principles in lecture — an object should be responsible for its own state and behavior, which is exactly what CRC card design enforces when assigning responsibilities to classes.