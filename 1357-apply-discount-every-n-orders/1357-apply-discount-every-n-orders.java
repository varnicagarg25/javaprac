import java.util.HashMap;
import java.util.Map;

class Cashier {
    private final int n;
    private final int discount;
    private final Map<Integer, Integer> priceMap;
    private int customerCount;

    public Cashier(int n, int discount, int[] products, int[] prices) {
        this.n = n;
        this.discount = discount;
        this.customerCount = 0;
        this.priceMap = new HashMap<>();

        for (int i = 0; i < products.length; i++) {
            priceMap.put(products[i], prices[i]);
        }
    }
    
    public double getBill(int[] product, int[] amount) {
        customerCount++;
        double total = 0.0;

        for (int i = 0; i < product.length; i++) {
            total += (double) priceMap.get(product[i]) * amount[i];
        }

        if (customerCount % n == 0) {
            total = total * ((100.0 - discount) / 100.0);
        }

        return total;
    }
}