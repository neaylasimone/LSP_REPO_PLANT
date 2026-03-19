# CRC Card Delegation Explanation

**Package:** org.howard.edu.lsp.midterm.crccards.doc

TaskManager collaborates with Task because its responsibilities — storing tasks,
finding tasks by ID, and returning tasks by status — require it to create, hold,
and inspect Task objects. Task does not collaborate with TaskManager because its
only responsibilities are storing its own data and updating its own status, which
are fully self-contained and require no knowledge of any external collection or manager.
This reflects the principle that a class should only list collaborators it actively
depends on to fulfill its own responsibilities.