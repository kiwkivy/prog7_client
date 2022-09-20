package utils;

import com.google.gson.JsonIOException;
import commands.*;
import data.Dragon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interpreter {
    private static boolean isConsoleMod;
    public static File file;
    public static List<String> scriptArray = new ArrayList<>();
    public static ArrayList<Command> listOfCommands = new ArrayList<>();


    public Interpreter() {
        this.isConsoleMod = true;
    }

    public Interpreter(File file) {
        this.isConsoleMod = false;
        this.file = file;
    }

    /**
     * Метод, реализующий работу интерпретатора. Интерпретатор работает в двух режимах: консольном и скриптовом.
     */

    public Command getCommand() {
        getListOfCommands();
        FileWorker worker = new FileWorker();
        String data = "";
        Scanner scanner = null;
        Dragon dragon;
        if (isConsoleMod) {
            scanner = new Scanner(System.in);
        } else {
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка доступа к файла.");
            }
        }
            if (isConsoleMod) {
                System.out.print("Введите команду: ");
            }
            if (!scanner.hasNextLine() || ((data = scanner.nextLine()).equals(" "))) {
                System.exit(20);
            }
            String[] commandParts = data.split("\\s+");
            String message = commandParts[0];

            for (Command command: listOfCommands){
            try {
                if (message.equals(command.getCommandType().getName())) {
                    command.getCommandType();
                    if(command.validate(commandParts)) {
                        if(command.getCommandType() == CommandType.EXIT){
                            Exit exit = new Exit();
                            exit.execute();
                        }
                        return command;
                    }else{
                        return null;
                    }
                }
            } catch (JsonIOException ex){
            }
        }
            return null;
    }

    public static void findEndOfFile(Scanner scanner){
        if (!scanner.hasNextLine())
        {
            System.out.println();
            System.out.println("Обнаружен конец ввода. Выход из программы.");
            System.exit(20);
        }
    }


    public static void getListOfCommands(){
        listOfCommands.add(new Add());
        listOfCommands.add(new Clear());
        listOfCommands.add(new CountByColor());
        listOfCommands.add(new ExecuteScript());
        listOfCommands.add(new FilterStartsWithName());
        listOfCommands.add(new Help());
        listOfCommands.add(new Info());
        listOfCommands.add(new InsertAt());
        listOfCommands.add(new PrintFieldDescendingCave());
        listOfCommands.add(new RemoveById());
        listOfCommands.add(new RemoveLower());
        listOfCommands.add(new Reorder());
        listOfCommands.add(new Show());
        listOfCommands.add(new Update());
        listOfCommands.add(new Exit());
    }

}
