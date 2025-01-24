public class Todo extends Task{
    protected TaskType type = TaskType.TODO;
    Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
