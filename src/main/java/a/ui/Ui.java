package a.ui;
import java.util.Scanner;

/**
 * Handles all user interactions with the chatbot.
 * It manages user input and displays messages.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Constructs a new Ui instance and initializes the scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void welcome() {
        System.out.println("Hello! I'm A");
        System.out.println("What can I do for you?\n");
    }
    /**
     * Reads and returns user input from the console.
     *
     * @return The next line of user input.
     */
    public String read(){
        return scanner.nextLine();
    }
    /**
     * Displays a message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    /**
     * Displays a goodbye message and closes the scanner.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
