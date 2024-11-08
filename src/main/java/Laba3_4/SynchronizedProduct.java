package Laba3_4;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;

class SynchronizedProduct implements Product, Serializable {
    private final Product product;

    public SynchronizedProduct(Product product) {
        this.product = product;
    }

    @Override
    public synchronized String[] getProducts() {
        return product.getProducts();
    }

    @Override
    public synchronized String getName() {
        return product.getName();
    }

    @Override
    public synchronized int getPrice() {
        return product.getPrice();
    }

    @Override
    public synchronized int calculateTotalPrice() {
        return product.calculateTotalPrice();
    }

    @Override
    public synchronized void output(OutputStream out) {
        product.output(out);
    }

    @Override
    public String toString() {
        return product.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SynchronizedProduct that = (SynchronizedProduct) obj;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}

