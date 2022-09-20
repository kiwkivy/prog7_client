package commands;

import java.io.IOException;
import java.nio.channels.DatagramChannel;

import static client.Client.sendMessage;

/**
 * Комманда help : вывести справку по доступным командам.
 */

public class Exit extends Command{
    private CommandType commandType = CommandType.EXIT;

    public void execute() {
        try {
            sendMessage(DatagramChannel.open(), new Exit());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Выход из программы.");
        System.exit(4444);
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }
}
