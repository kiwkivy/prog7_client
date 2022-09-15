package commands;

/**
 * Команда info : вывести в стандартный поток вывода информацию о коллекции
 * (тип, дата инициализации, количество элементов и т.д.).
 */

public class Info extends Command{
    private CommandType commandType = CommandType.INFO;

    public CommandType getCommandType() {
        return commandType;
    }
}
