public class Event extends Task {

    protected String from;
    protected String to;
    protected TaskType type = TaskType.EVENT;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: "+ to +")";
    }
}
