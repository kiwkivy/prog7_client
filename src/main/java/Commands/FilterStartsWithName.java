package Commands;

import storage.DragonVectorStorage;
import storage.Storage;

/**
 * Команда filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки.
 */

public class FilterStartsWithName extends Command {
    private String name;
    private CommandType commandType = CommandType.FILTER_STARTS_WITH_NAME;

    public FilterStartsWithName(String name) {
        this.name = name;
    }
}
