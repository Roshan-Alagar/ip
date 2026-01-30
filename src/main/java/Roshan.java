public class Roshan {
    public static void main(String[] args) {
        String logo = " ____            _                 \n"
                + "|  _ \\ ___  ___| |__   __ _ _ __  \n"
                + "| |_) / _ \\/ __| '_ \\ / _` | '_ \\ \n"
                + "|  _ < (_) \\__ \\ | | | (_| | | | |\n"
                + "|_| \\_\\___/|___/_| |_|\\__,_|_| |_|\n";
        String line = " ____________________________________________________________";
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello I'm Roshan" );
        System.out.println("What can I do for you?");
        System.out.println(line);

        Task[] tasks = new Task[100];
        int counter = 0;

        for(int i = 0; i < args.length; i++) {
            String repeat = args[i];
            System.out.println(line);
            if(repeat.equals("bye"))
            {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
            }
            else if(repeat.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for(int j = 0; j < counter; j++ )
                {
                    System.out.println("     " + (j + 1) + ". " + tasks[j]);
                }
                System.out.println(line);
            }
            else if(repeat.equals("mark"))
            {
                i++;
                int taskNumber = Integer.parseInt(args[i]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println(line);
            }
            else if(repeat.equals("unmark"))
            {
                i++;
                int taskNumber = Integer.parseInt(args[i]) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskNumber]);
                System.out.println(line);
            }
            else
            {
                Task newTask = new Task(repeat);
                tasks[counter] = newTask;
                counter++;
                System.out.println("     added: " + repeat);
                System.out.println(line);
            }
            System.out.println(" ");
        }
    }
}
