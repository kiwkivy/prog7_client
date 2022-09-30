package commands;


/**
 * Родительский класс для команд.
 */

public abstract class Command{
    private int port;
    protected String username;
    protected String password;

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

    public void setUser(String username, String password) {
        this.username=username;
        this.password=password;
    }
}
