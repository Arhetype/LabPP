package Laba5.Task2;

class Reader implements Runnable {
    private Synchronizer synchronizer;

    public Reader(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canRead()) {
                synchronizer.read();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
