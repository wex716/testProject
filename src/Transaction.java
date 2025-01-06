import java.time.LocalDate;

public class Transaction {
    private String type;
    private Category category;
    private double amount;
    private String description;


    public Transaction(String type, Category category, double amount, String description) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public Category getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", category=" + category +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }
}
