import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        Task[] list = new Task[100];

        System.out.println("Hello! I'm A");
        System.out.println("What can I do for you?\n");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + list[i]);
                }
                continue;
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                list[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list[index]);
                continue;
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                list[index].unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + list[index]);
                continue;
            } else if (input.startsWith("todo ")) {
                list[count] = new Todo(input.substring(5));
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + list[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                list[count] = new Deadline(parts[0], parts[1]);
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + list[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                String description = input.substring(6).split(" /from ")[0];
                String[] time = input.substring(6).split(" /from ")[1].split(" /to ");
                list[count] = new Event(description, time[0], time[1]);
                System.out.println("Got it. I've added this task:");
                System.out.println(" " + list[count]);
                count++;
                System.out.println("Now you have " + count + " tasks in the list.");
            }
        }

        scanner.close();
    }
}
