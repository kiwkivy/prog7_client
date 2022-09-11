package Commands;

import storage.Storage;

/**
 * Команда remove_by_id id : удалить элемент из коллекции по его id.
 */

public class RemoveById extends Command {
    int id;

    public RemoveById(int id) {
        this.id = id;
    }
}
