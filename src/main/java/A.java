import java.util.Scanner;
import java.util.ArrayList;
public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm A");
        System.out.println("What can I do for you?\n");

        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }

                if (input.equals("list")) {
                    if(count==0){
                        throw new DukeException("Your task list is empty.");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    continue;
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    if (index < 0 || index >= count) {
                        throw new DukeException("Task number out of range.");
                    }
                    list.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + list.get(index));
                    continue;
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= count) {
                        throw new DukeException("Task number out of range.");
                    }
                    list.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" + list.get(index));
                    continue;
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5);
                    if (description.isEmpty()) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    list.add(new Todo(description));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.substring(9).split(" /by ");
                    list.add(new Deadline(parts[0], parts[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                } else if (input.startsWith("event ")) {
                    String description = input.substring(6).split(" /from ")[0];
                    String[] time = input.substring(6).split(" /from ")[1].split(" /to ");
                    list.add(new Event(description, time[0], time[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + list.get(count));
                    count++;
                    System.out.println("Now you have " + count + " tasks in the list.");
                }else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    if (index < 0 || index >= count) {
                        throw new DukeException("Task number out of range.");
                    } else {
                        Task removedTask = list.remove(index);
                        count--;
                        System.out.println("Noted. I've removed this task:\n" + " "+removedTask);
                        System.out.println("Now you have " + count + " tasks in the list.");
                    }
                }
                else{
                    throw new DukeException("please enter a valid task");
                }
            }catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for marking tasks.");
            }
        }

        scanner.close();
    }
}
