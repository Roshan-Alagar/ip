package roshan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws RoshanException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        // Create directory if it doesn't exist
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        // If file doesn't exist, return empty list
        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                try {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    // Skip corrupted lines
                    System.out.println("Warning: Skipping corrupted line: " + line);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            throw new RoshanException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    private Task parseTask(String line) throws RoshanException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new RoshanException("Invalid task format");
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = null;
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new RoshanException("Invalid deadline format");
            }
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            if (parts.length < 5) {
                throw new RoshanException("Invalid event format");
            }
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            throw new RoshanException("Unknown task type: " + taskType);
        }

        if (task != null && isDone) {
            task.markAsDone();
        }
        return task;
    }

    public void saveTasks(ArrayList<Task> tasks) throws RoshanException {
        File file = new File(filePath);

        // Create directory if it doesn't exist
        File directory = file.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(formatTask(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RoshanException("Error saving tasks to file: " + e.getMessage());
        }
    }

    private String formatTask(Task task) {
        String taskType = "";
        String details = "";

        if (task instanceof Todo) {
            taskType = "T";
        } else if (task instanceof Deadline) {
            taskType = "D";
            details = " | " + ((Deadline) task).by;
        } else if (task instanceof Event) {
            taskType = "E";
            details = " | " + ((Event) task).from + " | " + ((Event) task).to;
        }

        String isDone = task.isDone ? "1" : "0";
        return taskType + " | " + isDone + " | " + task.description + details;
    }
}
