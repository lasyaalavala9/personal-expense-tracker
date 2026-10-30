
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

    // Add a new expense
    public void addExpense(String title, String category,
                           double amount, LocalDate date) {

        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            return;
        }

        if (category == null || category.trim().isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }

        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Error: Amount must be greater than zero.");
            return;
        }

        if (date == null) {
            System.out.println("Error: Date cannot be empty.");
            return;
        }

        Expense expense = new Expense(
                nextId, title.trim(), category.trim(), amount, date
        );

        expenses.add(expense);
        FileManager.saveExpenses(expenses);
        nextId++;

        System.out.println("Expense added successfully!");
    }

    // View all expenses
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

    // Search expenses by category
    public void searchByCategory(String category) {

        if (category == null || category.trim().isEmpty()) {
            System.out.println("Error: Category cannot be empty.");
            return;
        }

        boolean found = false;

        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category.trim())) {
                System.out.println(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No expenses found in this category.");
        }
    }

    // Delete an expense using its ID
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

    // Calculate total expenses
    public void showTotalExpenses() {

        double total = 0;

        for (Expense expense : expenses) {
            total += expense.getAmount();
        }

        System.out.printf("Total expenses: Rs.%.2f%n", total);
    }

    // Display expenses grouped by category
    public void showCategorySummary() {

        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }

        ArrayList<String> categories = new ArrayList<>();

        for (Expense expense : expenses) {
            String category = expense.getCategory();

            boolean exists = false;

            for (String existingCategory : categories) {
                if (existingCategory.equalsIgnoreCase(category)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
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

    // Calculate expenses for a particular month
    public double getMonthlyExpenses(YearMonth month) {

        if (month == null) {
            return 0;
        }

        double total = 0;

        for (Expense expense : expenses) {
            if (YearMonth.from(expense.getDate()).equals(month)) {
                total += expense.getAmount();
            }
        }

        return total;
    }

    // Display monthly expense summary
    public void showMonthlySummary(YearMonth month) {

        if (month == null) {
            System.out.println("Error: Month cannot be empty.");
            return;
        }

        double total = getMonthlyExpenses(month);

        System.out.printf(
                "Total expenses for %s: Rs.%.2f%n",
                month, total
        );
    }
}


