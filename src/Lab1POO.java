
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab1POO {

    // Definirea unei clase interne pentru a reprezenta produsele
    static class Product {
        private String name;
        private double price;
        private int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    // Excepție personalizată pentru validarea produselor
    static class InvalidProductException extends Exception {
        public InvalidProductException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        // Masive de variabile
        String[] productNames = {"Apple", "Banana", "Orange"};
        double[] productPrices = {1.2, 0.5, 0.8};
        int[] productQuantities = {10, 20, 0};

        // Lista de produse
        List<Product> productList = new ArrayList<>();

        // Adăugarea produselor în listă și gestionarea excepțiilor
        for (int i = 0; i < productNames.length; i++) {
            try {
                if (productPrices[i] <= 0 || productQuantities[i] < 0) {
                    throw new InvalidProductException("Produs invalid: prețul și cantitatea trebuie să fie pozitive.");
                }
                productList.add(new Product(productNames[i], productPrices[i], productQuantities[i]));
            } catch (InvalidProductException e) {
                System.out.println("Eroare la adăugarea produsului: " + e.getMessage());
            }
        }

        // Afișarea produselor
        System.out.println("Toate produsele:");
        productList.forEach(System.out::println);

        // Utilizarea stream-urilor pentru a filtra produsele disponibile
        List<Product> availableProducts = productList.stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());

        System.out.println("\nProdusele disponibile:");
        availableProducts.forEach(System.out::println);

        // Utilizarea condițiilor pentru a căuta un produs după nume
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIntroduceți numele produsului pentru a-l căuta:");
        String searchProduct = scanner.nextLine();

        Product foundProduct = productList.stream()
                .filter(product -> product.getName().equalsIgnoreCase(searchProduct))
                .findFirst()
                .orElse(null);

        if (foundProduct != null) {
            System.out.println("Produs găsit: " + foundProduct);
        } else {
            System.out.println("Produsul nu a fost găsit.");
        }

        // Afișarea unui masiv de prețuri
        double[] prices = {1.5, 2.3, 3.5};
        System.out.println("\nLista de prețuri a produselor:");
        System.out.println(Arrays.toString(prices));

        // Închidere scanner
        scanner.close();
    }
}
