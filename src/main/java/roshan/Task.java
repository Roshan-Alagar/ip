package roshan;

/**
 * Represents a task with a description and completion status.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return "X" if task is done, " " otherwise.
     */

    public String getStatusIcon() {

        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as done.
     */

    public void markAsDone() {

        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */

    public void markAsNotDone() {

        this.isDone = false;
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String showing status icon and description.
     */

    @Override

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}