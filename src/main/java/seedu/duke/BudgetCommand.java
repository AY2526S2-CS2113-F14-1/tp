package seedu.duke;

/**
 * Handles the logic for setting a spending budget.
 */
public class BudgetCommand extends Command {
    private final double budgetAmount;

    /**
     * Constructs a BudgetCommand with the specified Ui and budget amount.
     *
     * @param ui           The Ui object used to display user-facing messages.
     * @param budgetAmount The budget amount to set.
     */
    public BudgetCommand(Ui ui, double budgetAmount) {
        super(ui);
        this.budgetAmount = budgetAmount;
    }

    /**
     * Executes the budget command by updating the budget in the expense list.
     *
     * @param expenseList The list whose budget will be updated.
     */
    @Override
    public void execute(ExpenseList expenseList) {
        expenseList.setBudget(budgetAmount);
        ui.showBudgetSet(budgetAmount);
    }

    /**
     * Returns true because setting a budget modifies persisted data.
     *
     * @return true to indicate this command should be saved.
     */
    @Override
    public boolean shouldPersist() {
        return true;
    }
}
