package commands;

/**
 * Команда filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки.
 */

public class FilterStartsWithName extends Command {
    private String name;
    private CommandType commandType = CommandType.FILTER_STARTS_WITH_NAME;


    public FilterStartsWithName(){}

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts){
        if (commandParts.length == 2) {
            name = commandParts[1];
            return true;
        }else{
            return false;}
    }
}
