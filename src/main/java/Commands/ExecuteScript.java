package Commands;

import storage.DragonVectorStorage;

import java.io.File;

/**
 * Команда execute_script file_name : считать и исполнить скрипт из указанного файла.
 * В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class ExecuteScript extends Command {
    private File scriptFile;
    private CommandType commandType = CommandType.EXECUTE_SCRIPT;

    public ExecuteScript(File scriptFile){
        this.scriptFile = scriptFile;
    }

}
