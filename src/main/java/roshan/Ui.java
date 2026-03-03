package roshan;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interface interactions.
 * Manages input from user and output to console.
 */

public class Ui {
    private final Scanner scanner;
    private final String line = " ____________________________________________________________";

    /**
     * Creates a new Ui instance with a scanner for reading user input.
     */

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo.
     */

    public void showWelcome() {
        String logo = " ____            _                 \n"
                + "|  _ \\ ___  ___| |__   __ _ _ __  \n"
                + "| |_) / _ \\/ __| '_ \\ / _` | '_ \\ \n"
                + "|  _ < (_) \\__ \\ | | | (_| | | | |\n"
                + "|_| \\_\\___/|___/_| |_|\\__,_|_| |_|\n";
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello I'm Roshan");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Displays the divider line.
     */

    public void showLine() {
        System.out.println(line);
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */

    public void showError(String message) {
        System.out.println("  " + message);
    }

    /**
     * Displays a loading error.
     */

    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    /**
     * Displays a goodbye.
     */

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads next line
     */

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a tasks added.
     *
     * @param task The task message to display.
     */

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a tasks deleted.
     *
     * @param task The task message to display.
     */

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a tasks that has been marked.
     *
     * @param task The task message to display.
     */

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Displays a tasks that has been unmarked.
     *
     * @param task The task message to display.
     */

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Displays all tasks in the list.
     *
     * @param tasks The task message to display.
     */

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays all found tasks in the list.
     *
     * @param foundTasks The task message to display.
     */

    public void showFoundTasks(ArrayList<Task> foundTasks) {
        if (foundTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println((i + 1) + "." + foundTasks.get(i));
            }
        }
    }
}