import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinanceTracker {
    private List<Category> categories;
    private List<Transaction> transactions;

    public FinanceTracker() {
        categories = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addCategory(String name) {
        categories.add(new Category(name));
    }

    public void removeCategory(String name) {
        categories.removeIf(category -> category.getName().equals(name));
        transactions.removeIf(transaction -> transaction.getCategory().getName().equals(name));
    }

    public void getCategories() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(categories.get(i));
        }
    }


    public void addTransaction(String type, Category category, double amount, LocalDate date, String description) {
        transactions.add(new Transaction(type, category, amount, date, description));
    }

    public void removeTransaction(String name) {
        transactions.removeIf(transaction -> transaction.getCategory().getName().equals(name));
    }

    public void getTransactions() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
    }

    public void getBalance() {
        double income = transactions.stream().filter(item -> item.getType().equals("Доход")).mapToDouble(transaction -> transaction.getAmount()).sum();
        double expenses = transactions.stream().filter(item -> item.getType().equals("Расход")).mapToDouble(transaction -> transaction.getAmount()).sum();

        System.out.println("Доход:" + income);
        System.out.println("Расход:" + expenses);
    }

    public void getTransactionByDate(LocalDate startDate, LocalDate endDate) {
        List<Transaction> filteredListByDate = transactions.stream().filter(transaction -> !transaction.getDate().isBefore(startDate) && !transaction.getDate().isAfter(endDate)).toList();

        System.out.println(filteredListByDate);
    }

    public void getTransactionsByCategory(String categoryName) {
        List<Transaction> filteredList = transactions;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getCategory().getName().equals(categoryName)) {
                filteredList.add(transactions.get(i));
            }
        }
    }


    public String getListInTableView() {
        String output = "";
        output = String.format("%10s%12s%10s%12s%20s\n", "Категория", "Тип", "Деньги", "Дата", "Описание");

        if (transactions.size() > 0) {
            for (int i = 0; i < transactions.size(); i++) {
                Transaction currentTransaction = transactions.get(i);

                output += String.format("%10s%12s%15s%12s%20s\n", currentTransaction.getType(), currentTransaction.getCategory(), currentTransaction.getAmount(), currentTransaction.getDate(), currentTransaction.getDescription());
            }
        } else {
            output += "Список пуст\n";
        }
        return output;
    }

    public void saveToFile(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < categories.size(); i++) {
                bufferedWriter.write("Категория: " + categories.get(i).getName());
                bufferedWriter.newLine();
            }

            for (int i = 0; i < transactions.size(); i++) {
                bufferedWriter.write(transactions.get(i).getType());
                bufferedWriter.newLine();

                bufferedWriter.write(transactions.get(i).getCategory().toString());
                bufferedWriter.newLine();

                bufferedWriter.write(Double.toString(transactions.get(i).getAmount()));
                bufferedWriter.newLine();

                bufferedWriter.write(transactions.get(i).getDate().toString());
                bufferedWriter.newLine();

                bufferedWriter.write(transactions.get(i).getDescription());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Данные сохранены в файл: " + fileName);
        } catch (Exception e) {
            System.out.println("Ошибка при сохранении данных в файл: " + e.getMessage());
        }
    }


    public void loadFromFile(String fileName) {
        FinanceTracker financeTracker = new FinanceTracker();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int size = Integer.parseInt(bufferedReader.readLine());
            categories.clear();

            for (int i = 0; i < size; i++) {
                String type = bufferedReader.readLine();
                String categoryName = bufferedReader.readLine();
                double amount = Double.parseDouble(bufferedReader.readLine());
                LocalDate date = LocalDate.parse(bufferedReader.readLine());
                String description = bufferedReader.readLine();

                Category category = new Category(categoryName);

                financeTracker.addTransaction(type, category, amount, date, description);

            }

            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }
}
