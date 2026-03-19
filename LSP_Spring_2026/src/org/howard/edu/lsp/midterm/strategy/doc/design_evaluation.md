# Design Evaluation of PriceCalculator

The current PriceCalculator design is difficult to maintain because all pricing rules are embedded in one method using multiple conditional checks. As the system evolves, every new customer type or discount rule requires modifying the same class, which increases the likelihood of errors and makes the class harder to understand.

The design is also not easily extensible. It does not separate the concept of "how price is calculated" from the calculator itself. A better design would encapsulate each discount rule in its own class and allow the calculator to delegate pricing behavior to interchangeable strategy objects. This improves flexibility, readability, and maintainability.