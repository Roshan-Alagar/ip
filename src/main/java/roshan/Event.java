package roshan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with start and end times.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Creates a new Event task with the given description and time period.
     * Attempts to parse the dates in yyyy-MM-dd format.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        try {
            this.fromDate = LocalDate.parse(from);
        } catch (DateTimeParseException e) {
            this.fromDate = null;
        }
        try {
            this.toDate = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.toDate = null;
        }
    }

    /**
     * Returns the string representation of this event task.
     * If dates are valid, formats them as MMM dd yyyy.
     *
     * @return String with [E] prefix, status, description, and time period.
     */
    @Override
    public String toString() {
        String formattedFrom = from;
        String formattedTo = to;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        if (fromDate != null) {
            formattedFrom = fromDate.format(formatter);
        }
        if (toDate != null) {
            formattedTo = toDate.format(formatter);
        }

        return "[E]" + super.toString() + " (from: " + formattedFrom + " to: " + formattedTo + ")";
    }

    /**
     * Gets the start date for file storage.
     *
     * @return The start date in original format.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end date for file storage.
     *
     * @return The end date in original format.
     */
    public String getTo() {
        return to;
    }
}
