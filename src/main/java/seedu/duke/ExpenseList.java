package seedu.duke;

import java.util.ArrayList;

/**
 * Represents the list of expenses.
 * Handles adding, deleting, and retrieving expenses.
 */
public class ExpenseList {
    private final ArrayList<Expense> expenses;
    private double budget;

    public ExpenseList() {
        this.expenses = new ArrayList<>();
        this.budget = -1;
    }

    /**
     * Adds a new expense to the end of the list.
     */
    public void addExpense(Expense expense) {
        if (expense == null) {
            throw new IllegalArgumentException("Expense must not be null");
        }
        expenses.add(expense);
    }

    /**
     * Returns the current number of expenses in the list.
     */
    public int getSize() {
        return expenses.size();
    }

    /**
     * Retrieves an expense from the list based on its index.
     */
    public Expense getExpense(int index) {
        return expenses.get(index);
    }

    /**
     * Sets the spending budget.
     *
     * @param budget The budget amount to set. Must be non-negative.
     * @throws IllegalArgumentException If the budget is negative.
     */
    public void setBudget(double budget) {
        if (budget < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        this.budget = budget;
    }

    /**
     * Returns the currently set budget.
     *
     * @return The budget amount, or -1 if no budget has been set.
     */
    public double getBudget() {
        return budget;
    }

    /**
     * Returns whether a budget has been set.
     *
     * @return true if a budget exists, false otherwise.
     */
    public boolean hasBudget() {
        return budget >= 0;
    }

    /**
     * Calculates the total amount of all expenses.
     *
     * @return The total expense amount.
     */
    public double getTotalAmount() {
        double total = 0.0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        return total;
    }

    /**
     * Checks whether the total spending exceeds the budget.
     *
     * @return true if total spending is greater than the budget, false otherwise.
     */
    public boolean isOverBudget() {
        return hasBudget() && getTotalAmount() > budget;
    }

    /**
     * Removes an expense from the list and returns the removed item.
     * Required for Issue 3.
     */
    public Expense deleteExpense(int index) throws IndexOutOfBoundsException {
        return expenses.remove(index);
    }
}
