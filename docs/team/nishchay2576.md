# Project Portfolio Page - SpendSwift

## Overview
**SpendSwift** is a fast, text-based personal finance tracker optimized for CLI users, allowing university students to seamlessly manage budgets, track daily expenses, and isolate peer-to-peer debts.

## Summary of Contributions

* **Code Contributed:** [Repo Dashboard Link](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=Nishchay2576&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=Nishchay2576&tabRepo=AY2526S2-CS2113-F14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Enhancements Implemented**:
    * **Core Parsing Engine (`Parser`)**: Architected the centralized parser with a robust substring manipulation algorithm, allowing users to input optional CLI flags in *any* order without rigid positional constraints.
    * **Loan Tracking System**: Engineered an entirely independent ledger with specialized commands (`lend`, `repay`, `loans`) to track peer-to-peer debts, preventing temporary IOUs from skewing personal budgeting statistics or triggering false budget warnings.
    * **Expense Modification (`edit`)**: Developed an immutable object update pattern allowing users to selectively modify specific expense fields (amount, description, category, date) without needing to delete and recreate entries.
    * **Summary Queries (`total`, `list`)**: Authored read-only commands to calculate absolute chronological spending sums and filter historical data seamlessly.

* **Contributions to the UG**:
    * Authored parameters, constraints, and execution examples for `edit`, `list`, `total`, `lend`, `repay`, and `loans`.
    * Wrote the definitive "Notes about the Command Format" to proactively establish system boundaries, restricted characters (e.g., `/`), and floating-point limits for the user.

* **Contributions to the DG**:
    * Authored the comprehensive "Design & Implementation" and "Design Considerations" chapters for the Edit Expense Feature, Total Feature, and Loan Tracking System.
    * Drafted and embedded 4 major UML Sequence Diagrams resolving static class notation and precise lifeline terminations for command execution flows.

* **Contributions to Team-based Tasks**:
    * Managed complex Git merge conflicts involving the `Parser` component during concurrent feature integration across the team.

* **Review/Mentoring**:
    * Reviewed teammate PRs for `AddCommand` and `DeleteCommand`, identifying and correcting critical null-handling flaws and edge-case execution failures.

---

## Contributions to the User Guide (Extracts)

### Editing an expense: `edit`
Edits an existing expense in your list. You only need to provide the flags for the fields you want to change, and SpendSwift handles the rest.

**Format:** `edit INDEX [/a NEW_AMOUNT] [/de NEW_DESC] [/c NEW_CATEGORY] [/da YYYY-MM-DD]`

**Rules:**
* At least one flag must be provided.
* Flags can be provided in **any order**.
* Do not use extra `/` characters in your new description or category, as it will confuse the parser.

**Examples of Combinations:**
* `edit 1 /a 15.00 /c Food` *(Updates the amount to $15.00 and category to Food)*
* `edit 3 /da 2026-04-10 /a 5.50 /de Lunch` *(Updates the date, amount, and description simultaneously)*

---

## Contributions to the Developer Guide (Extracts)

### Loan Tracking System
The Loan Tracking System allows users to manage debts (money lent to others) completely separately from their primary expenses.

**Design Consideration:**
We chose to keep loans in a separate ledger (`ArrayList<Loan>`) rather than mixing them into the main `ExpenseList`. Treating a loan as a standard expense would artificially inflate the user's spending totals and trigger false "Budget Exceeded" warnings for money that was not actually consumed, ruining the integrity of the `stats` and `forecast` features.

### Edit Expense Feature
The edit feature allows users to modify one or more fields of an existing expense using the `edit` command.

**Implementation:**
`Parser.parseEditCommand()` extracts the index and each flag from the input string sequentially. Each flag is located by its keyword using `indexOf()`, its value is extracted up to the next `/` or the end of the input, and then it is stripped from the working string before the next flag is processed. This substring manipulation algorithm allows flags to appear in any order without ambiguity.

**Design considerations:**
`Expense` objects are immutable (all fields are `final`), so editing produces a new `Expense` rather than mutating the existing one. An alternative considered was making `Expense` mutable with setter methods, but immutability was preferred to avoid unintended side effects across the codebase.