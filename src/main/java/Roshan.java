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

        String[] tasks = new String[100];
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
                for(int j = 0; j < counter; j++ )
                {
                    System.out.println("     " + (j + 1) + ". " + tasks[j]);
                }
                System.out.println(line);
            }
            else
            {
                tasks[counter] = repeat;
                counter++;
                System.out.println("     added: " + repeat);
                System.out.println(line);
            }
            System.out.println(" ");
        }
    }
}
