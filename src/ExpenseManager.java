
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class ExpenseManager {
    private ArrayList<Expense> expenses;
    private int nextId;

    public ExpenseManager() {
        expenses = FileManager.loadExpenses();
        nextId = 1;

        for (Expense expense : expenses) {
            if (expense.getId() >= nextId) {
                nextId = expense.getId() + 1;
            }
        }
    }

    public void addExpense(String title, String category,
                           double amount, LocalDate date) {
        Expense expense = new Expense(
                nextId++, title, category, amount, date
        );

        expenses.add(expense);
        FileManager.saveExpenses(expenses);

        System.out.println("Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }

        System.out.println("\nID | Title | Category | Amount | Date");
        System.out.println("-----------------------------------------------");

        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void searchByCategory(String category) {
        boolean found = false;

        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in this category.");
        }
    }

    public void deleteExpense(int id) {
        boolean removed = expenses.removeIf(
                expense -> expense.getId() == id
        );

        if (removed) {
            FileManager.saveExpenses(expenses);
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Expense ID not found.");
        }
    }

    public void showTotalExpenses() {
        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        System.out.printf("Total expenses: Rs.%.2f%n", total);
    }

    public void showCategorySummary() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        ArrayList<String> categories = new ArrayList<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();

            if (!categories.contains(category)) {
                categories.add(category);
            }
        }

        System.out.println("\nCategory Summary");

        for (String category : categories) {
            double total = 0;

            for (Expense expense : expenses) {
                if (expense.getCategory().equalsIgnoreCase(category)) {
                    total += expense.getAmount();
                }
            }

            System.out.printf("%s: Rs.%.2f%n", category, total);
        }
    }

    public void showMonthlySummary(YearMonth month) {
        double total = 0;

        for (Expense expense : expenses) {
            if (YearMonth.from(expense.getDate()).equals(month)) {
                total += expense.getAmount();
            }
        }

        System.out.printf(
                "Total expenses for %s: Rs.%.2f%n",
                month, total
        );
    }
}
