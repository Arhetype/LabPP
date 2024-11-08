import java.lang.Math;

public class SecondProgramm {
    private double[] elements;

    public SecondProgramm(int size) {
        elements = new double[size];
    }

    // Полуgчение значения элемента вектора по индексу
    public double getElement(int index) {
        return elements[index];
    }

    // Изменение значения элемента вектора по индексу
    public void setElement(int index, double value) {
        elements[index] = value;
    }

    // Получение длины вектора (количества его элементов)
    public int length() {
        return elements.length;
    }

    // Поиск минимального значения вектора
    public double findMin() {
        double min = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] < min) {
                min = elements[i];
            };       }
        return min;
    }

    // Поиск максимального значения вектора
    public double findMax() {
        double max = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i] > max) {
                max = elements[i];
            }
        }
        return max;
    }

    // Вывод элементов вектора
    public void print() {
        for (double element : elements) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Евклидовая норма
    public double euclideanNorm() {
        double sumOfSquares = 0.0;
        for (double element : elements) {
            sumOfSquares += element * element;
        }
        return Math.sqrt(sumOfSquares);
    }

    // Умножения вектора на число
    public double[] multiply(double scalar) {
        double[] result = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            result[i] = elements[i] * scalar;
        }
        return result;
    }

    // Сложения двух векторов
    public double[] add(SecondProgramm otherVector) {
        double[] result2 = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            result2[i] = elements[i] + otherVector.getElement(i);
        }
        return result2;
    }

    // Поиск скалярного произведения двух векторов
    public double scalarMultiplay(SecondProgramm otherVector) {
        if (elements.length != otherVector.length()) {
            throw new IllegalArgumentException("Векторы должны иметь одинаковую длину");
        }

        double result = 0;
        for (int i = 0; i < elements.length; i++) {
            result += elements[i] * otherVector.getElement(i);
        }
        return result;
    }

    // Сортировка вектора по возрастанию
    public void sortAscending() {
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = 0; j < elements.length - 1 - i; j++) {
                if (elements[j] > elements[j + 1]) {
                    double temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        SecondProgramm vector1 = new SecondProgramm(4);
        vector1.setElement(0, 3);
        vector1.setElement(1, 2);
        vector1.setElement(2, 1);
        vector1.setElement(3, 0);

        SecondProgramm vector2 = new SecondProgramm(4);
        vector2.setElement(0, 5);
        vector2.setElement(1, 7);
        vector2.setElement(2, 6);
        vector2.setElement(3, 4);

        System.out.println("Вектор 1:");
        vector1.print();
        System.out.println("Вектор 2:");
        vector2.print();
        System.out.println();

        System.out.println("Минимальное значение 1: " + vector1.findMin());
        System.out.println("Максимальное значение 1: " + vector1.findMax() + "\n");

        System.out.println("Минимальное значение 2: " + vector2.findMin());
        System.out.println("Максимальное значение 2: " + vector2.findMax() + "\n");

        System.out.println("Длина 1: " + vector1.length());
        System.out.println("Длина 2: " + vector2.length() + "\n");

        System.out.println("Индекс 1ого = 2: " + vector1.getElement(2));
        System.out.println("Индекс 2ого = 3: " + vector2.getElement(3) + "\n");

        System.out.println("Евклидова норма 1: " + vector1.euclideanNorm());
        System.out.println("Евклидова норма 2: " + vector2.euclideanNorm() + "\n");

        double scalar = 4.5;
        double[] result = vector1.multiply(scalar);
        System.out.println("Результат умножения вектора 1 на число: ");
        for (double value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println();

        double[] result2 = vector1.add(vector2);
        System.out.println("Результат сложения векторов:");
        for (double element : result2) {
            System.out.print(element + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("Скалярное произведение двух векторов: " + vector1.scalarMultiplay(vector2) + "\n");

        System.out.println("Сортировка вектора 1 по возрастанию:");
        vector1.sortAscending();
        vector1.print();
    }
}