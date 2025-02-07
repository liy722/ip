package a.task;

public abstract class  Task {
    String description;
    boolean isDone;
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    /**
     * Converts the task to a savable string format for file storage.
     *
     * @return A formatted string representing the task.
     */
    public abstract String toSaveFormat();

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A formatted string representation of the task.
     */
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
