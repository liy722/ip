package a.storage;
import java.io.*;
import java.util.ArrayList;

import a.task.*;


public class Storage {

    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
        ensureFileExists();
    }

    private void ensureFileExists() {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException ignored) {}
    }



    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(parseTask(line));
            }
        } catch (IOException ignored) {}
        return tasks;
    }

    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = switch (parts[0]) {
            case "T" -> new Todo(parts[2]);
            case "D" -> new Deadline(parts[2], parts[3]);
            case "E" -> new Event(parts[2], parts[3], parts[4]);
            default -> null;
        };
        if (task != null && parts[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                bw.write(task.toSaveFormat());
                bw.newLine();
            }
        } catch (IOException ignored) {}
    }
}
