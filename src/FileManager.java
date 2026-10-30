
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FileManager {
    private static final String FILE_PATH = "data/expenses.txt";

    public static void saveExpenses(ArrayList<Expense> expenses) {
        File file = new File(FILE_PATH);

        try {
            File parent = file.getParentFile();

            if (parent != null) {
                parent.mkdirs();
            }

            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(file))) {

                for (Expense expense : expenses) {
                    writer.write(expense.toFileString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    public static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return expenses;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", -1);

                if (parts.length == 5) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String title = parts[1];
                        String category = parts[2];
                        double amount = Double.parseDouble(parts[3]);
                        LocalDate date = LocalDate.parse(parts[4]);

                        expenses.add(
                                new Expense(id, title, category, amount, date)
                        );

                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping invalid expense record.");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }

        return expenses;
    }
}
