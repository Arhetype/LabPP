package Laba5.Task1;

class ThreadB extends Thread {
    private final DataStorage storage;

    public ThreadB(DataStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int value = storage.read(i);
            System.out.println("Read: " + value + " from position " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
