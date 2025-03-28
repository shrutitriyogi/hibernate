package hibernate1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Prices for each item
        int rice = 100, dal = 150, atta = 200;
        Scanner sc = new Scanner(System.in);

        System.out.println("SHOP ENTRIES");
        System.out.println("-----------------------------");

        // Get customer ID
        System.out.print("Enter customer ID: ");
        String cusID = sc.nextLine();

        int totalPrice = 0;
        StringBuilder items = new StringBuilder();

        while (true) {
            System.out.println("Available items: \n1. Rice - ₹100\n2. Dal - ₹150\n3. Atta - ₹200");
            System.out.print("Enter item number: ");
            int val = sc.nextInt();

            if (val == 1) {
                totalPrice += rice;
                items.append("Rice, ");
            } else if (val == 2) {
                totalPrice += dal;
                items.append("Dal, ");
            } else if (val == 3) {
                totalPrice += atta;
                items.append("Atta, ");
            } else {
                System.out.println("Error! Invalid item number.");
                continue;  // Skip invalid input
            }

            // Ask if they want to add more items
            System.out.print("Want to add more items? (y/n): ");
            sc.nextLine();  // Consume newline
            String ch = sc.nextLine();

            if (!ch.equalsIgnoreCase("y")) {
                break;
            }
        }
        sc.close();

        // Remove the last comma
        if (items.length() > 0) {
            items.setLength(items.length() - 1);
        }

        // Display the order details
        System.out.println("\nCustomer ID: " + cusID);
        System.out.println("Items Purchased: " + items);
        System.out.println("Total Price: ₹" + totalPrice);

        // Hibernate Session - Save Order
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Creating and saving order
        Shop order = new Shop(cusID, items.toString(), totalPrice);
        session.persist(order);

        tx.commit();
        session.close();
        factory.close();

        System.out.println("Record saved successfully using Hibernate!");
    }
}
