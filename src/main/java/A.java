
public class A {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/duke.txt");
        TaskList list = new TaskList(storage);

        ui.welcome();

        Boolean isExit = true;
        while (isExit) {
            String input = ui.read();
            isExit = Parser.parse(input, ui, list);
        }
    }
}
