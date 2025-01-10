import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FinanceTracker financeTracker = new FinanceTracker();
        Scanner scanner = new Scanner(System.in);

        String fileName = "data.txt";

        try {
            financeTracker = FileManager.loadFromFile(fileName);
            System.out.println("Файл успешно загружен");
        } catch (Exception e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }

        while (true) {
            System.out.print("1. Добавить категорию. \n2. Удалить категорию. \n3. Просмотреть все категории. \n4. Добавить транзакцию. \n5. Удалить транзакцию. \n6. Просмотреть все транзакции \n7. Вывод общего баланса. \n8. Вывод доходов и расходов за указанный период времени. \n9. Выход \n--!Выберите действие:!-- ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введите имя категории: ");
                    String categoryName = scanner.nextLine();

                    financeTracker.addCategory(categoryName);

                    skipLine();

                    break;
                case "2":
                    System.out.print("Введите имя категории для удаления: ");
                    String removeCategoryName = scanner.nextLine();

                    financeTracker.removeCategory(removeCategoryName);

                    skipLine();

                    break;
                case "3":
                    System.out.print("Все категории: ");
                    List<Category> categories = financeTracker.getCategories();

                    System.out.println(categories.toString());

                    skipLine();

                    break;
                case "4":
                    System.out.print("Введите тип транзакции (Доход, Расход): ");
                    String type = scanner.nextLine();

                    System.out.print("Введите категорию: ");
                    String transCategoryName = scanner.nextLine();

                    Category category = new Category(transCategoryName);

                    System.out.print("Введите сумму: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    System.out.print("Введите дату в формате ('2025-12-12'): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());

                    System.out.print("Введите описание: ");
                    String description = scanner.nextLine();

                    financeTracker.addTransaction(type, category, amount, date, description);

                    skipLine();

                    break;
                case "5":
                    System.out.print("Введите имя транзакции для удаления: ");
                    String removeTransactionName = scanner.nextLine();

                    financeTracker.removeTransaction(removeTransactionName);

                    skipLine();

                    break;
                case "6":
                    System.out.print("Список транзакций: ");
                    List<Transaction> filteredTransaction = financeTracker.getTransactions();

                    System.out.println(filteredTransaction.toString());

                    skipLine();

                    break;
                case "7":
                    System.out.println("Общий баланс: ");
                    financeTracker.getBalance();

                    skipLine();

                    break;
                case "8":
                    System.out.print("Введите начальную дату транзакции в формате ('2025-12-12'): ");
                    LocalDate startDate = LocalDate.parse(scanner.nextLine());

                    System.out.print("Введите конечную дату транзакции в формате ('2025-12-12'): ");
                    LocalDate endDate = LocalDate.parse(scanner.nextLine());

                    List<Transaction> filteredListByCategory = financeTracker.getTransactionByDate(startDate, endDate);

                    System.out.println(filteredListByCategory.toString());

                    skipLine();

                    break;
                case "9":
                    try {
                        FileManager.saveToFile(financeTracker, fileName);
                        System.out.println("Данные успешно сохранены");
                    } catch (Exception e) {
                        System.out.println("Ошибка сохранения данных: " + e.getMessage());
                    }
                    System.exit(0);

                    break;
                default:
                    System.out.println("Такого действия нет, выберите чето другое");
            }
        }
    }

    private static void skipLine(){
        System.out.println("");
    }

}