package Laba3_4;

// Исключение для ошибок ввода цены
class InvalidPriceException extends Exception {
    public InvalidPriceException(String message) {
        super(message);
    }
}