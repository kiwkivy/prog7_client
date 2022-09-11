package utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import data.Dragon;
import exceptions.ElementNotValidException;
import storage.DragonVectorStorage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс, работающий с файлом коллекции с элементами типа Dragon.
 */

public class FileWorker implements FileInterface {
    private File file;
    private String data;

    @Override
    public DragonVectorStorage readFile(DragonVectorStorage dragonVectorStorage, File file) {
        final XmlMapper xmlMapper = XmlMapperFactory.getInstance();
        this.file = file;
        Scanner sc;
        Dragon dragon;
        String data = "";
            try {
                sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    data = sc.nextLine();
                    try {
                        dragon = XmlMapperFactory.getInstance().readValue(data, Dragon.class);
                        if (dragon.getName().split("\\s+").length == 1){;
                            dragonVectorStorage.add(dragon);
                        }
                    } catch (JsonProcessingException | ElementNotValidException e) {
                    }
                }
                sc.close();
            } catch (FileNotFoundException ex) {
                System.out.println("Файл не найден.");
            }
        return dragonVectorStorage;
    }

    @Override
    public void writeFile(DragonVectorStorage dragonVectorStorage, File file) {
    String xmlDragon;
    this.file = file;
    final XmlMapper xmlMapper = XmlMapperFactory.getInstance();
        try {
            FileWriter writer = new FileWriter(file, false);
            for(Dragon dragon: dragonVectorStorage.getDragonVector())
                {
                    xmlDragon = xmlMapper.writeValueAsString(dragon);
                    writer.write(xmlDragon + "\n");
                };
            writer.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
