package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    private Ui ui = new Ui();

    @Test
    public void parse_listCommand_returnsListCommand() {
        Command command = Parser.parse("list", ui);
        assertTrue(command instanceof ListCommand, "Parser should return a ListCommand object");
    }

    @Test
    public void parse_exitCommand_returnsExitCommand() {
        Command command = Parser.parse("exit", ui);
        assertTrue(command instanceof ExitCommand, "Parser should return an ExitCommand object");
    }

    @Test
    public void parse_invalidCommandWord_returnsNull() {
        Command command = Parser.parse("jump", ui);
        assertNull(command, "Parser should return null for unknown commands");
    }

    @Test
    public void parse_addCommandMissingArguments_returnsNull() {
        Command command = Parser.parse("add", ui);
        assertNull(command, "Parser should return null when add arguments are missing");
    }

    @Test
    public void parse_addCommandValidArguments_returnsCommand() {
        Command command = Parser.parse("add 5.50 Coffee", ui);
        // Note: Because we are using the anonymous class workaround for AddExpense right now,
        // we can only assert that it returns a valid Command object.
        // Once Krishna refactors, you can change this to: assertTrue(command instanceof AddCommand)
        assertNotNull(command, "Parser should return a Command object for valid add inputs");
        assertFalse(command.isExit(), "Add command should not trigger exit");
    }

    @Test
    public void parse_deleteCommandInvalidNumber_returnsNull() {
        Command command = Parser.parse("delete abc", ui);
        assertNull(command, "Parser should return null if index is not a number");
    }
}
