# Proposed Improved CRC Design

## Class:
Order

## Responsibilities:
- Store order data such as customer name, email, item, and base price
- Provide access to order information
- Represent a single customer purchase

## Collaborators:
PricingService, ReceiptPrinter, OrderRepository, EmailService, AuditLogger

---

## Class:
PricingService

## Responsibilities:
- Calculate tax
- Apply discount rules
- Compute the final total price

## Collaborators:
Order

---

## Class:
ReceiptPrinter

## Responsibilities:
- Format and print the receipt for an order
- Display customer, item, and total information

## Collaborators:
Order, PricingService

---

## Class:
OrderRepository

## Responsibilities:
- Save processed order data to persistent storage
- Isolate file-writing or database-writing logic from the rest of the system

## Collaborators:
Order, PricingService

---

## Class:
EmailService

## Responsibilities:
- Send confirmation messages to customers
- Isolate communication behavior from order processing logic

## Collaborators:
Order

---

## Class:
AuditLogger

## Responsibilities:
- Log processing activity and timestamps
- Keep audit and monitoring behavior separate from business logic

## Collaborators:
Order

---

## Class:
OrderProcessor

## Responsibilities:
- Coordinate the overall order-processing workflow
- Delegate pricing, printing, saving, email, and logging to specialized collaborators

## Collaborators:
Order, PricingService, ReceiptPrinter, OrderRepository, EmailService, AuditLogger