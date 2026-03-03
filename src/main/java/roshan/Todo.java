package roshan;

/**
 * Represents a todo task without any date/time.
 */

public class Todo extends Task {

    /**
     * Creates a new Todo task with the given description.
     *
     * @param description The description of the todo task.
     */

    public Todo(String description) {

        super(description);
    }

    /**
     * Returns the string representation of this todo task.
     *
     * @return String with [T] prefix, status icon, and description.
     */
    
    @Override

    public String toString() {

        return "[T]" + super.toString();
    }
}