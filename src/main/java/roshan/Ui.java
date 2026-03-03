package roshan;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private final String line = " ____________________________________________________________";

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public void showLine() {
        System.out.println(line);
    }

    public void showError(String message) {
        System.out.println("  " + message);
    }

    public void showLoadingError() {
        showError("Error loading tasks from file. Starting with empty task list.");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
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