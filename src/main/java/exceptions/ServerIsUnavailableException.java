package exceptions;

public class ServerIsUnavailableException extends RuntimeException{
    @Override
    public void printStackTrace(){
        System.err.println("Сервер временно недоступен");
    }
}
