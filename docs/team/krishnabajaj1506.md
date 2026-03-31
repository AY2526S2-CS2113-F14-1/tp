# Project Portfolio Page - SpendSwift

## Overview
**SpendSwift** is a desktop application for managing personal finances, optimized for Command Line Interface (CLI) users. It allows university students to track expenses, manage budgets, and analyze spending habits through a fast, text-based interface.

## Summary of Contributions
This section summarizes my specific contributions to the project, focusing on core functionality related to sorting, calculating statistics, chronological insertion, and deletion logic.

* **Code Contributed:** [Repo Dashboard Link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=krishnabajaj1506&breakdown=true)

* **Enhancements Implemented**:
  * **Sort Feature**: Implemented the ability to reorder the entire tracking list. Users can sort alphabetically by their custom categories or chronologically by date (newest first).
    * *Justification:* Gives users a powerful tool for discovering duplicate expenses or analyzing spending trends within a specific time frame.
    * *Highlights:* Engineered with an in-place `Collections.sort` leveraging static, chained Comparators (`Comparator.reverseOrder()`), guaranteeing seamless persistence of the newly sorted list and optimal efficiency.
  * **Statistics Feature**: Developed the `stats` command to instantly calculate and list a per-category monetary breakdown.
    * *Justification:* Quickly summarizing overall spending categories is the core value proposition of a budget tracker.
    * *Highlights:* Utilized a `LinkedHashMap` to maintain deterministic, intuitive insertion-order iteration, ensuring the users see a consistent report every time without relying on heavier map sorting algorithms.
  * **Chronological Insertion & Deletion**: Refactored the fundamental way `ExpenseList` handles data. Expenses are now injected directly in purely chronological (newest-first) order. Also authored the strict `DeleteCommand` ensuring comprehensive bound-checking and error-handling against corrupted manual saves.
  * **Automated Unit Testing Environment**: Independently designed and authored extensive JUnit testing suites ensuring edge-case scenarios—such as chronological anomalies or out-of-bounds index deletion inputs—fail gracefully without an application crash.

* **Contributions to the UG**:
  * Authored the comprehensive usage and parameter explanation sections for the `sort`, `list`, `stats`, and `delete` commands, ensuring non-technical users completely understand their application logic.
  * Updated the overarching Command Summary, adding warnings regarding expected date descending behaviors.

* **Contributions to the DG**:
  * Authored the "Design & Implementation" and "Design Considerations" for three full chapters: **Sort Feature**, **Statistics Feature**, and **Delete Feature**.
  * Drafted and embedded **3 highly detailed UML Sequence Diagrams** mapping the exact memory execution paths of the Parser, Commands, and UI interactions to visualize application flow.
  * Wrote manual functional testing parameters for my three core commands.

* **Contributions to Team-based Tasks**:
  * Standardized the repository's `.jar` build configurations during critical milestone releases.
  * Handled fixing cascading unit testing errors whenever overarching formatting updates were applied to expected outputs.

* **Review/Mentoring Contributions**:
  * Reviewed two major overarching PRs covering the `EditCommand` and `Parser` abstractions, specifically pointing out missing edge-case null handling scenarios that improved the robustness of the Parser class.
  * Held an offline pair-programming session to help a teammate debug complex Git Merge conflicts halfway through a milestone sprint.
  
* **Contributions Beyond the Project Team**:
  * Actively participated in the Canvas/Luminus Module forums, sharing a comprehensive explanation regarding a common Checkstyle configuration error affecting GitHub Actions.
  * Participated in the structured peer-testing phase, where I successfully identified and submitted a critical timezone parsing bug in another team's project, preventing them from losing marks during their final evaluation.

---

## Contributions to the User Guide (Extracts)

### Sorting expenses: `sort`
Sorts your recorded expenses. You can organize them alphabetically by category, or chronologically by date (newest first). 
*Note: When sorting by category, expenses within the same category will automatically fall back to being sorted by date (newest first).*

**Format:** `sort category` or `sort date`

**Examples:**
* `sort category`
* `sort date`

---

## Contributions to the Developer Guide (Extracts)

### Statistics Feature

The statistics feature provides a per-category breakdown of total spending using the `stats` command.

**Implementation:**
`StatisticsCommand.execute()` iterates over every expense in the list and accumulates per-category totals into a `LinkedHashMap<String, Double>`. Using a `LinkedHashMap` preserves the **insertion order**, so categories are printed in the order they first appear in the list — giving the output a predictable, intuitive feel.

The final map and expense count are then passed to `Ui.showStatistics()`, which formats each category-total pair as `CategoryName: $X.XX`.

**Design Considerations:**
- **Why `LinkedHashMap` instead of `HashMap`?** A plain `HashMap` has non-deterministic iteration order, which would cause the printed output to vary between runs. `LinkedHashMap` maintains insertion order at negligible extra cost.
- **Why `TreeMap` was not used?** `TreeMap` would sort categories alphabetically, which is a different concern from counting. Keeping the order user-defined (insertion order) is more intuitive for the `stats` command.
