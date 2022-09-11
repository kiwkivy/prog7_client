package Commands;

import data.Checker;
import storage.Storage;

/**
 * Команда remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный.
 */

public class RemoveLower extends Command{
    private int id;
    private CommandType commandType = CommandType.REMOVE_LOWER;

    public RemoveLower(int id) {
        this.id = id;
    }
    public RemoveLower(){}

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts) {
        if (commandParts.length == 2 && Checker.checkIntUpZero(commandParts[1])) {
            id = Integer.parseInt(commandParts[1]);
            return true;
        } else {
            return false;
        }
    }
}
