package roshan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    /**
     * Creates a new Deadline task with the given description and deadline.
     * Attempts to parse the deadline as a date in yyyy-MM-dd format.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date/time as a string.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            this.byDate = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            this.byDate = null; // Keep as string if not a valid date
        }
    }

    /**
     * Returns the string representation of this deadline task.
     * If the deadline is a valid date, formats it as MMM dd yyyy.
     *
     * @return String with [D] prefix, status, description, and deadline.
     */
    @Override

    public String toString() {
        String formattedDate = by;
        if (byDate != null) {
            formattedDate = byDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Gets the deadline date for file storage.
     * Returns the original string format.
     *
     * @return The deadline in original format.
     */
    public String getBy() {
        return by;
    }
}
