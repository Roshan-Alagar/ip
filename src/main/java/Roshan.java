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
        String repeat = args[0];
        System.out.println(" ");
        if(repeat.equals("bye"))
        {
            System.out.println("Bye. Hope to see you again soon!");
        }
        else
        {
            System.out.println(repeat);
        }
        System.out.println(line);
    }
}
