package roshan;

import java.util.ArrayList;
/**
 * Main class for the Roshan chatbot application.
 * Handles initialization and coordination of UI, storage, and task management.
 */

public class Roshan {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new Roshan instance with the specified file path for data storage.
     *
     * @param filePath The file path where tasks are stored.
     */

    public Roshan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (RoshanException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the chatbot, processing user commands until exit.
     */

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                String command = Parser.getCommand(input);

                switch (command) {
                case "bye":
                    ui.showGoodbye();
                    isExit = true;
                    break;
                case "list":
                    ui.showTaskList(tasks);
                    break;
                case "mark":
                    handleMark(input);
                    break;
                case "unmark":
                    handleUnmark(input);
                    break;
                case "delete":
                    handleDelete(input);
                    break;
                case "todo":
                    handleTodo(input);
                    break;
                case "deadline":
                    handleDeadline(input);
                    break;
                case "event":
                    handleEvent(input);
                    break;
                case "find":
                    handleFind(input);
                    break;

                default:
                    if (input.trim().equals("todo") || input.trim().equals("deadline")
                            || input.trim().equals("event")) {
                        throw new RoshanException("OOPS!!! The description of a "
                                + input.trim() + " cannot be empty.");
                    }
                    throw new RoshanException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (RoshanException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("OOPS!!! Something went wrong: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Handles the mark command to mark a task as done.
     *
     * @param input The user input containing the task number.
     * @throws RoshanException If the task number is invalid.
     */

    private void handleMark(String input) throws RoshanException {
        int taskNumber = Parser.parseTaskNumber(input, 5);
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
        }
        tasks.get(taskNumber).markAsDone();
        ui.showTaskMarked(tasks.get(taskNumber));
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Handles the unmark command to unmark a task.
     *
     * @param input The user input containing the task number.
     * @throws RoshanException If the task number is invalid.
     */

    private void handleUnmark(String input) throws RoshanException {
        int taskNumber = Parser.parseTaskNumber(input, 7);
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
        }
        tasks.get(taskNumber).markAsNotDone();
        ui.showTaskUnmarked(tasks.get(taskNumber));
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Handles the delete command to delete a task.
     *
     * @param input The user input containing the task number.
     * @throws RoshanException If the task number is invalid.
     */

    private void handleDelete(String input) throws RoshanException {
        int taskNumber = Parser.parseTaskNumber(input, 7);
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new RoshanException("OOPS!!! Task number " + (taskNumber + 1) + " does not exist.");
        }
        Task removedTask = tasks.remove(taskNumber);
        ui.showTaskDeleted(removedTask, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Handles the todo command for a task that needs to be done.
     *
     * @param input The user input containing the task number.
     * @throws RoshanException If the task number is invalid.
     */

    private void handleTodo(String input) throws RoshanException {
        Todo task = Parser.parseTodo(input);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Handles the deadline command for a task with a deadline.
     *
     * @param input The user input containing the task number.
     * @throws RoshanException If the task number is invalid.
     */

    private void handleDeadline(String input) throws RoshanException {
        Deadline task = Parser.parseDeadline(input);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Handles the event command for a task that is an event.
     *
     * @param input The user input containing the task number.
     * @throws RoshanException If the task number is invalid.
     */

    private void handleEvent(String input) throws RoshanException {
        Event task = Parser.parseEvent(input);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Handles the find command for a task that needs to be found.
     *
     * @param input The user input containing the task number.
     * @throws RoshanException If the task number is invalid.
     */

    private void handleFind(String input) throws RoshanException {
        String keyword = Parser.parseFind(input);
        ArrayList<Task> foundTasks = tasks.find(keyword);
        ui.showFoundTasks(foundTasks);
    }

    /**
     * Main entry point of the application.
     *
     * @param args Command line arguments (not used).
     */

    public static void main(String[] args) {
        new Roshan("./data/roshan.txt").run();
    }
}