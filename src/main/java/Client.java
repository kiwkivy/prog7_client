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

            DatagramChannel reciverChannel = DatagramChannel.open();
            InetSocketAddress reciverSocketAdress = new InetSocketAddress("localhost", 1111);
            reciverChannel.bind(reciverSocketAdress);
            Thread reciverThread = new Thread(()->{
                while (true){
                    receiveMessage(reciverChannel);
                }
            });

            DatagramChannel datagramChannel = DatagramChannel.open();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 4357);
            datagramChannel.bind(inetSocketAddress);

            Coordinates coordinates = new Coordinates();
            coordinates.setX(2314L);
            coordinates.setY(23423D);
            DragonCave cave = new DragonCave();
            cave.setDepth(324532L);
            cave.setNumberOfTreasures(3453D);
            Dragon dragon = new Dragon("Рогалик", coordinates, 5464, Color.GREEN, DragonType.FIRE, DragonCharacter.CHAOTIC_EVIL, cave);

            //receiveMessage(datagramChannel);
            Help help = new Help();
            Add add = new Add(dragon);
            sendMessage(datagramChannel, add);
            reciverThread.run();

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
