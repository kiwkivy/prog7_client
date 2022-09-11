package storage;

import exceptions.ElementNotValidException;

import java.util.Collection;

/**
 * Интерфейс для коллекций c элементами любого типа.
 */
public interface Storage<T extends Collection<E>, E>{

    /**
     * Релизация команды add.
     */
    void add(final E element) throws ElementNotValidException;

    /**
     * Релизация команды remove_by_id.
     */
    void removeById(final int id);

    /**
     * Релизация команды update.
     */
    void update(final int id, final E element) throws ElementNotValidException, ElementNotValidException;

    /**
     * Релизация команды insert_at.
     */
    void insertAt(final int index, final E element) throws ElementNotValidException;

    /**
     * Релизация команды remove_lower;
     */
    void removeLower(final E element);

    /**
     * Релизация команды clear.
     */
    void clear();

    /**
     * Релизация команды reorder.
     */
    void reorder();

    /**
     * Релизация команды info.
     */
    void info();

    /**
     * Релизация команды show.
     */
    void show();

    /**
     * Релизация команды filter_starts_with_name.
     */
    void filterStartsWithName(String name);
}
