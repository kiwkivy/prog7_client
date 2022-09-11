package Commands;

import storage.Storage;

/**
 * Команда update id name age : обновить значение элемента коллекции, id которого равен заданному.
 */

public class Update extends Command {
    private int id;
    private CommandType commandType = CommandType.UPDATE;

    public Update(int id) {
        this.id = id;
    }
}
