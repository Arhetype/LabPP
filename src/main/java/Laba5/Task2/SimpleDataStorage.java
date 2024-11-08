package Laba5.Task2;

class SimpleDataStorage implements DataStorage {
    private double[] data;

    public SimpleDataStorage(int size) {
        data = new double[size];
    }

    @Override
    public double getElement(int index) {
        return data[index];
    }

    @Override
    public void setElement(int index, double value) {
        data[index] = value;
    }

    @Override
    public int getSize() {
        return data.length;
    }
}