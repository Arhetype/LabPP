package Laba6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyClass implements MyIterable<Student> {
    private Student[] students;

    public MyClass(Student[] students) {
        this.students = students;
    }

    @Override
    public Iterator<Student> iterator() {
        return new StudentIterator(students);
    }

    private static class StudentIterator implements Iterator<Student> {
        private Student[] students;
        private int currentIndex = 0;

        public StudentIterator(Student[] students) {
            this.students = students;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < students.length;
        }

        @Override
        public Student next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return students[currentIndex++];
        }
    }
}