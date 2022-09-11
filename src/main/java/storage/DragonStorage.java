package storage;

import utils.FileWorker;
import data.Color;
import data.Dragon;
import data.DragonCave;

import java.io.File;
import java.util.Collection;

/**
 * Интерфейс для коллекций с элементами типа Dragon.
 */
public interface DragonStorage<T extends Collection<Dragon>> extends Storage<T, Dragon> {

    /**
     * Релизация команды count_by_color.
     */
    void countByColor(final Color color);

    /**
     * @return Collection<DragonCave>.
     */
    Collection<DragonCave> getAllDescendingCave();

    /**
     * Релизация команды save.
     */
    void save(DragonVectorStorage dragonVectorStorage, File file, FileWorker worker);
}
