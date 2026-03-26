package seedu.duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatisticsCommandTest {

    private final Ui ui = new Ui();

    // ── Helpers ───────────────────────────────────────────────────────────────

    private ExpenseList buildList() {
        ExpenseList list = new ExpenseList();
        list.addExpense(new Expense("Lunch",    10.00, "Food",      LocalDate.of(2026, 3, 1)));
        list.addExpense(new Expense("Dinner",   20.00, "Food",      LocalDate.of(2026, 3, 2)));
        list.addExpense(new Expense("Bus fare",  5.00, "Transport", LocalDate.of(2026, 3, 3)));
        return list;
    }

    // ── Category totals ───────────────────────────────────────────────────────

    @Test
    public void execute_twoCategories_correctTotals() {
        // Use a capturing subclass to inspect the map passed to showStatistics
        final Map<String, Double>[] captured = new Map[]{null};

        Ui capturingUi = new Ui() {
            @Override
            public void showStatistics(Map<String, Double> totals, int count) {
                captured[0] = totals;
            }
        };

        ExpenseList list = buildList();
        new StatisticsCommand(capturingUi).execute(list);

        assertNotNull(captured[0]);
        assertEquals(2, captured[0].size(), "Should have exactly two categories");
        assertEquals(30.00, captured[0].get("Food"),      0.001);
        assertEquals(5.00,  captured[0].get("Transport"), 0.001);
    }

    @Test
    public void execute_emptyList_emptyMap() {
        final Map<String, Double>[] captured = new Map[]{null};
        Ui capturingUi = new Ui() {
            @Override
            public void showStatistics(Map<String, Double> totals, int count) {
                captured[0] = totals;
            }
        };

        new StatisticsCommand(capturingUi).execute(new ExpenseList());

        assertNotNull(captured[0]);
        assertTrue(captured[0].isEmpty(), "Totals map should be empty for an empty expense list");
    }

    @Test
    public void execute_sameCategory_combinedTotal() {
        final Map<String, Double>[] captured = new Map[]{null};
        Ui capturingUi = new Ui() {
            @Override
            public void showStatistics(Map<String, Double> totals, int count) {
                captured[0] = totals;
            }
        };

        ExpenseList list = new ExpenseList();
        list.addExpense(new Expense("A", 7.00, "Food", null));
        list.addExpense(new Expense("B", 3.00, "Food", null));
        new StatisticsCommand(capturingUi).execute(list);

        assertEquals(10.00, captured[0].get("Food"), 0.001);
        assertEquals(1, captured[0].size());
    }

    // ── shouldPersist ─────────────────────────────────────────────────────────

    @Test
    public void shouldPersist_returnsFalse() {
        assertFalse(new StatisticsCommand(ui).shouldPersist());
    }

    // ── Parser integration ────────────────────────────────────────────────────

    @Test
    public void parse_statsCommand_returnsStatisticsCommand() {
        Command cmd = Parser.parse("stats", ui);
        assertNotNull(cmd);
        assertTrue(cmd instanceof StatisticsCommand);
    }

    @Test
    public void parse_statsWithExtraArgs_returnsNull() {
        Command cmd = Parser.parse("stats extra", ui);
        assertNull(cmd);
    }
}
