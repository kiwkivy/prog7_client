package commands;

/**
 * Команда clear : очистить коллекцию.
 */

public class Clear extends Command {
    private CommandType commandType = CommandType.CLEAR;

    public CommandType getCommandType() {
        return commandType;
    }
}
