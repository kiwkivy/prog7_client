package storage;

import utils.FileWorker;
import data.Color;
import data.Dragon;
import data.DragonCave;
import exceptions.ElementNotValidException;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс векторной коллекции с элементами типа Dragon.
 */
public class DragonVectorStorage implements DragonStorage<Vector<Dragon>> {

    private int idCounter;
    private final Vector<Dragon> dragonVector;
    private final ZonedDateTime creationDate;
    private File file;

    /**
     * @return idCounter
     */
    public int getIdCounter() {
        return idCounter;
    }

    /**
     * @return file
     */
    public File getFile(){
        return file;
    }

    public DragonVectorStorage(File file) {
        this.file = file;
        idCounter = 1;
        dragonVector = new Vector<>();
        creationDate = ZonedDateTime.now();
    }

    @Override
    public void add(final Dragon someElement) throws ElementNotValidException {
        if (!someElement.isValid()) {
            throw new ElementNotValidException("Element is not valid");
        }
        dragonVector.add(someElement);
        someElement.setId(idCounter++);
    }

    @Override
    public void update(final int id, final Dragon element) throws ElementNotValidException{
        if (!element.isValid()) {
            throw new ElementNotValidException("Element is not valid");
        }
        dragonVector.setElementAt(element, id);
        generateId();
    }

    @Override
    public void removeById(final int id) {
        boolean result = dragonVector.removeIf(dragon -> dragon.getId() == id);
        if (id > 0 && id <= idCounter) {
            generateId();
        }else{
            System.out.println("Элемента с id " + id + " не сущетсвует");
        }
    }

    @Override
    public void insertAt(final int index, final Dragon element) throws ElementNotValidException{
        if (!element.isValid()) {
            throw new ElementNotValidException("Element is not valid");
        }
        dragonVector.insertElementAt(element, index);
        generateId();
    }

    @Override
    public void clear() {
        dragonVector.clear();
    }

    @Override
    public void reorder() {
        Collections.reverse(dragonVector);
        generateId();
    }

    @Override
    public void countByColor(final Color color) {
        System.out.println("Количество элементов цвета " + color + ": " + dragonVector.stream()
                .filter(dragon -> Objects.equals(dragon.getColor(), color))
                .count());
    }

    @Override
    public void filterStartsWithName(final String name) {
        Vector<Dragon> newVector = dragonVector.stream().filter(dragon -> dragon.getName() != null)
                .filter(dragon -> dragon.getName().startsWith(name))
                .collect(Collectors.toCollection(Vector::new));
        for (Dragon dragon : newVector){
            System.out.println(dragon.toString());
        }

    }

    @Override
    public Collection<DragonCave> getAllDescendingCave() {
        return (dragonVector.stream()
                .map(Dragon::getCave)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toCollection(Vector::new)));
    }

    public <T> T test(T element){
        return element;
    }

    /**
     * @return dragonVector
     */
    public Vector<Dragon> getDragonVector() {
        return dragonVector;
    }

    /**
     * @return creationDate
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public void info() {
        System.out.println("Тип: DragonVectorStorage");
        System.out.println("Дата инизиализации: " + getCreationDate());
        System.out.println("Количество элементов: " + dragonVector.size());
        System.out.println("Место хранение: test.txt");
    }

    @Override
    public void show(){
        if (dragonVector.size() > 0) {
            for (Dragon dragon : dragonVector) {
                System.out.println(dragon.toString());
            }
        }else System.out.println("Коллекция пуста.");
    }

    /**
     * Релизация команды save.
     */
    public void save(DragonVectorStorage dragonVectorStorage, File file, FileWorker worker) {
        worker.writeFile(dragonVectorStorage, file);
    }

    @Override
    public void removeLower(Dragon lowerDragon) {
        dragonVector.removeIf(dragon -> dragon.getAge() < lowerDragon.getAge());
        generateId();
    }

    /**
     * Метод для генерации новых id.
     */
    private void generateId(){
        idCounter = 0;
        for (Dragon dragon : dragonVector) {
            dragon.setId(++idCounter);
        }
    }

}
