package roshan;

public class Parser {

    public static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

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

    public static Todo parseTodo(String input) throws RoshanException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

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
}