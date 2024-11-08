package Laba5.Task1;

import java.util.concurrent.ThreadLocalRandom;

class ThreadA extends Thread {
    private final DataStorage storage;

    public ThreadA(DataStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int value = ThreadLocalRandom.current().nextInt(1, 100); // Генерация случайного значения
            storage.write(i, value);
            System.out.println("Write: " + value + " to position " + i);
            try {
                Thread.sleep(100); // Симуляция некоторой работы
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}