package commands;

import data.Checker;
import data.CreatorOfDragons;
import data.Dragon;

/**
 * Команда update id name age : обновить значение элемента коллекции, id которого равен заданному.
 */

public class Update extends Command {
    private int id;
    private CommandType commandType = CommandType.UPDATE;
    private Dragon dragon;

    public Update(){}

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts) {
        if (commandParts.length == 2 && Checker.checkIntUpZero(commandParts[1])) {
            id = Integer.parseInt(commandParts[1]);
            dragon = CreatorOfDragons.create();
            return true;
        } else {
            return false;
        }
    }
}
