package hibernate1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_orders")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;

    @Column(name = "customer_id")
    private String customerId;

    @Column(name = "items")
    private String items;

    @Column(name = "total_price")
    private int totalPrice;

    // Constructors
    public Shop() {}

    public Shop(String customerId, String items, int totalPrice) {
        this.customerId = customerId;
        this.items = items;
        this.totalPrice = totalPrice;
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getItems() { return items; }
    public void setItems(String items) { this.items = items; }
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
}
