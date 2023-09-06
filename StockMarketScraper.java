import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.util.Scanner;

public class StockMarketScraper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // ask the user for a stock symbol
            System.out.print("Enter a stock symbol: ");
            String stockSymbol = scanner.nextLine().toUpperCase();//useful later
            String url = "https://finance.yahoo.com/quote/" + stockSymbol;

            // Connect to the URL
            Document document = Jsoup.connect(url).get();

            // Locate element containing stock price
            Element priceElement = document.select("fin-streamer.Fw\\(b\\):nth-child(1)").first();

            // Extract and print the stock price
            if (priceElement != null) {
                String stockPrice = priceElement.text();
                System.out.println("Stock Price for " + stockSymbol + ": " + stockPrice);
            } else {
                System.out.println("Unable to find stock price for " + stockSymbol);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}