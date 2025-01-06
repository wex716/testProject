import java.util.ArrayList;
import java.util.List;

public class FinanceManager {
    private List<Category> categories;
    private List<Transaction> transactions;

    public FinanceManager() {
        categories = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void addCategory(String name){
        categories.add(new Category(name));
    }

    public void removeCategory(String name){
        categories.removeIf(category -> category.getName().equals(name));
    }

    public List<Category> getCategories(){
        return categories;
    }


    public void addTransaction(String type, Category category, double amount, String description){
        transactions.add(new Transaction(type, category, amount, description));
    }

    public void removeTransaction(Transaction transaction){
        transactions.remove(transaction);
    }

    public List<Transaction> getTransactions(){
        return transactions;
    }



}
