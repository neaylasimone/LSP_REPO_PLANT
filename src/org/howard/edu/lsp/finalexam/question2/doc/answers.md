The Report abstract class defines generateReport() as a final template
method, locking in the fixed workflow: loadData, formatHeader, formatBody,
and formatFooter. The abstract methods represent the variable steps that
each subclass overrides with its own specific implementation. StudentReport
and CourseReport each provide their own versions of these steps without
altering the overall sequence. The Driver uses List<Report> to demonstrate
polymorphism, calling generateReport() on each object without knowing its
concrete type at compile time.