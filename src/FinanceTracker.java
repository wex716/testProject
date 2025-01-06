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
        System.out.println("Список категорий:");
        for (Category category : categories) {
            return categories;
        }
        return null;
    }


    public void addTransaction(String type, Category category, double amount, LocalDate date, String description) {
        transactions.add(new Transaction(type, category, amount, date, description));
    }

    public void removeTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public List<Transaction> getTransactions() {
        System.out.println("Список транзакций:");
        for (Transaction transaction : transactions) {
            return transactions;
        }
        return null;
    }

    public double getBalance() {
        double income = transactions.stream().filter(item -> item.getType().equals("Доход")).mapToDouble(Transaction::getAmount).sum();
        double expenses = transactions.stream().filter(item -> item.getType().equals("Расход")).mapToDouble(Transaction::getAmount).sum();

        System.out.println("Доход:" + income);
        System.out.println("Расход:" + expenses);
        return income - expenses;
    }

    public List<Transaction> getTransactionByDate(LocalDate startDate, LocalDate endDate) {
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (!transaction.getDate().isBefore(startDate) && !transaction.getDate().isAfter(endDate)) {
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

    public List<Transaction> getTransactionsByCategory(String categoryName) {
        List<Transaction> filteredList = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().getName().equals(categoryName)) {
                filteredList.add(transaction);
            }
        }
        return filteredList;
    }

    public double getIncomes() {
        double incomes = transactions.stream().filter(item -> item.getType().equals("Доход")).mapToDouble(Transaction::getAmount).sum();

        return incomes;
    }

    public double getExpenses() {
        double expenses = transactions.stream().filter(item -> item.getType().equals("Расход")).mapToDouble(Transaction::getAmount).sum();

        return expenses;
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
