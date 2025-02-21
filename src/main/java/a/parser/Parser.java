package a.parser;

import a.exception.DukeException;
import a.task.*;
import a.tasklist.TaskList;
import a.ui.Ui;

/**
 * Handles parsing and executing user commands for task management.
 */

public class Parser {


    /**
     * Parses and executes the user command.
     *
     * @param input The user input command.
     * @param ui The UI instance for displaying messages.
     * @param list The TaskList instance containing tasks.
     * @return {@code false} if the command is "bye" (to exit the program), otherwise {@code true}.
     */
    public static void parse(String input, Ui ui, TaskList list) {
        assert ui != null : "UI should not be null";
        try {
            if (input.equals("bye")) {
                ui.bye();
            }
            else if (input.startsWith("find ")) {
                String keyword = input.substring(5);
                ui.showMessage("Here are the matching tasks in your list:");
                int count = 1;
                for (Task task : list.getTasks()) {
                    if (task.getDescription().contains(keyword)) {
                        ui.showMessage(count + "." + task);
                        count++;
                    }
                }
                if (count == 1) {
                    ui.showMessage("No matching tasks found.");
                }
            } else if (input.equals("list")) {
                if (list.size() == 0) {
                    throw new DukeException("Your task list is empty.");
                }
                String s = "";
                for (int i = 0; i < list.size(); i++) {
                    s = s + (i + 1) + "." + list.get(i) +"\n";
                }
                ui.showMessage("Here are the tasks in your list:\n" + s);


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
                ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) + "\n" + "Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                list.add(new Deadline(parts[0], parts[1]));
                ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) +"\n" + "Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                String description = input.substring(6).split(" /from ")[0];
                String[] time = input.substring(6).split(" /from ")[1].split(" /to ");
                list.add(new Event(description, time[0], time[1]));
                ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) + "\n" + "Now you have " + list.size() + " tasks in the list.");
            } else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index < 0 || index >= list.size()) {
                    throw new DukeException("Task number out of range.");
                } else {
                    Task removedTask = list.remove(index);
                    ui.showMessage("Noted. I've removed this task:\n" + " " + removedTask + "\n" + "Now you have " + list.size() + " tasks in the list.");
                }
            } else if (input.startsWith("fixed ")) {
                String[] parts = input.substring(6).split(" /for ");
                if (parts.length < 2) {
                    throw new DukeException("Please provide a valid description and duration.");
                }
                String description = parts[0];
                int duration;
                try {
                    duration = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid number for the duration.");
                }
                list.add(new FixedDurationTask(description, duration));
                ui.showMessage("Got it. I've added this task:\n" + " " + list.get(list.size() - 1) + "\n" + "Now you have " + list.size() + " tasks in the list.");
            }
            else {
                ui.showMessage("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }


        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } catch (NumberFormatException e) {
            ui.showError("Please enter a valid number for marking tasks.");
        }

    }

}
