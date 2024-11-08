package Laba6;

import java.util.Iterator;

public class MyStaticMethods {
    private static MyInterfaceFactory<?> factory = new MyClassFactory();

    public static <T> void setMyInterfaceFactory(MyInterfaceFactory<T> factory) {
        MyStaticMethods.factory = factory;
    }

    public static <T> T createInstance() {
        return (T) factory.createInstance();
    }

    //Создает и возвращает "немодифицируемую" обертку для переданного объекта
    public static <T> MyIterable<T> unmodifiable(MyIterable<T> obj) {
        return new UnmodifiableDecorator<>(obj);
    }

    private static class UnmodifiableDecorator<T> implements MyIterable<T> {
        private MyIterable<T> obj;

        public UnmodifiableDecorator(MyIterable<T> obj) {
            this.obj = obj;
        }

        @Override
        public Iterator<T> iterator() {
            return new UnmodifiableIterator<>(obj.iterator());
        }

        /**
         * Приватный внутренний класс UnmodifiableIterator,
         * который реализует интерфейс Iterator.
         * Обеспечивает неизменяемый итератор для объекта типа MyIterable.
         */
        private static class UnmodifiableIterator<T> implements Iterator<T> {
            private Iterator<T> iterator;

            public UnmodifiableIterator(Iterator<T> iterator) {
                this.iterator = iterator;
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return iterator.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }
    private static class MyClassFactory implements MyInterfaceFactory<MyClass> {
        @Override
        public MyClass createInstance() {
            return new MyClass(new Student[]{
                    new Student("John", 5),
                    new Student("Anna", 3),
                    new Student("Peter", 2),
                    new Student("Sophia", 5),
                    new Student("Robert", 4)
            });
        }
    }
}