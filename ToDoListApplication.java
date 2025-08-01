package todolist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class ToDoListApplication {
    private List<Task> tasks;

    public ToDoListApplication() {
        tasks = new ArrayList<>();
    }

    public void addTask(String name, LocalDateTime dueDate, LocalDateTime reminder) {
        Task task = new Task(name, dueDate, reminder);
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
    
    public void markTaskDone(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        Task task = tasks.get(index - 1);
        if (task.isDone()) {
            System.out.println("Task is already marked as done.");
        } else {
            task.markDone();
            System.out.println("Task marked as done.");
        }
    }
    
    public void deleteTask(int index) {
        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks.remove(index - 1);
        System.out.println("Task deleted.");
    }
    
    public void checkReminders() {
        LocalDateTime now = LocalDateTime.now();
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (!t.isDone() && t.getReminder() != null && !t.getReminder().isAfter(now)) {
                if (!found) {
                    System.out.println("=== Reminders Due ===");
                    found = true;
                }
                System.out.printf("%d. %s%n", i + 1, t);
            }
        }
        if (!found) {
            System.out.println("No reminders due right now.");
        }
    }
    

    public static void main(String[] args) {
        ToDoListApplication app = new ToDoListApplication();
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Tasks Done");
            System.out.println("4. Remind Tasks");
            System.out.println("5. Delete Tasks");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter due date and time (yyyy-MM-dd HH:mm): ");
                    String dueDateStr = scanner.nextLine();
                    System.out.print("Enter reminder date and time (yyyy-MM-dd HH:mm): ");
                    String reminderDateStr = scanner.nextLine();

                    // Convert String to Date
                    try {
                    	LocalDateTime dueDate = LocalDateTime.parse(dueDateStr, dateFormat);
                    	LocalDateTime reminderDate = LocalDateTime.parse(reminderDateStr, dateFormat);
                        app.addTask(name, dueDate, reminderDate);
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use yyyy-mm-dd HH:mm.");
                    }
                    break;
                case 2:
                    app.displayTasks();
                    break;
                case 3:
                    System.out.print("Enter task number to mark as done: ");
                    int doneTaskNum = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    app.markTaskDone(doneTaskNum);
                    break;    
                case 4:
                	app.checkReminders();
                    break;
                case 5:
                	System.out.print("Enter task number to delete: ");
                    int taskNum = scanner.nextInt();
                    scanner.nextLine();
                    app.deleteTask(taskNum);
                    break; 
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}