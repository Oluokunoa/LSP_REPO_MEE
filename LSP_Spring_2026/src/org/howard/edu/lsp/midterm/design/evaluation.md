# Evaluation of OrderProcessor Design

The `OrderProcessor` class has several object-oriented design problems. First, it has poor encapsulation because the data fields `customerName`, `email`, `item`, and `price` are public. This allows any other part of the program to modify the object's state directly without validation or control, which weakens data integrity.

Second, the class violates the idea of focused responsibility because it performs many unrelated tasks inside one method. The `processOrder()` method calculates tax, prints a receipt, writes to a file, simulates sending an email, applies a discount, and logs activity. These responsibilities belong to different concerns and should not all be handled by one class.

Third, the design is hard to maintain and extend. If the business changes the tax rule, receipt format, discount rule, storage mechanism, or email behavior, the same class must be edited repeatedly. This creates tight coupling between business logic, persistence, communication, and logging.

The design also makes testing difficult. Since file writing, console printing, and email messaging are mixed into the same method, it is not easy to test one behavior in isolation. A better design would separate the order data from services such as pricing, receipt generation, file storage, email notification, and logging.

Overall, the class has low cohesion and too many responsibilities. A better object-oriented structure would distribute work across several collaborating classes, each with a clear and limited purpose.