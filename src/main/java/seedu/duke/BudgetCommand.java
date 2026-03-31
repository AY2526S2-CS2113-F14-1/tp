package seedu.duke;

/**
 * Handles the logic for setting or displaying a spending budget.
 */
public class BudgetCommand extends Command {
    private final Double budgetAmount;

    /**
     * Constructs a BudgetCommand with the specified Ui and optional budget amount.
     *
     * @param ui The Ui object used to display user-facing messages.
     * @param budgetAmount The budget amount to set, or null if displaying budget details.
     */
    public BudgetCommand(Ui ui, Double budgetAmount) {
        super(ui);
        assert ui != null : "Ui must not be null";
        this.budgetAmount = budgetAmount;
    }

    /**
     * Executes the budget command.
     * If an amount is provided, sets the budget.
     * If no amount is provided, displays current budget details.
     *
     * @param expenseList The list whose budget will be updated or queried.
     */
    @Override
    public void execute(ExpenseList expenseList) {
        assert expenseList != null : "ExpenseList must not be null";

        if (budgetAmount == null) {
            if (!expenseList.hasBudget()) {
                ui.showBudgetNotSet();
                return;
            }

            double budget = expenseList.getBudget();
            double totalSpent = expenseList.getTotalAmount();
            double remaining = expenseList.getRemainingBudget();

            ui.showBudgetDetails(budget, totalSpent, remaining);
            return;
        }

        if (budgetAmount <= 0) {
            ui.showInvalidBudget();
            return;
        }

        expenseList.setBudget(budgetAmount);
        ui.showBudgetSet(budgetAmount);
        if (expenseList.isOverBudget()) {
            ui.showBudgetExceededWarning(
                    expenseList.getBudget(),
                    expenseList.getTotalAmount()
            );
        }
    }

    /**
     * Returns true only if the budget is being modified.
     *
     * @return true if setting a budget, false if only viewing.
     */
    @Override
    public boolean shouldPersist() {
        return budgetAmount != null;
    }
}
