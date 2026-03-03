# Roshan User Guide

Roshan is a **task management chatbot** that helps you keep track of your todos, deadlines, and events through a simple command-line interface.

## Quick Start

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest `roshan.jar` from [here](https://github.com/Roshan-Alagar/ip/releases).
3. Copy the file to a folder where you want to store your task data.
4. Open a command terminal, navigate to the folder containing the jar file.
5. Run the command `java -jar roshan.jar` to start the application.
6. Type commands in the terminal and press Enter to execute them.
7. Refer to the Features section below for details of each command.

## Features

> **Notes about the command format:**
> - Words in `UPPER_CASE` are parameters to be supplied by you.
> - Items in square brackets are optional.

### Adding a Todo: `todo`

Adds a todo task to your task list.

Format: `todo DESCRIPTION`

Example:
```
todo read book
```

Expected output:
```
 ____________________________________________________________
 Got it. I've added this task:
   [T][ ] read book
 Now you have 1 tasks in the list.
 ____________________________________________________________
```

---

### Adding a Deadline: `deadline`

Adds a task with a deadline to your task list.

Format: `deadline DESCRIPTION /by DEADLINE`

Example:
```
deadline return book /by Sunday
```

Expected output:
```
 ____________________________________________________________
 Got it. I've added this task:
   [D][ ] return book (by: Sunday)
 Now you have 2 tasks in the list.
 ____________________________________________________________
```

---

### Adding an Event: `event`

Adds an event with start and end times to your task list.

Format: `event DESCRIPTION /from START /to END`

Example:
```
event project meeting /from Mon 2pm /to 4pm
```

Expected output:
```
 ____________________________________________________________
 Got it. I've added this task:
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
 Now you have 3 tasks in the list.
 ____________________________________________________________
```

---

### Listing all tasks: `list`

Shows all tasks in your task list.

Format: `list`

Example output:
```
 ____________________________________________________________
 Here are the tasks in your list:
 1.[T][ ] read book
 2.[D][ ] return book (by: Sunday)
 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
 ____________________________________________________________
```

---

### Marking a task as done: `mark`

Marks a task as completed.

Format: `mark TASK_NUMBER`

Example:
```
mark 1
```

Expected output:
```
 ____________________________________________________________
 Nice! I've marked this task as done:
   [T][X] read book
 ____________________________________________________________
```

---

### Unmarking a task: `unmark`

Marks a completed task as not done.

Format: `unmark TASK_NUMBER`

Example:
```
unmark 1
```

Expected output:
```
 ____________________________________________________________
 OK, I've marked this task as not done yet:
   [T][ ] read book
 ____________________________________________________________
```

---

### Deleting a task: `delete`

Deletes a task from your task list.

Format: `delete TASK_NUMBER`

Example:
```
delete 2
```

Expected output:
```
 ____________________________________________________________
 Noted. I've removed this task:
   [D][ ] return book (by: Sunday)
 Now you have 2 tasks in the list.
 ____________________________________________________________
```

---

### Finding tasks: `find`

Finds all tasks containing a specific keyword.

Format: `find KEYWORD`

Example:
```
find book
```

Expected output:
```
 ____________________________________________________________
 Here are the matching tasks in your list:
 1.[T][ ] read book
 2.[E][ ] book club (from: Mon to: Tue)
 ____________________________________________________________
```

---

### Exiting the program: `bye`

Exits the application. All tasks are automatically saved.

Format: `bye`

Example output:
```
 ____________________________________________________________
 Bye. Hope to see you again soon!
 ____________________________________________________________
```

---

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| Add Todo | `todo DESCRIPTION` | `todo read book` |
| Add Deadline | `deadline DESCRIPTION /by DEADLINE` | `deadline return book /by Sunday` |
| Add Event | `event DESCRIPTION /from START /to END` | `event meeting /from Mon 2pm /to 4pm` |
| List | `list` | `list` |
| Mark | `mark TASK_NUMBER` | `mark 1` |
| Unmark | `unmark TASK_NUMBER` | `unmark 1` |
| Delete | `delete TASK_NUMBER` | `delete 2` |
| Find | `find KEYWORD` | `find book` |
| Exit | `bye` | `bye` |

---

## FAQ

**Q: How do I save my tasks?**  
A: Tasks are automatically saved to `data/roshan.txt` after every change. No manual saving required!

**Q: Can I edit the data file directly?**  
A: Yes, advanced users can edit `data/roshan.txt` directly. However, be careful to maintain the correct format to avoid data corruption.

**Q: Where is my data stored?**  
A: Task data is stored in `data/roshan.txt` in the same folder as the jar file.

---

## Known Issues

- Task numbers in commands are 1-indexed (first task is 1, not 0).
- Dates and times are stored as plain text strings, not validated or parsed.

---

*Created by Roshan Alagar*
