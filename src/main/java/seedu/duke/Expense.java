package seedu.duke;

/**
 * Represents a single financial expense tracked by the user.
 * Contains the description and the monetary amount of the expense.
 */
public class Expense {
    protected String description;
    protected double amount;

    /**
     * Constructs an Expense object with the specified description and amount.
     *
     * @param description The details or name of the expense.
     * @param amount The monetary cost of the expense.
     */
    public Expense(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    /**
     * Retrieves the description of the expense.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the monetary amount of the expense.
     *
     * @return The cost of the expense.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns a string representation of the expense, formatting the amount to two decimal places.
     *
     * @return A formatted string showing the description and cost.
     */
    @Override
    public String toString() {
        return description + " ($" + String.format("%.2f", amount) + ")";
    }
}
