package commands;

import data.CreatorOfDragons;
import data.Dragon;

/**
 * Команда add name age : добавить новый элемент в коллекцию.
 */
public class Add extends Command {
    private CommandType commandType = CommandType.ADD;
    private Dragon dragon;

    public Add() {
    }

    public Add(Dragon dragon) {
        this.dragon = dragon;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts){
        if (commandParts.length == 1) {
            dragon = CreatorOfDragons.create();
            return true;
        }else{return false;}
    }
}

