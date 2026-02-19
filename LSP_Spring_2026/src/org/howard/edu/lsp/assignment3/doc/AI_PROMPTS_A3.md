# AI Prompts & Excerpts

This file documents how I used a generative AI assistant to redesign my Assignment 2 ETL pipeline into a more object-oriented solution. I reviewed and edited all suggestions to ensure the program behavior exactly matches Assignment 2’s specification.


## Prompt 1
**Prompt:**  
Suggest an object-oriented redesign of a Java ETL pipeline that reads a CSV, applies ordered transformations, skips invalid rows, and writes an output CSV. Keep behavior identical to the procedural version.

**AI Excerpt (paraphrased):**  
- Create a Product domain object
- Create a Transformation interface and separate classes per transformation step
- Create CSVReader/CSVWriter abstractions
- Create a Pipeline/App class to orchestrate extract-transform-load
- Track metrics in a report object

**My Adaptation:**  
I implemented Product + Transformation polymorphism + CSV reader/writer classes. I ensured transformation order exactly matches Assignment2 and preserved rounding/skip rules.


## Prompt 2
**Prompt:**  
How can I incorporate inheritance/polymorphism meaningfully without changing functional requirements?

**AI Excerpt (paraphrased):**  
Use an interface (or abstract base class) for transformation steps and store them in a list to apply uniformly.

**My Adaptation:**  
I created `Transformation` and four concrete classes. `ProductTransformer` applies them in a list, demonstrating polymorphism.


## Prompt 3
**Prompt:**  
What are the typical pitfalls when translating a spec-heavy procedural ETL into OO?

**AI Excerpt (paraphrased):**  
- Accidentally changing rounding order
- Using current vs original category incorrectly
- Not counting skipped rows properly
- Writing output header only when input has data

**My Adaptation:**  
I explicitly preserved:
- originalCategory stored on Product
- finalRoundedPrice stored and used for later decisions
- always writing output header
- row counters consistent with A2


## Prompt 4
**Prompt:**  
Generate Javadocs for a Product class and transformation interface.

**AI Excerpt (paraphrased):**  
Provided Javadoc templates for class/method responsibilities.

**My Adaptation:**  
I edited for accuracy and aligned comments with the assignment’s exact rules (skip rules, transform order, rounding requirements).