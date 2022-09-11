import Commands.Add;
import Commands.Command;
import Commands.Help;
import Commands.Show;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.*;
import data.Color;
import serializers.CaveSerializer;
import serializers.CoordinatesSerializer;
import serializers.DragonSerializer;
import utils.Interpreter;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args){
        try {
            Help help = new Help();
            Interpreter interpreter = new Interpreter();
            DatagramChannel sendChannel = DatagramChannel.open();
            InetSocketAddress sendSocketAddress = new InetSocketAddress("localhost", 1111);
            sendChannel.bind(sendSocketAddress);
            Thread sendThread = new Thread(()->{
                while (true){
                    Command command = interpreter.getCommand();
                    if (command!= null) {
                        sendMessage(sendChannel, command);
                    }else{System.out.println("Команда введена неверно. Для получения справки введите help");}
                }
            });
            sendThread.run();

            DatagramChannel receiveChannel = DatagramChannel.open();
            InetSocketAddress receiveSocketAddress = new InetSocketAddress("localhost", 2222);
            receiveChannel.bind(receiveSocketAddress);
            Thread receiveThread = new Thread(()->{
                while (true){
                    receiveMessage(receiveChannel);
                }
            });



            Coordinates coordinates = new Coordinates();
            coordinates.setX(2314L);
            coordinates.setY(23423D);
            DragonCave cave = new DragonCave();
            cave.setDepth(324532L);
            cave.setNumberOfTreasures(3453D);
            Dragon dragon = new Dragon("Рогалик", coordinates, 5464, Color.GREEN, DragonType.FIRE, DragonCharacter.CHAOTIC_EVIL, cave);

            Add add = new Add(dragon);
            //sendMessage(sendChannel, add);
            receiveThread.run();

        }catch (IOException ex){
            System.out.println("Сокет наебнулся");
        }
    }

    public static void receiveMessage(DatagramChannel channel){
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        String message = null; // само сообщение
        SocketAddress socket = null; //сокет
        try{
            socket = channel.receive(buffer); //пытаемся получить сообщение из буфера
            buffer.flip(); //Переворачиваем сообщение
            byte[] bytes = new byte[buffer.remaining()]; //получаем длину сообщения
            buffer.get(bytes); //получили массив битов
            message = new String(bytes);
            System.out.println("Получено сообщение: " + message);
        }catch (IOException ex){
            System.out.println("yheytjh"); //TODO exception
        }
    }

    public static void sendMessage(DatagramChannel channel, String message){
        try {
            channel.send(StandardCharsets.UTF_8.newEncoder().encode(CharBuffer.wrap(message)), new InetSocketAddress("localhost", 1683));
            System.out.println("Ты смог дебила кусок это отправить - " + message);
        }catch (IOException ex) {
            ex.printStackTrace(); //TODO ЯК СЕ МАТЕ МОЕ ПРШТЫЛКУНИ
        }

    }

    public static void sendMessage(DatagramChannel channel, Command command){
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Dragon.class, new DragonSerializer())
                .registerTypeAdapter(DragonCave.class, new CaveSerializer())
                .registerTypeAdapter(Coordinates.class, new CoordinatesSerializer())
                .create();
        sendMessage(channel, gson.toJson(command));
    }

}
