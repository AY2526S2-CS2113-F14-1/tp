package seedu.duke;

import java.util.ArrayList;

/**
 * Represents the list of expenses.
 * Handles operations such as adding, deleting, and retrieving expenses from the list.
 */
public class ExpenseList {
    private ArrayList<Expense> expenses;

    /**
     * Constructs an empty ExpenseList.
     */
    public ExpenseList() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Adds a new expense to the end of the list.
     *
     * @param expense The Expense object to be added.
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Returns the current number of expenses in the list.
     *
     * @return The size of the expense list.
     */
    public int getSize() {
        return expenses.size();
    }

    /**
     * Retrieves an expense from the list based on its index.
     *
     * @param index The zero-based index of the expense to retrieve.
     * @return The requested Expense object.
     */
    public Expense getExpense(int index) {
        return expenses.get(index);
    }

    /**
     * Removes an expense from the list based on its index.
     *
     * @param index The zero-based index of the expense to be deleted.
     */
    public void removeExpense(int index) {
        expenses.remove(index);
    }
}
