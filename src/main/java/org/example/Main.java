package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        record Product(String name, String category, double price, int stock){}

        List<Product> products = List.of(
                new Product("Laptop",      "Electronics", 999.0,  5),
                new Product("Headphones",  "Electronics",  49.0,  0),
                new Product("Java Book",   "Books",        29.0, 10),
                new Product("T-shirt",     "Clothing",     19.0,  3),
                new Product("Smartwatch",  "Electronics",  79.0,  2)
        );

        Validator<Product> isElectronics =
                p -> p.category.equals("Electronics");

        List<Product> result1 =
                products.stream()
                        .filter(isElectronics::validate)
                        .toList();
        System.out.println(result1);

        Validator<Product> isAffordable =
                p -> p.price < 100.0;

        List<Product> result2 =
                products.stream()
                        .filter(isAffordable.negate()::validate)
                        .toList();
        System.out.println(result2);

        Validator<Product> inStock =
                p -> p.stock > 0;

        List<Product> result3 =
                products.stream()
                        .filter(inStock::validate)
                        .toList();
        System.out.println(result3);

        List<Product> result4 =
                products.stream()
                        .filter(isElectronics.and(inStock)::validate)
                        .toList();
        System.out.println(result4);

        List<Product> result5 =
                products.stream()
                        .filter(isAffordable.negate()::validate)
                        .toList();
        System.out.println(result5);


        Validator<Product> isBooks =
                p -> p.category.equals("Books");

        Validator<Product> isClothing =
                p -> p.category.equals("Clothing");

        List<Product> result6 =
                products.stream()
                        .filter(isBooks.or(isClothing)::validate)
                        .toList();
        System.out.println(result6);
    }
}