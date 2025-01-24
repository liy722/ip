import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        Task[] list = new Task[100];

        System.out.println("Hello! I'm A");
        System.out.println("What can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + list[i]);
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
            }

            System.out.println("added: " + input);
            list[count] = new Task(input);
            count++;
        }

        scanner.close();
    }
}
