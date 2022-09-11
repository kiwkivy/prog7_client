package Commands;

import data.Dragon;

/**
 * Команда add name age : добавить новый элемент в коллекцию.
 */
public class Add extends Command {
    private CommandType commandType = CommandType.ADD;
    private Dragon dragon;

    public Add(Dragon dragon) {
        this.dragon = dragon;
    }
}

