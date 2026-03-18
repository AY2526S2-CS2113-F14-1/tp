package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HelpCommandTest {

    @Test
    public void execute_helpCommand_executesWithoutException() {
        Ui ui = new Ui();
        ExpenseList expenseList = new ExpenseList();
        HelpCommand helpCommand = new HelpCommand(ui);

        // Since HelpCommand just triggers a UI print, we verify it doesn't crash the program
        assertDoesNotThrow(() -> helpCommand.execute(expenseList),
                "HelpCommand should execute successfully without throwing errors");
    }
}
