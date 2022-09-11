package Commands;

import data.Dragon;
import storage.Storage;

/**
 * Команда insert_at index name age : добавить новый элемент в заданную позицию.
 */

public class InsertAt extends Command {
    private int index;
    private CommandType commandType = CommandType.INSERT_AT;
    private Dragon dragon;

    public InsertAt(int index, Dragon dragon) {
        this.index = index;
        this.dragon = dragon;
    }
}
