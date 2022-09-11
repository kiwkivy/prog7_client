package Commands;

import storage.Storage;

/**
 * Команда reorder : отсортировать коллекцию в порядке, обратном нынешнему.
 */

public class Reorder extends Command {
    private CommandType commandType = CommandType.REORDER;

    public CommandType getCommandType() {
        return commandType;
    }
}
