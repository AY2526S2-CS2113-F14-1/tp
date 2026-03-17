package seedu.duke;

import java.util.Scanner;

/**
 * The main entry point for the SpendSwift application.
 * Initializes the application and starts the interaction loop with the user.
 */
public class SpendSwift {
    private static final String DATA_FILE_PATH = "data/expenses.txt";
    private ExpenseList expenseList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a SpendSwift instance and initializes the core components.
     * Loads any previously saved expenses from disk.
     */
    public SpendSwift() {
        expenseList = new ExpenseList();
        ui = new Ui();
        storage = new Storage(DATA_FILE_PATH, ui);
        storage.load(expenseList);
    }

    /**
     * Runs the main loop of the application.
     * Continuously reads user input and handles the "exit" command.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit && scanner.hasNextLine()) {
            String fullCommand = scanner.nextLine().trim();
            if (fullCommand.isEmpty()) {
                continue;
            }

            String commandWord = fullCommand.split(" ")[0].toLowerCase();

            switch (commandWord) {
            case "list":
                new ListCommand(ui).execute(expenseList);
                break;
            case "help":
                new HelpCommand(ui).execute(expenseList);
                break;
            case "add":
                String[] parts = fullCommand.split("\\s+", 3);
                if (parts.length < 3) {
                    ui.showAddUsage();
                    break;
                }

                try {
                    double amount = Double.parseDouble(parts[1]);
                    String description = parts[2];

                    AddCommand addCommand = new AddCommand(description, amount);
                    addCommand.execute(expenseList, ui);
                    storage.save(expenseList);

                } catch (NumberFormatException e) {
                    ui.showInvalidAmount();
                }
                break;
            case "exit":
                Command exitCommand = new ExitCommand(ui);
                exitCommand.execute(expenseList);
                storage.save(expenseList);
                isExit = exitCommand.isExit();
                break;
            case "delete":
                String[] deleteParts = fullCommand.split("\\s+");
                if (deleteParts.length < 2) {
                    ui.showDeleteUsage();
                    break;
                }
                try {
                    int index = Integer.parseInt(deleteParts[1]);
                    // Assuming you create a DeleteExpense class similar to AddExpense
                    DeleteCommand deleteCommand = new DeleteCommand(index);
                    deleteCommand.execute(expenseList, ui);
                    storage.save(expenseList); // Save immediately after deletion

                } catch (NumberFormatException e) {
                    ui.showInvalidIndexFormat();
                }
                break;
            default:
                ui.showUnknownCommand();
                break;
            }
        }
        scanner.close();
    }

    /**
     * The main method that launches the SpendSwift application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {

        new SpendSwift().run();
    }
}
