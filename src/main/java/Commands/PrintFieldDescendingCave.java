package Commands;

import data.Dragon;

/**
 * Команда print_field_descending_cave : вывести значения поля cave всех элементов в порядке убывания.
 */

public class PrintFieldDescendingCave extends Command {
    private CommandType commandType = CommandType.PRINT_FIELD_DESCENDING_CAVE;

    public CommandType getCommandType() {
        return commandType;
    }
}
