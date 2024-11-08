package Laba3_4;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

// Интерфейс продукта
interface Product extends Serializable {
    String[] getProducts();
    String getName();
    int getPrice();
    int calculateTotalPrice();
    void output(OutputStream out);
}

class ProductItem implements Product, Serializable {
    private String[] products;
    private String name;
    private int price;

    public ProductItem(String[] products, String name, int price) throws InvalidPriceException {
        if (price <= 0) {
            throw new InvalidPriceException("Цена должна быть больше нуля");
        }
        this.products = products;
        this.name = name;
        this.price = price;
    }

    // Получение списка компонентов продукта
    @Override
    public String[] getProducts() {
        return products;
    }

    // Получение названия продукта
    @Override
    public String getName() {
        return name;
    }

    // Получение цены продукта
    @Override
    public int getPrice() {
        return price;
    }

    // Вычисление общей стоимости продукта
    @Override
    public int calculateTotalPrice() {
        return price * products.length;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "products=" + Arrays.toString(products) +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    // Сравнение объектов
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductItem that = (ProductItem) obj;
        return price == that.price &&
                Arrays.equals(products, that.products) &&
                Objects.equals(name, that.name);
    }

    // Вычисление хэш-кода
    @Override
    public int hashCode() {
        int result = Objects.hash(name, price);
        result = 31 * result + Arrays.hashCode(products);
        return result;
    }

    // Запись продукта в выходной поток
    @Override
    public void output(OutputStream out) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(out)) {
            objectOut.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Десериализация продукта из входного потока
    public static ProductItem deserialize(InputStream in) {
        try (ObjectInputStream objectIn = new ObjectInputStream(in)) {
            return (ProductItem) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
