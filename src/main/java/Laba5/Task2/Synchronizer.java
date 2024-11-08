package Laba5.Task2;

class Synchronizer {
    private DataStorage dataStorage;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public Synchronizer(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public double read() throws InterruptedException {
        double val;
        synchronized(lock) {
            if (!canRead()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = dataStorage.getElement(current++);
            System.out.println("Read: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void write(double val) throws InterruptedException {
        synchronized(lock) {
            if (!canWrite()) throw new InterruptedException();
            while (set)
                lock.wait();
            dataStorage.setElement(current, val);
            System.out.println("Write: " + val);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canRead() {
        return current < dataStorage.getSize();
    }

    public boolean canWrite() {
        return (!set && current < dataStorage.getSize()) || (set && current < dataStorage.getSize() - 1);
    }
}