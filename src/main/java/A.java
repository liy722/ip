import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        String[] list = new String[100];

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
            }

            System.out.println("added: " + input);
            list[count] = input;
            count++;
        }

        scanner.close();
    }
}
