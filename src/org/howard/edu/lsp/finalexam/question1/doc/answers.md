Part 1:
Shared Resource #1: nextId — the integer counter shared across all threads
Shared Resource #2: requests — the ArrayList shared across all threads

Concurrency Problem: Race condition. Two threads can read and write to
nextId or requests simultaneously, resulting in duplicate IDs or corrupted
list state.

Why addRequest() is unsafe: It calls getNextId() and requests.add() as
separate non-atomic steps. Another thread can interleave between those
steps, causing two threads to get the same ID or corrupting the ArrayList
since it is not thread-safe.

Part 2:
Fix A: Incorrect. Synchronizing getNextId() only protects the ID counter.
The requests.add() call inside addRequest() is still unsynchronized, so
two threads can still corrupt the ArrayList concurrently.

Fix B: Correct. Synchronizing addRequest() locks the entire method so
both getNextId() and requests.add() execute as one atomic unit. No two
threads can interleave inside addRequest(), preventing duplicate IDs and
concurrent ArrayList writes.

Fix C: Incorrect. Synchronizing getRequests() only locks the read method.
It does nothing to protect nextId or requests during write operations.
The race condition in addRequest() is completely unaddressed.

Part 3:
No. According to Riel's heuristics, a class should minimize its public
interface and not expose implementation details. getNextId() is an internal
helper used only by addRequest(). Making it public breaks encapsulation,
allows external code to manipulate ID generation out of context, and leaks
implementation details. It should be private.

Part 4:
Description:
Instead of using the synchronized keyword, a ReentrantLock from
java.util.concurrent.locks can be used. You explicitly call lock() before
the critical section and unlock() in a finally block, guaranteeing the
lock is always released even if an exception occurs. This gives the same
mutual exclusion as synchronized but with more explicit control.

Code Snippet:
private final ReentrantLock lock = new ReentrantLock();

public void addRequest(String studentName) {
    lock.lock();
    try {
        int id = getNextId();
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    } finally {
        lock.unlock();
    }
}