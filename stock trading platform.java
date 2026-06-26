import java.util.HashMap;
import java.util.Scanner;

class StockTradingPlatform {
    private double balance;
    private HashMap<String, Integer> portfolio;
    private HashMap<String, Double> market;

    public StockTradingPlatform(double balance) {
        this.balance = balance;
        portfolio = new HashMap<>();
        market = new HashMap<>();

        market.put("TCS", 3500.0);
        market.put("INFY", 1500.0);
        market.put("RELIANCE", 2800.0);
        market.put("WIPRO", 450.0);
    }

    public void displayMarket() {
        System.out.println("\nAvailable Stocks:");
        for (String stock : market.keySet()) {
            System.out.println(stock + " : ₹" + market.get(stock));
        }
    }

    public void buyStock(String stock, int quantity) {
        if (!market.containsKey(stock)) {
            System.out.println("Stock not found!");
            return;
        }

        double cost = market.get(stock) * quantity;

        if (cost > balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        balance -= cost;
        portfolio.put(stock, portfolio.getOrDefault(stock, 0) + quantity);

        System.out.println("Purchased " + quantity + " shares of " + stock);
    }

    public void sellStock(String stock, int quantity) {
        if (!portfolio.containsKey(stock) || portfolio.get(stock) < quantity) {
            System.out.println("Not enough shares!");
            return;
        }

        double revenue = market.get(stock) * quantity;
        balance += revenue;

        portfolio.put(stock, portfolio.get(stock) - quantity);

        if (portfolio.get(stock) == 0) {
            portfolio.remove(stock);
        }

        System.out.println("Sold " + quantity + " shares of " + stock);
    }

    public void showPortfolio() {
        System.out.println("\nPortfolio:");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (String stock : portfolio.keySet()) {
                System.out.println(stock + " : " + portfolio.get(stock) + " shares");
            }
        }
        System.out.println("Balance: ₹" + balance);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StockTradingPlatform trader = new StockTradingPlatform(100000);

        while (true) {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    trader.displayMarket();
                    break;

                case 2:
                    System.out.print("Enter Stock Name: ");
                    String buyStock = sc.next().toUpperCase();
                    System.out.print("Enter Quantity: ");
                    int buyQty = sc.nextInt();
                    trader.buyStock(buyStock, buyQty);
                    break;

                case 3:
                    System.out.print("Enter Stock Name: ");
                    String sellStock = sc.next().toUpperCase();
                    System.out.print("Enter Quantity: ");
                    int sellQty = sc.nextInt();
                    trader.sellStock(sellStock, sellQty);
                    break;

                case 4:
                    trader.showPortfolio();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}