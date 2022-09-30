package commands;

/**
 * Комманда help : вывести справку по доступным командам.
 */

public class Exit extends Command{
    private CommandType commandType = CommandType.EXIT;


    public void execute() {
        System.out.println("Выход из программы.");
        System.exit(4444);
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
