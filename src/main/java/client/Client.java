package client;

import commands.Command;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import commands.CommandType;
import commands.*;
import data.*;
import exceptions.*;
import serializers.CaveSerializer;
import serializers.CoordinatesSerializer;
import serializers.DragonSerializer;
import utils.Interpreter;

import java.io.Console;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Client {
    public static long sendTime;
    public static boolean sendTimeFlag;
    public static boolean userIsLoggedIn = false;
    private static boolean workWithScript = false;
    public static int port = 4111;

    public static void main(String[] args){
        String username = null;
        String password = null;
        Thread checkTime = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (new Date().getTime() - sendTime > 1000000 && sendTimeFlag && sendTime != -1) {
                            sendTimeFlag = false;
                            sendTime = -1;
                            throw new ServerIsUnavailableException();
                        }
                    }catch (ServerIsUnavailableException ex){
                        ex.printStackTrace();
                        System.exit(-2534);
                    }
                }
            }, 250, 250);
        });

        checkTime.run();

        try {
            long sendTime = -1;
            Boolean sendTimeFlag = true;
            Interpreter interpreter = new Interpreter();
            DatagramChannel sendChannel = DatagramChannel.open();
            DatagramChannel receiveChannel = DatagramChannel.open();
            boolean bindFlag = true;
            while (bindFlag)
                try {
                    InetSocketAddress receiveSocketAddress = new InetSocketAddress("localhost", port);
                    receiveChannel.bind(receiveSocketAddress);
                    bindFlag = false;
                } catch (BindException ex) {
                    port++;
                }

            Start start = new Start();
            start.setPort(port);
            Console console = System.console();
            Scanner scanner = new Scanner(System.in);
            String choice;
            boolean normalChoice;
            do {
                System.out.println("Введите 1 для авторизации и 2 для регистрации");
                System.out.print("Ваш выбор:");
                choice = scanner.nextLine();
                if (choice.equals("1")) {
                    start.setRegisterFlag(false);
                } else if (choice.equals("2")) {
                    start.setRegisterFlag(true);
                }else {
                    System.out.println("Такого выбор нет.");
                }
                System.out.println();
            }while (!(choice.equals("1") | choice.equals("2")));
            while (!userIsLoggedIn) {
                    System.out.print("Введите логин:");
                    username = scanner.nextLine();
                    System.out.print("Введите пароль:");
                    if (console != null) {
                        char[] pass = console.readPassword();
                        if (pass == null) continue;
                        password = String.valueOf(pass);
                    }else {
                        password = scanner.nextLine();
                    }
                    start.setUser(username, password);
                    sendMessage(sendChannel, start);
                    receiveMessage(receiveChannel);
                    System.out.println();

            }

            while (true) {
                Command command = interpreter.getCommand(username, password);
                if (command != null) {
                    command.setPort(port);
                    sendMessage(sendChannel, command);
                    if(command.getCommandType() == CommandType.EXECUTE_SCRIPT){
                        workWithScript = true;
                    }
                    receiveMessage(receiveChannel);
                } else {
                    System.out.println("Команда введена неверно. Для получения справки введите help");
                }
            }
            }catch(IOException ex){
                ex.printStackTrace();
            }
    }

    public static void receiveMessage(DatagramChannel channel){
         do {
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            String message = null; // само сообщение
            SocketAddress socket = null; //сокет
            try {
                socket = channel.receive(buffer); //пытаемся получить сообщение из буфера
                buffer.flip(); //Переворачиваем сообщение
                byte[] bytes = new byte[buffer.remaining()]; //получаем длину сообщения
                buffer.get(bytes); //получили массив битов
                message = new String(bytes);
                sendTimeFlag = false;
                System.out.println(message.replace("\\n", "\n").replace("={", "(").replace("},", ")")
                        .replace("}", ")")
                        .replace("\"","")
                        .replace("}", ""));
                if (message.equals("\"Возврат в обычный режим.\"")){
                    System.out.println();
                    workWithScript = false;
                }
                if (message.equals("Авторизация прошла успешно.") | message.equals("Регистрация прошла успешно.") ){
                    userIsLoggedIn = true;
                }
            } catch (IOException ex) {
            }
        }while (workWithScript);
    }

    public static void sendMessage(DatagramChannel channel, String message){
        try {
            channel.send(StandardCharsets.UTF_8.newEncoder().encode(CharBuffer.wrap(message)), new InetSocketAddress("localhost", 3321));
            sendTime = new Date().getTime();
            sendTimeFlag = true;
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void sendMessage(DatagramChannel channel, Command command){
        Gson gson = new GsonBuilder().disableHtmlEscaping()
                .registerTypeAdapter(Dragon.class, new DragonSerializer())
                .registerTypeAdapter(DragonCave.class, new CaveSerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerializer())
                .create();
        sendMessage(channel, gson.toJson(command));

        if(sendTime == -1)
            sendTime = new Date().getTime();
        else
            sendTime = Math.min(sendTime, new Date().getTime());

    }
}
