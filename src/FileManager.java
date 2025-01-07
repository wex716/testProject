import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;

public class FileManager {
    public static void saveToFile(FinanceTracker financeTracker, String fileName) throws Exception {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Category category : financeTracker.getCategories()) {
                bufferedWriter.write("Категория: " + category.getName());
                bufferedWriter.newLine();
            }

            for (Transaction transaction : financeTracker.getTransactions()) {
                bufferedWriter.write(String.format("Транзакция: %s, %s, %.2f, %s, %s",
                        transaction.getType(),
                        transaction.getCategory(),
                        transaction.getAmount(),
                        transaction.getDate(),
                        transaction.getDescription()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Данные сохранены в файл: " + fileName);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении данных в файл: " + e.getMessage());
        }
    }

    public static FinanceTracker loadFromFile(String fileName) throws Exception {
        FinanceTracker financeTracker = new FinanceTracker();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals("Category")) {
                    financeTracker.addCategory(parts[1]);
                } else if (parts[0].equals("Transaction")) {
                    String[] transactionParts = parts[1].split(",");
                    String type = transactionParts[0];
                    String categoryName = transactionParts[1];
                    double amount = Double.parseDouble(transactionParts[2]);
                    LocalDate date = LocalDate.parse(transactionParts[3]);
                    String description = transactionParts[4];
                    Category category = new Category(categoryName);

                    financeTracker.addTransaction(type, category, amount, date, description);
                }
            }

            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
        return financeTracker;
    }
}
