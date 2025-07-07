package todolist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean done;
    private LocalDateTime dueDate;
    private LocalDateTime reminder;

    public Task(String name, LocalDateTime dueDate, LocalDateTime reminder) {
        this.name = name;
        this.done = false;
        this.dueDate = dueDate;
        this.reminder = reminder;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReminder() {
        return reminder;
    }

    @Override
    public String toString() {
    	String status = done ? "[✓] " : "[ ] ";
        String result = status + name;
        if (dueDate != null) {
            result += " (due: " + dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        }
        if (reminder != null) {
            result += " (reminder: " + reminder.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
        }
        return result;
    }
}

