package Laba5.Task2;

class Writer implements Runnable {
    private Synchronizer synchronizer;

    public Writer(Synchronizer synchronizer) {
        this.synchronizer = synchronizer;
    }

    @Override
    public void run() {
        try {
            while (synchronizer.canWrite()) {
                synchronizer.write(Math.random());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}