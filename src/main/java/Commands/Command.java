package Commands;

import data.Dragon;
import storage.DragonVectorStorage;
import storage.Storage;

import java.util.Collection;

/**
 * Родительский класс для команд.
 */

public abstract class Command{

    public Command(){
    }

    public abstract CommandType getCommandType();

    public boolean validate(String[] commandParts){
        if (commandParts.length == 1) {
            return true;
        }else{return false;}
    }
}
