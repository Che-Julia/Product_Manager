import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    Book item1 = new Book(1, "book1", 1000);
    Smartphone item2 = new Smartphone(2, "smartphone1", 10_000);
    Book item3 = new Book(3, "book2", 4000);
    Smartphone item4 = new Smartphone(4, "smartphone2", 15_000);


    @Test
    public void saveProduct() {
        ProductRepository prod = new ProductRepository();
        prod.save(item1);
        prod.save(item2);
        prod.save(item4);

        Product[] expected = {item1, item2, item4};
        Product[] actual = prod.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void removeProduct() {
        ProductRepository prod = new ProductRepository();
        prod.save(item1);
        prod.save(item2);
        prod.removeById(item1.getId());

        Product[] expected = {item2};
        Product[] actual = prod.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReverseAllItems() {
        ProductRepository prod = new ProductRepository();
        ProductManager manager = new ProductManager(prod);
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);

        Product[] expected = {item1, item2, item3};
        Product[] actual = prod.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByWhenFewProductsFound() {
        ProductRepository prod = new ProductRepository();
        ProductManager manager = new ProductManager(prod);
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);

        Product[] expected = {item1, item3};
        Product[] actual = manager.searchBy("book");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void sortTest() {
        ProductRepository prod = new ProductRepository();
        ProductManager manager = new ProductManager(prod);
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);

        Product[] expected = {item4, item3, item2, item1};
        Product[] actual = manager.getItems();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByWhenOneItemFound() {
        ProductRepository prod = new ProductRepository();
        ProductManager manager = new ProductManager(prod);

        manager.add(item1);
        manager.add(item2);
        manager.add(item3);

        Product[] expected = {item1};
        Product[] actual = manager.searchBy("book1");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchByWhenItemNotFound() {
        ProductRepository prod = new ProductRepository();
        ProductManager manager = new ProductManager(prod);

        manager.add(item1);
        manager.add(item2);
        manager.add(item3);

        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("book18");

        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void removeProd() {
        ProductRepository prod = new ProductRepository();
        prod.save(item1);
        prod.save(item2);


        Assertions.assertThrows(NotFoundException.class,
                () -> prod.removeById(3)
                );
    }
}
