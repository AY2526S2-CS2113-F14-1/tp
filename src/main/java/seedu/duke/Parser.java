package seedu.duke;

/**
 * Parses user input into meaningful commands.
 */
public class Parser {

    /**
     * Parses the full command string from the user and returns the appropriate Command object.
     *
     * @param fullCommand The raw string entered by the user.
     * @param ui The UI instance to pass to commands or use for error messages.
     * @return The corresponding Command object, or null if invalid.
     */
    public static Command parse(String fullCommand, Ui ui) {
        String[] parts = fullCommand.trim().split("\\s+", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "list":
            return new ListCommand(ui);

        case "help":
            return new HelpCommand(ui);

        case "exit":
            return new ExitCommand(ui);

        case "add":
            if (arguments.isEmpty()) {
                ui.showAddUsage();
                return null;
            }

            String[] addParts = arguments.split("\\s+", 2);
            if (addParts.length < 2) {
                ui.showAddUsage();
                return null;
            }

            try {
                double amount = Double.parseDouble(addParts[0]);
                String description = addParts[1];

                // WORKAROUND: Wrap the old AddCommand in an anonymous Command
                return new Command(ui) {
                    @Override
                    public void execute(ExpenseList expenseList) {
                        AddCommand addCommand = new AddCommand(description, amount);
                        addCommand.execute(expenseList, ui);
                    }
                };
            } catch (NumberFormatException e) {
                ui.showInvalidAmount();
                return null;
            }

        case "delete":
            if (arguments.isEmpty()) {
                ui.showDeleteUsage();
                return null;
            }

            try {
                int index = Integer.parseInt(arguments);

                // WORKAROUND: Wrap the DeleteCommand in an anonymous Command
                return new Command(ui) {
                    @Override
                    public void execute(ExpenseList expenseList) {
                        DeleteCommand deleteCommand = new DeleteCommand(index);
                        deleteCommand.execute(expenseList, ui);
                    }
                };
            } catch (NumberFormatException e) {
                ui.showInvalidIndexFormat();
                return null;
            }

        default:
            ui.showUnknownCommand();
            return null;
        }
    }
}
