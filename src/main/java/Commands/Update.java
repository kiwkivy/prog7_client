package Commands;

import data.Checker;
import storage.Storage;

/**
 * Команда update id name age : обновить значение элемента коллекции, id которого равен заданному.
 */

public class Update extends Command {
    private int id;
    private CommandType commandType = CommandType.UPDATE;

    public Update(){}

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
