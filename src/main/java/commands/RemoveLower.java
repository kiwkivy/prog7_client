package commands;

import data.CreatorOfDragons;
import data.Dragon;

/**
 * Команда remove_lower id : удалить из коллекции все элементы, меньшие, чем заданный.
 */

public class RemoveLower extends Command{
    private Dragon dragon;
    private CommandType commandType = CommandType.REMOVE_LOWER;

    public RemoveLower(){}

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts) {
        if (commandParts.length == 1) {
            dragon = CreatorOfDragons.create();
            return true;
        }else{return false;}
    }
}
