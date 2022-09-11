package Commands;

import data.CreatorOfDragons;
import storage.DragonVectorStorage;
import utils.Interpreter;

import java.io.File;

/**
 * Команда execute_script file_name : считать и исполнить скрипт из указанного файла.
 * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScript extends Command {
    private File scriptFile;
    private CommandType commandType = CommandType.EXECUTE_SCRIPT;

    public ExecuteScript(){}

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts) {
        if (commandParts.length == 2) {
            File file = new File(commandParts[1]);
            if (file.exists()) {
                if (!file.canRead()) {
                    System.out.println("Отсутствуют права на чтение!");
                } else if (!file.canWrite()) {
                    System.out.println("Отсутствуют права на запись!");
                }
                if (!Interpreter.scriptArray.contains(commandParts[1])) {
                    Interpreter.scriptArray.add(commandParts[1]);
                    scriptFile = file;
                    Interpreter.scriptArray.remove(commandParts[1]);
                    return true;
                } else System.out.println("Данный скрипт уже использован.");
            } else
                System.out.println(
                        "Не удалось получить данные из файла. Проверьте корректность данных."
                );
        }return false;
    }
}
