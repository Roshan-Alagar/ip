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
            String input = in.nextLine();
            System.out.println(line);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + "." + tasks[i]);
                }
                System.out.println(line);
            }
            else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println(line);
            }
            else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println(line);
            }
            else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task newTask = new Todo(description);
                tasks[counter] = newTask;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println(line);
            }
            else if (input.startsWith("deadline ")) {
                String commandContent = input.substring(9);
                int byIndex = commandContent.indexOf("/by");
                String description = commandContent.substring(0, byIndex).trim();
                String by = commandContent.substring(byIndex + 4).trim();
                Task newTask = new Deadline(description, by);
                tasks[counter] = newTask;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println(line);
            }
            else if (input.startsWith("event ")) {
                String commandContent = input.substring(6);
                int fromIndex = commandContent.indexOf("/from");
                int toIndex = commandContent.indexOf("/to");
                String description = commandContent.substring(0, fromIndex).trim();
                String from = commandContent.substring(fromIndex + 6, toIndex).trim();
                String to = commandContent.substring(toIndex + 4).trim();
                Task newTask = new Event(description, from, to);
                tasks[counter] = newTask;
                counter++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + counter + " tasks in the list.");
                System.out.println(line);
            }
            else {
                System.out.println("I don't understand that command!");
                System.out.println(line);
            }
        }
    }
}