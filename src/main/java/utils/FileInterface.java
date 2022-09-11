package utils;

import storage.DragonVectorStorage;

import java.io.File;

/**
 * Интерфейс для работы с файлами.
 */
public interface FileInterface {
    /**
     * Метод для реализации чтения коллекции из файла.
     */
    DragonVectorStorage readFile(DragonVectorStorage dragonVectorStorage, File file);

    /**
     * Метод для записи коллекции в файл.
     */
    void writeFile(DragonVectorStorage data, File file);

}
