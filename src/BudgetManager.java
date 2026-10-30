
import java.io.*;
import java.time.YearMonth;

public class BudgetManager {

    private static final String FILE_PATH = "data/budget.txt";

    public void setBudget(double budget) {

        if (!Double.isFinite(budget) || budget <= 0) {
            System.out.println("Budget must be greater than zero.");
            return;
        }

        File file = new File("data");
        file.mkdirs();

        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            writer.println(budget);
            System.out.println("Monthly budget saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving budget.");
        }
    }

    public double getBudget() {

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return 0;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            if (line != null) {
                double budget = Double.parseDouble(line);

                if (Double.isFinite(budget) && budget > 0) {
                    return budget;
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading budget.");
        }

        return 0;
    }

    public void showBudgetStatus(double monthlyExpenses) {

        double budget = getBudget();

        if (budget <= 0) {
            System.out.println("Please set your monthly budget first.");
            return;
        }

        double remaining = budget - monthlyExpenses;

        System.out.printf("Monthly Budget: Rs.%.2f%n", budget);
        System.out.printf("Monthly Expenses: Rs.%.2f%n",
                monthlyExpenses);

        if (remaining >= 0) {
            System.out.printf("Remaining Budget: Rs.%.2f%n",
                    remaining);
        } else {
            System.out.printf("Budget Exceeded By: Rs.%.2f%n",
                    -remaining);
        }
    }
}
