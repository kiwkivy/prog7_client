package commands;

/**
 * Родительский класс для команд.
 */

public abstract class Command{
    private int port;
    public Command(){
    }

    public abstract CommandType getCommandType();

    public boolean validate(String[] commandParts){
        if (commandParts.length == 1) {
            return true;
        }else{return false;}
    }

    public void setPort(int port) {
        this.port = port;
    }
}
