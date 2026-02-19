# Reflection — Assignment 3 (A3): OO Redesign of ETL Pipeline

## Overview
Assignment 2 implemented the ETL pipeline as a single procedural program with direct file I/O, parsing, transformation logic, output writing, and summary printing in one class. Assignment 3 preserves the exact same observable behavior (inputs, outputs, transformation order, row skipping rules, rounding, and error handling), but decomposes the solution into multiple classes with clearer responsibilities.

## What is different about the design?
### Assignment 2 (procedural/monolithic)
- One class performed: opening files, reading lines, validating rows, applying all transformations, writing output, and printing the summary.
- Most state (counters, intermediate values) lived inside `main`.
- Transformation rules were embedded in one control flow, making it harder to isolate and test individual steps.

### Assignment 3 (object-oriented decomposition)
The program is split into collaborating classes:
- `Product` encapsulates per-row state: productId, name, originalPrice, finalRoundedPrice, originalCategory, category, and priceRange.
- `ProductParser` implements validation + parsing according to skip rules and returns a `ProductParseResult`.
- `Transformation` interface defines a uniform operation `apply(Product)`.
- Four concrete transformations implement the spec in strict order:
  1) `UppercaseNameTransformation`
  2) `ElectronicsDiscountTransformation`
  3) `PremiumElectronicsCategoryTransformation`
  4) `PriceRangeTransformation`
- `ProductTransformer` coordinates applying a list of `Transformation` steps (polymorphism).
- `CSVProductReader` and `CSVProductWriter` encapsulate file I/O concerns.
- `ETLReport` collects counters and is printed at the end by the app.

## How is Assignment 3 more object-oriented?
- **Encapsulation:** Product-related data is grouped inside `Product`, rather than floating as local variables. The object owns both original fields and final transformed outputs.
- **Single Responsibility:** Parsing/validation, transformation, I/O, orchestration, and reporting are separated into different classes.
- **Extensibility:** Adding a new transformation becomes adding a new class implementing `Transformation` and inserting it into the ordered list, without modifying unrelated logic.

## Which OO ideas did you use?
- **Object/Class:** A CSV row is modeled as a `Product` object. The ETL process is modeled as interacting classes.
- **Encapsulation:** `Product` hides internal details (e.g., enforcing 2-decimal rounding through `setFinalRoundedPrice`).
- **Inheritance/Polymorphism:** Polymorphism is used via the `Transformation` interface. `ProductTransformer` calls `apply` uniformly across a list of different concrete transformation types.
- **Composition:** `ETLPipelineApp` composes a reader, writer, transformer, and report to implement the full pipeline.

## Confirming behavior matches Assignment 2
I validated Assignment 3 against the same test cases used in Assignment 2:
1. **Same sample input** (includes bad id, bad price, wrong field counts, and a blank line):
   - Verified the output file matches the expected “golden” transformed CSV exactly.
   - Verified skipped rows do not appear in output.
   - Verified rounding is round-half-up to two decimals.
2. **Empty input file (header only):**
   - Verified `data/transformed_products.csv` is created and contains only the header.
3. **Missing input file:**
   - Verified a clear error is printed and the program exits cleanly with no stack trace.

## Summary
Assignment 3’s primary improvement is architectural: the logic is decomposed into cohesive classes and uses polymorphism to implement the ordered transformation pipeline, while preserving the exact functional requirements and output behavior from Assignment 2.