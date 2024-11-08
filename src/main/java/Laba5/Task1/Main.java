package Laba5.Task1;

public class Main {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();
        ThreadA threadA = new ThreadA(sharedData);
        ThreadB threadB = new ThreadB(sharedData);

        threadA.setPriority(Thread.MIN_PRIORITY);
        threadB.setPriority(Thread.MAX_PRIORITY);

        threadA.start();
        threadB.start();
    }
}
