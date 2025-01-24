
class Task {
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

    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}
