package Laba3_4;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

class Goods implements Product, Serializable {
        private String[] products;
        private String name;
        private int price;

        public Goods(String[] products, String name, int price) throws InvalidPriceException {
                if (price <= 0) {
                        throw new InvalidPriceException("Цена должна быть больше нуля");
                }
                this.products = products;
                this.name = name;
                this.price = price;
        }

        // Получение массива продуктов
        @Override
        public String[] getProducts() {
                return products;
        }

        // Получение названия товара
        @Override
        public String getName() {
                return name;
        }

        // Получение цены товара
        @Override
        public int getPrice() {
                return price;
        }

        // Вычисление общей стоимости товара
        @Override
        public int calculateTotalPrice() {
                return price * products.length;
        }

        // Вывод информации о товаре в виде строки
        @Override
        public String toString() {
                return "Goods{" +
                        "products=" + Arrays.toString(products) +
                        ", name='" + name + '\'' +
                        ", price=" + price +
                        '}';
        }

        // Сравнения объектов класса
        @Override
        public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Goods goods = (Goods) obj;
                return price == goods.price &&
                        Arrays.equals(products, goods.products) &&
                        Objects.equals(name, goods.name);
        }

        // Вычисление хэш-кода объекта
        @Override
        public int hashCode() {
                int result = Objects.hash(name, price);
                result = 31 * result + Arrays.hashCode(products);
                return result;
        }

        // Запись объекта в выходной поток
        @Override
        public void output(OutputStream out) {
                try (ObjectOutputStream objectOut = new ObjectOutputStream(out)) {
                        objectOut.writeObject(this);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        // Десериализация объекта из входного потока
        public static Goods deserialize(InputStream in) {
                try (ObjectInputStream objectIn = new ObjectInputStream(in)) {
                        return (Goods) objectIn.readObject();
                } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                }
        }
}
