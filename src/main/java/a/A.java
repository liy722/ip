package a;

import a.parser.Parser;
import a.storage.Storage;
import a.tasklist.TaskList;
import a.ui.Ui;


/**
 * The main entry point for the A chatbot application.
 * It initializes the UI, storage, and task list, and then runs the command loop.
 */
public class A {
    /**
     * The main method that starts the Duke chatbot.
     * It initializes UI, storage, and task management, then continuously processes user input.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        TaskList list = new TaskList(storage.load());

        ui.welcome();

        Boolean isExit = true;
        while (isExit) {
            String input = ui.read();
            isExit = Parser.parse(input, ui, list);
            storage.save(list.getTasks());
        }
    }
}
