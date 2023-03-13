public class ProductManager {
    private ProductRepository prod;

    public ProductManager(ProductRepository prod) {
        this.prod = prod;
    }

    public void add(Product item) {
        prod.save(item);
    }

    public Product[] getItems() {
        Product[] all = prod.findAll();
        Product[] reversed = new Product[all.length];
        for (int i = 0; i < reversed.length; i++) {
            reversed[i] = all[all.length - 1 - i];
        }
        return reversed;
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0]; // тут будем хранить подошедшие запросу продукты
        for (Product product : prod.findAll()) {
            if (matches(product, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = product;
                result = tmp;
            }
        }
        return result;
    }


    // метод определения соответствия товара product запросу search
    public boolean matches(Product product, String search) {
//            if (product.getName().contains(search)) {
//                return true;
//            } else {
//                return false;
//            }
//            // или в одну строку:
        return product.getName().contains(search);
    }
}

