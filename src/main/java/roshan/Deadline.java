package roshan;

/**
 * Represents a task with a deadline.
 */

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline task with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string.
     */

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of this deadline task.
     *
     * @return String with [D] prefix, status, description, and deadline.
     */

    @Override

    public String toString() {

        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}