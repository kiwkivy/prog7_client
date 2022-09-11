package Commands;

import data.Checker;
import data.Color;
import storage.DragonVectorStorage;

/**
 * Команда count_by_color color : вывести количество элементов, значение поля color которых равно заданному.
 */

public class CountByColor extends Command {
    private Color color;
    private CommandType commandType = CommandType.COUNT_BY_COLOR;

    public CountByColor() {
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts){
        if (commandParts.length == 2 && Checker.checkColor(commandParts[1])) {
            color = Color.valueOf(commandParts[1]);
            return true;
        }
        return false;
    }
}
