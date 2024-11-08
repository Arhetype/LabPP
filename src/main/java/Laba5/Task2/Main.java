package Laba5.Task2;

public class Main {
    public static void main(String[] args) {
        DataStorage dataStorage = new SimpleDataStorage(10);
        Synchronizer synchronizer = new Synchronizer(dataStorage);

        Thread writerThread = new Thread(new Writer(synchronizer));
        Thread readerThread = new Thread(new Reader(synchronizer));

        writerThread.start();
        readerThread.start();
    }
}