package roshan;

import java.util.Scanner;

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
        Task[] tasks = new Task[100];
        int counter = 0;

        while (true) {
            try {
                String input = in.nextLine();
                System.out.println(line);

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + "." + tasks[i]);
                    }
                    System.out.println(line);
                } else if (input.startsWith("mark ")) {
                    counter = handleMark(input, tasks, counter);
                    System.out.println(line);
                } else if (input.startsWith("unmark ")) {
                    counter = handleUnmark(input, tasks, counter);
                    System.out.println(line);
                } else if (input.startsWith("todo ")) {
                    counter = handleTodo(input, tasks, counter);
                    System.out.println(line);
                } else if (input.startsWith("deadline ")) {
                    counter = handleDeadline(input, tasks, counter);
                    System.out.println(line);
                } else if (input.startsWith("event ")) {
                    counter = handleEvent(input, tasks, counter);
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

    private static int handleMark(String input, Task[] tasks, int counter) throws RoshanException {
        String taskNumberStr = input.substring(5).trim();
        if (taskNumberStr.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify which task to mark.");
        }
        try {
            int taskNumber = Integer.parseInt(taskNumberStr) - 1;
            if (taskNumber < 0 || taskNumber >= counter) {
                throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
            }
            tasks[taskNumber].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks[taskNumber]);
        } catch (NumberFormatException e) {
            throw new RoshanException("OOPS!!! Please provide a valid task number.");
        }
        return counter;
    }

    private static int handleUnmark(String input, Task[] tasks, int counter) throws RoshanException {
        String taskNumberStr = input.substring(7).trim();
        if (taskNumberStr.isEmpty()) {
            throw new RoshanException("OOPS!!! Please specify which task to unmark.");
        }
        try {
            int taskNumber = Integer.parseInt(taskNumberStr) - 1;
            if (taskNumber < 0 || taskNumber >= counter) {
                throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
            }
            tasks[taskNumber].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks[taskNumber]);
        } catch (NumberFormatException e) {
            throw new RoshanException("OOPS!!! Please provide a valid task number.");
        }
        return counter;
    }

    private static int handleTodo(String input, Task[] tasks, int counter) throws RoshanException {
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new RoshanException("OOPS!!! The description of a todo cannot be empty.");
        }
        if (counter >= 100) {
            throw new RoshanException("OOPS!!! Task list is full. Cannot add more tasks.");
        }
        Task newTask = new Todo(description);
        tasks[counter] = newTask;
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + counter + " tasks in the list.");
        return counter;
    }

    private static int handleDeadline(String input, Task[] tasks, int counter) throws RoshanException {
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
        if (counter >= 100) {
            throw new RoshanException("OOPS!!! Task list is full. Cannot add more tasks.");
        }
        Task newTask = new Deadline(description, by);
        tasks[counter] = newTask;
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + counter + " tasks in the list.");
        return counter;
    }

    private static int handleEvent(String input, Task[] tasks, int counter) throws RoshanException {
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
        if (counter >= 100) {
            throw new RoshanException("OOPS!!! Task list is full. Cannot add more tasks.");
        }
        Task newTask = new Event(description, from, to);
        tasks[counter] = newTask;
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + counter + " tasks in the list.");
        return counter;
    }
}