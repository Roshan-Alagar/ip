package roshan;

import java.util.Scanner;
import java.util.ArrayList;

public class Roshan {
    public static void main(String[] args) {
        String logo = " ____            _                 \n"
                + "|  _ \\ ___  ___| |__   __ _ _ __  \n"
                + "| |_) / _ \\/ __| '_ \\ / _` | '_ \\ \n"
                + "|  _ < (_) \\__ \\ | | | (_| | | | |\n"
                + "|_| \\_\\___/|___/_| |_|\\__,_|_| |_|\n";
        String line = " ____________________________________________________________";

        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello I'm Roshan");
        System.out.println("What can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (true) {
            try {
                String input = in.nextLine();
                System.out.println(line);

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equals("list")) {
                    handleList(tasks);
                    System.out.println(line);
                } else if (input.startsWith("mark ")) {
                    handleMark(input, tasks);
                    System.out.println(line);
                } else if (input.startsWith("unmark ")) {
                    handleUnmark(input, tasks);
                    System.out.println(line);
                } else if (input.startsWith("delete ")) {
                    handleDelete(input, tasks);
                    System.out.println(line);
                } else if (input.startsWith("todo ")) {
                    handleTodo(input, tasks);
                    System.out.println(line);
                } else if (input.startsWith("deadline ")) {
                    handleDeadline(input, tasks);
                    System.out.println(line);
                } else if (input.startsWith("event ")) {
                    handleEvent(input, tasks);
                    System.out.println(line);
                } else if (input.trim().equals("todo") || input.trim().equals("deadline") || input.trim().equals("event")) {
                    throw new RoshanException("OOPS!!! The description of a " + input.trim() + " cannot be empty.");
                } else {
                    throw new RoshanException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (RoshanException e) {
                System.out.println("  " + e.getMessage());
                System.out.println(line);
            } catch (Exception e) {
                System.out.println("  OOPS!!! Something went wrong: " + e.getMessage());
                System.out.println(line);
            }
        }
    }

    private static void handleList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void handleMark(String input, ArrayList<Task> tasks) throws RoshanException {
        String taskNumberStr = input.substring(5).trim();
        if (taskNumberStr.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify which task to mark.");
        }
        try {
            int taskNumber = Integer.parseInt(taskNumberStr) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
            }
            tasks.get(taskNumber).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(taskNumber));
        } catch (NumberFormatException e) {
            throw new RoshanException("OOPS!!! Please provide a valid task number.");
        }
    }

    private static void handleUnmark(String input, ArrayList<Task> tasks) throws RoshanException {
        String taskNumberStr = input.substring(7).trim();
        if (taskNumberStr.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify which task to unmark.");
        }
        try {
            int taskNumber = Integer.parseInt(taskNumberStr) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
            }
            tasks.get(taskNumber).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskNumber));
        } catch (NumberFormatException e) {
            throw new RoshanException("OOPS!!! Please provide a valid task number.");
        }
    }

    private static void handleDelete(String input, ArrayList<Task> tasks) throws RoshanException {
        String taskNumberStr = input.substring(7).trim();
        if (taskNumberStr.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify which task to delete.");
        }
        try {
            int taskNumber = Integer.parseInt(taskNumberStr) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
            }
            Task removedTask = tasks.remove(taskNumber);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new RoshanException("OOPS!!! Please provide a valid task number.");
        }
    }

    private static void handleTodo(String input, ArrayList<Task> tasks) throws RoshanException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(description);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks) throws RoshanException {
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
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String input, ArrayList<Task> tasks) throws RoshanException {
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
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}