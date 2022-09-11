package Commands;

import data.Checker;
import data.CreatorOfDragons;
import data.Dragon;
import storage.Storage;
import utils.Interpreter;

/**
 * Команда insert_at index name age : добавить новый элемент в заданную позицию.
 */

public class InsertAt extends Command {
    private int index;
    private CommandType commandType = CommandType.INSERT_AT;
    private Dragon dragon;

    public InsertAt(){}

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts){
        try{
            index = Integer.parseInt(commandParts[1]);
            dragon = CreatorOfDragons.create();
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
