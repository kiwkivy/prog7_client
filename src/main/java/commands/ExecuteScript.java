package commands;

import utils.Interpreter;

import java.io.File;

/**
 * Команда execute_script file_name : считать и исполнить скрипт из указанного файла.
 * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScript extends Command {
    private String fileName;
    private CommandType commandType = CommandType.EXECUTE_SCRIPT;

    public ExecuteScript() {
    }

    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public boolean validate(String[] commandParts) {
        if (commandParts.length == 2) {
            fileName = commandParts[1];
            return true;
        } else {
            return false;
        }
    }
}
