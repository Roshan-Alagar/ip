package roshan;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Manages a list of tasks.
 * Provides operations to add, delete, and retrieve tasks.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new empty TaskList.
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList with the given list of tasks.
     *
     * @param tasks The initial list of tasks.
     */

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to remove (0-based).
     * @return The removed task.
     */

    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve (0-based).
     * @return The task at the specified index.
     */

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */

    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return The ArrayList containing all tasks.
     */

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds all tasks containing the specified keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return ArrayList of tasks that match the keyword.
     */

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
    /**
     * Finds all tasks occurring on a specific date.
     *
     * @param date The date to search for in yyyy-MM-dd format.
     * @return ArrayList of tasks occurring on that date.
     */
    public ArrayList<Task> findByDate(String date) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        try {
            LocalDate searchDate = LocalDate.parse(date);
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.byDate != null && deadline.byDate.equals(searchDate)) {
                        matchingTasks.add(task);
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if ((event.fromDate != null && !event.fromDate.isAfter(searchDate))
                            && (event.toDate != null && !event.toDate.isBefore(searchDate))) {
                        matchingTasks.add(task);
                    }
                }
            }
        } catch (DateTimeParseException e) {
            // Invalid date format, return empty list
        }
        return matchingTasks;
    }
}