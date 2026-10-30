
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        BudgetManager budgetManager = new BudgetManager();

        int choice = 0;

        while (choice != 10) {

            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Search by Category");
            System.out.println("4. Delete Expense");
            System.out.println("5. Show Total Expenses");
            System.out.println("6. Category Summary");
            System.out.println("7. Monthly Summary");
            System.out.println("8. Set Monthly Budget");
            System.out.println("9. View Budget Status");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                        System.out.print("Enter expense title: ");
                        String title = sc.nextLine().trim();

                        System.out.print("Enter category: ");
                        String category = sc.nextLine().trim();

                        System.out.print("Enter amount: ");
                        double amount = Double.parseDouble(
                                sc.nextLine().trim()
                        );

                        if (title.isEmpty() || category.isEmpty()
                                || !Double.isFinite(amount) || amount <= 0) {
                            System.out.println(
                                    "Enter valid details and a positive amount."
                            );
                            break;
                        }

                        System.out.print(
                                "Enter date (YYYY-MM-DD), or press Enter for today: "
                        );

                        String dateInput = sc.nextLine().trim();

                        LocalDate date = dateInput.isEmpty()
                                ? LocalDate.now()
                                : LocalDate.parse(dateInput);

                        manager.addExpense(
                                title, category, amount, date
                        );
                        break;

                    case 2:
                        manager.viewExpenses();
                        break;

                    case 3:
                        System.out.print("Enter category to search: ");
                        String searchCategory = sc.nextLine().trim();

                        manager.searchByCategory(searchCategory);
                        break;

                    case 4:
                        System.out.print("Enter expense ID to delete: ");
                        int id = Integer.parseInt(
                                sc.nextLine().trim()
                        );

                        manager.deleteExpense(id);
                        break;

                    case 5:
                        manager.showTotalExpenses();
                        break;

                    case 6:
                        manager.showCategorySummary();
                        break;

                    case 7:
                        System.out.print("Enter month (YYYY-MM): ");

                        YearMonth month = YearMonth.parse(
                                sc.nextLine().trim()
                        );

                        manager.showMonthlySummary(month);
                        break;

                    case 8:
                        System.out.print("Enter your monthly budget: ");

                        double budget = Double.parseDouble(
                                sc.nextLine().trim()
                        );

                        budgetManager.setBudget(budget);
                        break;

                    case 9:
                        YearMonth currentMonth = YearMonth.now();

                        double monthlyExpenses =
                                manager.getMonthlyExpenses(currentMonth);

                        System.out.println(
                                "\nBudget Status for " + currentMonth
                        );

                        budgetManager.showBudgetStatus(
                                monthlyExpenses
                        );
                        break;

                    case 10:
                        System.out.println(
                                "Thank you for using the app!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Try again."
                        );
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Please enter a valid number."
                );

            } catch (DateTimeParseException e) {
                System.out.println(
                        "Invalid date or month format. "
                        + "Use YYYY-MM-DD or YYYY-MM."
                );
            }
        }

        sc.close();
    }
}
