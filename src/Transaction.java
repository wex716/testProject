import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
    private String type;
    private Category category;
    private double amount;
    private LocalDate date;
    private String description;



    public Transaction(String type, Category category, double amount, LocalDate date, String description) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
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
