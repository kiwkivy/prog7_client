package Commands;

import storage.Storage;

/**
 * Команда show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении.
 */

public class Show extends Command{
    private CommandType commandType = CommandType.SHOW;

    public Show() {
    }
}
