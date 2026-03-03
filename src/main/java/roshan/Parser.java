package roshan;

/**
 * Parses user input and extracts command details.
 */

public class Parser {

    /**
     * Extracts the command word from the full user input.
     *
     * @param fullCommand The full command string from user.
     * @return The command word (first word).
     */

    public static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     * Parses a task number from user input.
     *
     * @param input The user input string.
     * @param startIndex The starting index to parse from.
     * @return The task number (0-indexed).
     * @throws RoshanException If the task number is invalid.
     */

    public static int parseTaskNumber(String input, int startIndex) throws RoshanException {
        String taskNumberStr = input.substring(startIndex).trim();
        if (taskNumberStr.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify a task number.");
        }
        try {
            return Integer.parseInt(taskNumberStr) - 1;
        } catch (NumberFormatException e) {
            throw new RoshanException("OOPS!!! Please provide a valid task number.");
        }
    }

    /**
     * Parses a todo task from user input.
     * @param input The user input string.
     * @return The todo tasks (0-indexed).
     * @throws RoshanException If the task number is invalid.
     */

    public static Todo parseTodo(String input) throws RoshanException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    /**
     * Parses a Deadline task from user input.
     * @param input The user input string.
     * @return The Deadline tasks (0-indexed).
     * @throws RoshanException If the task number is invalid.
     */


    public static Deadline parseDeadline(String input) throws RoshanException {
        String commandContent = input.substring(9).trim();
        if (commandContent.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of a deadline cannot be empty.");
        }
        int byIndex = commandContent.indexOf("/by");
        if (byIndex == -1) {
            throw new RoshanException("OOPS!!! Please specify the deadline using /by.");
        }
        String description = commandContent.substring(0, byIndex).trim();
        String by = commandContent.substring(byIndex + 3).trim();
        if (description.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (by.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify when the deadline is.");
        }
        return new Deadline(description, by);
    }

    /**
     * Parses a Event task from user input.
     * @param input The user input string.
     * @return The Event tasks (0-indexed).
     * @throws RoshanException If the task number is invalid.
     */

    public static Event parseEvent(String input) throws RoshanException {
        String commandContent = input.substring(6).trim();
        if (commandContent.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of an event cannot be empty.");
        }
        int fromIndex = commandContent.indexOf("/from");
        int toIndex = commandContent.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new RoshanException("OOPS!!! Please specify the event time using /from and /to.");
        }
        String description = commandContent.substring(0, fromIndex).trim();
        String from = commandContent.substring(fromIndex + 5, toIndex).trim();
        String to = commandContent.substring(toIndex + 3).trim();
        if (description.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of an event cannot be empty.");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify when the event starts and ends.");
        }
        return new Event(description, from, to);
    }

    /**
     * Parses a Find task from user input.
     * @param input The user input string.
     * @return The Found tasks (0-indexed).
     * @throws RoshanException If the task number is invalid.
     */

    public static String parseFind(String input) throws RoshanException {
        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new RoshanException("OOPS!!! The search keyword cannot be empty.");
        }
        return keyword;
    }
}