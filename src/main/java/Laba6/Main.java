package Laba6;

public class Main {
    public static void main(String[] args) {
        MyClass myClass = MyStaticMethods.createInstance();

        for (Student student : myClass) {
            System.out.println(student);
        }

        MyIterable<Student> unmodifiableClass = MyStaticMethods.unmodifiable(myClass);
    }
}
