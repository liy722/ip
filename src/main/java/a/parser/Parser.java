package a.parser;
import a.duke_exception.*;
import a.task.*;
import a.ui.*;
import a.tasklist.*;
public class Parser {


    public static boolean parse(String input, Ui ui, TaskList list) {
        try {
            if (input.equals("bye")) {
                ui.bye();
                return false;
            }

            if (input.equals("list")) {
                if (list.size() == 0) {
                    throw new DukeException("Your task list is empty.");
                }
                ui.showMessage("Here are the tasks in your list:");
                list.showTasks();

            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index < 0 || index >= list.size()) {
                    throw new DukeException("Task number out of range.");
                }
                list.get(index).markAsDone();
                ui.showMessage("Nice! I've marked this task as done:\n" + list.get(index));

            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index < 0 || index >= list.size()) {
                    throw new DukeException("Task number out of range.");
                }
                list.get(index).unmark();
                ui.showMessage("OK, I've marked this task as not done yet:\n" + list.get(index));

            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                if (description.isEmpty()) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                list.add(new Todo(description));
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(" " + list.get(list.size() - 1));
                ui.showMessage("Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                list.add(new Deadline(parts[0], parts[1]));
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(" " + list.get(list.size() - 1));
                ui.showMessage("Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                String description = input.substring(6).split(" /from ")[0];
                String[] time = input.substring(6).split(" /from ")[1].split(" /to ");
                list.add(new Event(description, time[0], time[1]));
                ui.showMessage("Got it. I've added this task:");
                ui.showMessage(" " + list.get(list.size() - 1));
                ui.showMessage("Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index < 0 || index >= list.size()) {
                    throw new DukeException("Task number out of range.");
                } else {
                    Task removedTask = list.remove(index);
                    ui.showMessage("Noted. I've removed this task:\n" + " " + removedTask);
                    ui.showMessage("Now you have " + list.size() + " tasks in the list.");
                }

            }

        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid number for marking tasks.");
        }
        return true;
    }

}
