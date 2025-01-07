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

    public List<Category> getCategories() {
        return categories;
    }


    public void addTransaction(String type, Category category, double amount, LocalDate date, String description) {
        transactions.add(new Transaction(type, category, amount, date, description));
    }

    public void removeTransaction(String name) {
        transactions.removeIf(transaction -> transaction.getCategory().getName().equals(name));
    }

    public List<Transaction> getTransactions() {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println(transactions.get(i));
        }
        return transactions;
    }

    public double getBalance() {
        double income = transactions.stream().filter(item -> item.getType().equals("Доход")).mapToDouble(transaction -> transaction.getAmount()).sum();
        double expenses = transactions.stream().filter(item -> item.getType().equals("Расход")).mapToDouble(transaction -> transaction.getAmount()).sum();

        System.out.println("Доход:" + income);
        System.out.println("Расход:" + expenses);
        return income - expenses;
    }

    public List<Transaction> getTransactionByDate(LocalDate startDate, LocalDate endDate) {
        List<Transaction> filteredList = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (!transactions.get(i).getDate().isBefore(startDate) && !transactions.get(i).getDate().isAfter(endDate)) {
                filteredList.add(transactions.get(i));
            }
        }
        return filteredList;
    }

    public List<Transaction> getTransactionsByCategory(String categoryName) {
        List<Transaction> filteredList = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getCategory().getName().equals(categoryName)) {
                filteredList.add(transactions.get(i));
            }
        }
        return filteredList;
    }

    public double getIncomes() {
        return transactions.stream().filter(item -> item.getType().equals("Доход")).mapToDouble(transaction -> transaction.getAmount()).sum();
    }

    public double getExpenses() {
        return transactions.stream().filter(item -> item.getType().equals("Расход")).mapToDouble(transaction -> transaction.getAmount()).sum();
    }

    public double getAverageIncome() {
        double sumIncomes = 0;
        int count = 0;

        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getCategory().getName().equals("Доход")) {
                sumIncomes += transactions.get(i).getAmount();
                count++;
            }
        }
        return sumIncomes / count;
    }

}
