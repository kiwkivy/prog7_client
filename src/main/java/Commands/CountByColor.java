package Commands;

import data.Color;
import storage.DragonVectorStorage;

/**
 * Команда count_by_color color : вывести количество элементов, значение поля color которых равно заданному.
 */

public class CountByColor extends Command {
    private Color color;
    private CommandType commandType = CommandType.COUNT_BY_COLOR;

    public CountByColor(Color color) {this.color = color;}
}
