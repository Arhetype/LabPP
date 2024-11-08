package Laba3_4;

class ObjectProcessor {

    public static SynchronizedProduct synchronizedProduct(Product product) {
        return new SynchronizedProduct(product);
    }
}
