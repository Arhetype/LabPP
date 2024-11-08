package Laba5.Task1;

class SharedData implements DataStorage {
    private final int[] data = new int[10];

    @Override
    public synchronized void write(int index, int value) {
        data[index] = value;
    }

    @Override
    public synchronized int read(int index) {
        return data[index];
    }
}
