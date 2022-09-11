package Commands;

/**
 * Комманда help : вывести справку по доступным командам.
 */

public class Help extends Command{
    private CommandType commandType = CommandType.HELP;

    public CommandType getCommandType() {
        return commandType;
    }
}
