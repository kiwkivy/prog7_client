package exceptions;

/**
 * Выбрасывается при попытке добавить в коллекцию некорректный элемент.
 */
public class ElementNotValidException extends Exception {

    public ElementNotValidException(final String message) {
        super(message);
    }

}
