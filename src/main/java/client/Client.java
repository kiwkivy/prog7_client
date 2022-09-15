package client;

import commands.Add;
import commands.Command;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.*;
import data.Color;
import exceptions.*;
import serializers.CaveSerializer;
import serializers.CoordinatesSerializer;
import serializers.DragonSerializer;
import utils.Interpreter;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
    public static long sendTime;
    public static boolean sendTimeFlag;

    public static void main(String[] args){

        Thread checkTime = new Thread(() -> {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(new Date().getTime() - sendTime > 1000 && sendTimeFlag && sendTime != -1){
                        System.err.println("Сервер недоступен");
                        sendTimeFlag = false;
                        sendTime = -1;
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
            InetSocketAddress receiveSocketAddress = new InetSocketAddress("localhost", 2222);
            receiveChannel.bind(receiveSocketAddress);

            while (true) {
                Command command = interpreter.getCommand();

                if (command != null) {
                    sendMessage(sendChannel, command);
                    receiveMessage(receiveChannel);
                } else {
                    System.out.println("Команда введена неверно. Для получения справки введите help");
                }
                Coordinates coordinates = new Coordinates();
                coordinates.setX(2314L);
                coordinates.setY(23423D);
                DragonCave cave = new DragonCave();
                cave.setDepth(324532L);
                cave.setNumberOfTreasures(3453D);
                Dragon dragon = new Dragon("Рогалик", coordinates, 5464, Color.GREEN, DragonType.FIRE, DragonCharacter.CHAOTIC_EVIL, cave);
                Add add = new Add(dragon);
            }
            }catch(IOException ex){
                ex.printStackTrace();
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
                sendTimeFlag = false;
                System.out.println(message.replace("\\n", "\n").replace("}id=", "id="));


        }catch (IOException ex){
        }
    }

    public static void sendMessage(DatagramChannel channel, String message){
        try {
            channel.send(StandardCharsets.UTF_8.newEncoder().encode(CharBuffer.wrap(message)), new InetSocketAddress("localhost", 1111));
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
