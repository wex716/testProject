import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private List<Category> categories;
    private List<Transaction> transactions;

    public FinanceManager() {
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

    public double getIncomesByPeriod(LocalDate startDate, LocalDate endDate) {
        double incomes = 0;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getType().equals("Доход") && transactions.get(i).getDate().isAfter(startDate) && transactions.get(i).getDate().isBefore(endDate)) {
                incomes += transactions.get(i).getAmount();
            }
        }
        System.out.println(incomes);
        return incomes;
    }

    public double getExpensesByPeriod(LocalDate startDate, LocalDate endDate) {
        double expenses = 0;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getType().equals("Дасход") && transactions.get(i).getDate().isAfter(startDate) && transactions.get(i).getDate().isBefore(endDate)) {
                expenses += transactions.get(i).getAmount();
            }
        }
        return expenses;
    }

    public void getExpensesAndIncomesByPeriod(LocalDate startDate, LocalDate endDate) {

        double incomes = 0;
        double expenses = 0;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getDate().isAfter(startDate) && transactions.get(i).getDate().isBefore(endDate)) {
                if (transactions.get(i).getType().equals("расход")) {
                    expenses += transactions.get(i).getAmount();
                } else {
                    incomes += transactions.get(i).getAmount();
                }
            }
        }
        System.out.println("Расходы за указанный период:" + expenses);
        System.out.println("Доходы за указанный период:" + incomes);
    }

    public void transactionByCategory(String categoryName){
        for (int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getCategory().getName().equals(categoryName)){
                System.out.println(transactions.get(i));
            }
        }
    }

    public void expensesByCategory(){

    }
}



