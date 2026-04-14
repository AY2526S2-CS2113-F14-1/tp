# Project Portfolio Page — SpendSwift

## Overview
**SpendSwift** is a desktop application for managing personal finances, optimized for Command Line Interface (CLI) users. It allows university students to track expenses, manage budgets, and analyze spending habits through a fast, text-based interface.

## Summary of Contributions
This section summarizes my specific contributions to the project, including the user interface design, monthly budget system, expense listing functionality, and technical documentation.

* **Code Contributed**: [View my code on RepoSense](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2026-02-20T00%3A00%3A00&filteredFileName=&tabOpen=true&tabType=authorship&tabAuthor=AnnikaGarg&tabRepo=AY2526S2-CS2113-F14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Feature**: **Centralised User Interface Component (`Ui`)**
    * **What it does**: Designed and improved the shared `Ui` component that centralises all user-facing input and output in SpendSwift. This includes confirmation messages, warnings, usage hints, summaries, and interactive prompts.
    * **Highlights**: Helped enforce a clearer separation of concerns by keeping presentation logic inside `Ui` while command classes remained focused on execution logic. This also improved consistency across features such as `add`, `budget`, `list`, and `help`, since output formatting was managed in one place.

* **Feature**: **Monthly Budget Management System (`budget`)**
    * **What it does**: Implemented and documented the `budget` feature, which allows users to set, update, and view monthly budgets using formats such as `budget`, `budget AMOUNT`, `budget YYYY-MM`, and `budget YYYY-MM AMOUNT`.
    * **Highlights**: Extended the design from a single budget into a **month-specific budget system**, allowing each month’s spending limit to be tracked independently. Also handled both **set/update mode** and **view mode**, including budget status display, remaining budget calculations, and warnings when spending exceeds the defined monthly limit.

* **Feature**: **Expense Listing System (`list`)**
    * **What it does**: Implemented and documented the `list` feature so users can either display all recorded expenses or filter expenses by a specific month using `list YYYY-MM`.
    * **Highlights**: Designed the feature to support both full-list and month-filtered output through a single command, keeping the user interface compact and intuitive. Added validation for invalid `YYYY-MM` input and ensured the output was clearly presented through the shared `Ui` component.

* **Contributions to Team Tasks**:
    * Helped improve the consistency of user-facing CLI output by centralising presentation logic in `Ui`.
    * Integrated the budget and list features into the command-based architecture used across the application.
    * Improved the quality of documentation by ensuring that command behaviour, formatting rules, and implementation details stayed aligned.

* **Contributions to the UG**:
    * Authored the `list` and `budget` command sections, including command formats, rules, examples, and expected behaviour.
    * Contributed the **Notes on User Interaction** section to explain confirmations, warnings, prompts, and read-only command behaviour.
    * Improved user-facing explanations so that monthly budget viewing, setting, and list filtering were easier to understand.

* **Contributions to the DG**:
    * Authored the **Ui Component** section, including its role, responsibilities, design rationale, and trade-offs.
    * Authored the **List Feature** section, covering parsing, execution flow, and design considerations.
    * Authored the **Budget Feature** section, covering the parser logic, command execution modes, monthly budget storage, and design considerations.

---

## Contributions to the User Guide (Extracts)

### Listing expenses: `list`
Shows your recorded expenses. You may list all expenses or only the expenses for a specific month.

**Format:** `list [YYYY-MM]`

**Rules:**
- If no month is provided, SpendSwift shows all recorded expenses.
- If `YYYY-MM` is provided, SpendSwift shows only the expenses recorded in that month.
- `YYYY-MM` must be a valid month in `YYYY-MM` format.
- The `list` command accepts at most one optional `YYYY-MM` argument.

### Setting and viewing a monthly budget: `budget`
Sets, updates, or views a monthly spending budget.

**Format:**
- `budget`
- `budget AMOUNT`
- `budget YYYY-MM`
- `budget YYYY-MM AMOUNT`

* Supports both viewing and updating budgets for the current month or a specified month.
* Displays the monthly budget, amount spent, and remaining budget when a budget exists.
* Warns the user when spending exceeds the defined monthly budget.

---

## Contributions to the Developer Guide (Extracts)

### Ui Component
**Implementation:** The `Ui` component centralises all user-facing input and output in SpendSwift. It is responsible for displaying confirmation messages, warnings, usage hints, summaries, and interactive prompts.

Command classes delegate presentation responsibilities to `Ui`, which improves separation of concerns:
1. command classes remain focused on application logic
2. output formatting is kept in one place
3. user interaction stays consistent across features

### List Feature
**Implementation:** `Parser.parseListCommand()` checks whether an argument was supplied.
1. If no argument is provided, it creates a `ListCommand` with `month == null`.
2. If an argument is provided, it attempts to parse it into a `YearMonth`.
3. If parsing fails, `Ui.showInvalidMonthYear()` is called and `null` is returned.

`ListCommand.execute()` then displays either:
- the full list of expenses, or
- only the expenses recorded in the specified month.

### Budget Feature
**Implementation:** `Parser.parseBudgetCommand()` extracts up to two arguments from user input:
1. a target `YearMonth`
2. an optional `amount`

If the amount is present, the command enters **set/update mode**.  
If the amount is omitted, the command enters **view mode**.

`BudgetCommand.execute()`:
- stores and updates month-specific budgets in `ExpenseList`
- retrieves the total spending for the target month
- displays budget usage details through `Ui`
- shows warnings when expenses exceed the defined monthly budget

This design keeps budgets scoped per month, which better matches real student budgeting behaviour.