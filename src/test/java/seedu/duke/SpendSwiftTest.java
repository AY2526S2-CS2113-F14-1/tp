Skip to content
Nishchay2576
tp
Repository navigation
Code
Pull requests
Actions
Projects
Wiki
Security
Insights
Settings
Files
Go to file
t
test/java/seedu/duke content loaded
.github
META-INF
config
docs
team
AboutUs.md
DeveloperGuide.md
README.md
UserGuide.md
gradle
src
main/java/seedu/duke
test/java/seedu/duke
AddCommandTest.java
BudgetCommandTest.java
DeleteCommandTest.java
EditCommandTest.java
ExitCommandTest.java
ExpenseListTest.java
ExpenseTest.java
HelpCommandTest.java
ListCommandTest.java
ParserTest.java
SpendSwiftTest.java
StorageTest.java
TotalCommandTest.java
text-ui-test
.gitignore
CONTRIBUTORS.md
README.md
build.gradle
gradlew
gradlew.bat
tp/src/test/java/seedu/duke
/
SpendSwiftTest.java
in
master

Edit

Preview
Indent mode

Spaces
Indent size

4
Line wrap mode

No wrap
Editing SpendSwiftTest.java file contents
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class SpendSwiftTest {

    @TempDir
    Path tempDir;

    @Test
    public void run_exitCommand_printsWelcomeAndExitMessage() {
        String output = runWithInput("exit\n", tempDir);

        assertTrue(output.contains("Hello! I'm SpendSwift."));
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    public void run_addThenList_showsAddedExpense() {
        String output = runWithInput("add 5.50 Coffee\nlist\nexit\n", tempDir);

        assertTrue(output.contains("I've added this expense:"));
        assertTrue(output.contains("Coffee ($5.50)"));
        assertTrue(output.contains("Here are your tracked expenses:"));
    }

Use Control + Shift + m to toggle the tab key moving focus. Alternatively, use esc then tab to move to the next interactive element on the page.
test/java/seedu/duke content loaded
