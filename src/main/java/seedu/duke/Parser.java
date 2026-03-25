package seedu.duke;
import java.time.LocalDate;

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
        if (ui == null) {
            throw new IllegalArgumentException("Ui must not be null");
        }
        if (fullCommand == null) {
            return null;
        }

        String trimmedCommand = fullCommand.trim();
        if (trimmedCommand.isEmpty()) {
            return null;
        }

        String[] parts = trimmedCommand.split("\\s+", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "list":
            // BUG FIX: help s / list s / exit s should not be allowed
            if (!arguments.isEmpty()) {
                ui.showUnknownCommand();
                return null;
            }
            return new ListCommand(ui);

        case "help":
            if (!arguments.isEmpty()) {
                ui.showUnknownCommand();
                return null;
            }
            return new HelpCommand(ui);

        case "exit":
            if (!arguments.isEmpty()) {
                ui.showUnknownCommand();
                return null;
            }
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
                // 1. Extract Amount
                double amount = Double.parseDouble(addParts[0]);
                if (Double.isNaN(amount) || Double.isInfinite(amount) || amount < 0) {
                    ui.showInvalidAmount();
                    return null;
                }

                // 2. Extract Description, Category, and Date
                String remaining = addParts[1];
                String description = "";
                String category = null;
                LocalDate date = null;

                int catIndex = remaining.indexOf("/c ");
                int dateIndex = remaining.indexOf("/d ");

                // Find where the description ends (before the first flag)
                int descEnd = remaining.length();
                if (catIndex != -1 && dateIndex != -1) {
                    descEnd = Math.min(catIndex, dateIndex);
                } else if (catIndex != -1) {
                    descEnd = catIndex;
                } else if (dateIndex != -1) {
                    descEnd = dateIndex;
                }

                description = remaining.substring(0, descEnd).trim();
                if (description.isEmpty()) {
                    ui.showAddUsage();
                    return null;
                }

                // Extract Category if /c is present
                if (catIndex != -1) {
                    int catEnd = (dateIndex > catIndex) ? dateIndex : remaining.length();
                    category = remaining.substring(catIndex + 3, catEnd).trim();
                }

                // Extract Date if /d is present
                if (dateIndex != -1) {
                    int dateEnd = (catIndex > dateIndex) ? catIndex : remaining.length();
                    String dateString = remaining.substring(dateIndex + 3, dateEnd).trim();
                    try {
                        date = LocalDate.parse(dateString); // Strictly expects YYYY-MM-DD
                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("Invalid date format! Please use YYYY-MM-DD (e.g., 2026-03-24).");
                        return null;
                    }
                }

                // 3. Pass the fully parsed variables to your updated Command!
                return new AddCommand(ui, description, amount, category, date);

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
                if (index <= 0) {
                    ui.showInvalidIndex();
                    return null;
                }
                return new DeleteCommand(ui, index);
            } catch (NumberFormatException e) {
                ui.showInvalidIndexFormat();
                return null;
            }

        case "total":
            if (!arguments.isEmpty()) {
                ui.showUnknownCommand();
                return null;
            }
            return new TotalCommand(ui);

        default:
            ui.showUnknownCommand();
            return null;
        }
    }
}
