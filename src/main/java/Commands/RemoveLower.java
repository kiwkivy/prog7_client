package Commands;

import storage.Storage;

/**
 * Команда remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный.
 */

public class RemoveLower extends Command{
    private int id;
    private CommandType commandType = CommandType.REMOVE_LOWER;

    public RemoveLower() {
        this.id = id;
    }
}
