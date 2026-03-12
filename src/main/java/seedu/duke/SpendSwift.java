package seedu.duke;

import java.util.Scanner;

/**
 * The main entry point for the SpendSwift application.
 * Initializes the application and starts the interaction loop with the user.
 */
public class SpendSwift {
    private ExpenseList expenseList;

    /**
     * Constructs a SpendSwift instance and initializes the core components.
     */
    public SpendSwift() {
        expenseList = new ExpenseList();
    }

    /**
     * Runs the main loop of the application.
     * Continuously reads user input and handles the "exit" command.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm SpendSwift.");
        System.out.println("What expenses are we tracking today?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        boolean isExit = false;
        while (!isExit && scanner.hasNextLine()) {
            String fullCommand = scanner.nextLine().trim();
            if (fullCommand.isEmpty()) {
                continue;
            }

            String commandWord = fullCommand.split(" ")[0].toLowerCase();

            switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "exit":
                isExit = true;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.print("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                break;
            default:
                System.out.println("Unknown command. Type 'help' to see available commands.");
                break;
            }
        }
        scanner.close();
    }

    /**
     * Prints the help menu showing all available commands and their formats.
     */
    private static void printHelp() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Here are the available commands:");
        System.out.println("  add AMOUNT DESCRIPTION  - Add a new expense");
        System.out.println("  list                    - List all expenses");
        System.out.println("  delete INDEX            - Delete an expense by index");
        System.out.println("  help                    - Show this help menu");
        System.out.println("  exit                    - Exit the application");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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
