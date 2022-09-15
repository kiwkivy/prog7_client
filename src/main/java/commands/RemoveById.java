package commands;

import data.Checker;

/**
 * Команда remove_by_id id : удалить элемент из коллекции по его id.
 */

public class RemoveById extends Command {
    int id;
    private CommandType commandType = CommandType.REMOVE_BY_ID;

    public RemoveById() {
    }

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
