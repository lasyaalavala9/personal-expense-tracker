
import java.time.LocalDate;

public class Expense {
    private int id;
    private String title;
    private String category;
    private double amount;
    private LocalDate date;

    public Expense(int id, String title, String category,
                   double amount, LocalDate date) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + category
                + " | Rs." + String.format("%.2f", amount)
                + " | " + date;
    }

    public String toFileString() {
        return id + "|" + title + "|" + category
                + "|" + amount + "|" + date;
    }
}
