public class Product {
    protected int id;
    protected String name;
    protected int price;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Product(int id, String productName, int price) {
        this.id = id;
        this.name = productName;
        this.price = price;
    }
}