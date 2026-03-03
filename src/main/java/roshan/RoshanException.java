package roshan;

/**
 * Represents exceptions specific to the Roshan application.
 * Used to handle errors that occur during task management operations.
 */

public class RoshanException extends Exception {

    /**
     * Creates a new RoshanException with the specified error message.
     *
     * @param message The error message describing what went wrong.
     */

    public RoshanException(String message){

        super(message);
    }
}
