package a.ui;
import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println("Hello! I'm A");
        System.out.println("What can I do for you?\n");
    }
    public String read(){
        return scanner.nextLine();
    }
    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
