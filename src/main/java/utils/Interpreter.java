package utils;

import Commands.*;
import storage.DragonVectorStorage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    private static boolean isConsoleMod;
    private File file;
    private DragonVectorStorage dragonVectorStorage;
    public static List<String> scriptArray = new ArrayList<>();
    public static ArrayList<Command> listOfCommands = new ArrayList<>();



    /*public Interpreter(DragonVectorStorage dragonVectorStorage) {
        this.dragonVectorStorage = dragonVectorStorage;
        this.isConsoleMod = true;
    }

    public Interpreter(DragonVectorStorage dragonVectorStorage, File file) {
        this.dragonVectorStorage = dragonVectorStorage;
        this.isConsoleMod = false;
        this.file = file;
    }

    /**
     * Метод, реализующий работу интерпретатора. Интерпретатор работает в двух режимах: консольном и скриптовом.
     */

    /*public void start() {
        FileWorker worker = new FileWorker();
        String data = "";
        Scanner scanner = null;
        Dragon dragon;
        if (isConsoleMod){
            scanner = new Scanner(System.in);
        }else{
            try {
                scanner = new Scanner(file);
            }catch(FileNotFoundException e){
                System.out.println("Ошибка доступа к файла.");
            }
        }
        while (true) {
            if (isConsoleMod) {
                System.out.print("Введите команду: ");
            }
            if (!scanner.hasNextLine() || ((data = scanner.nextLine()).equals(" "))){
                System.exit(20);
            }
            String[] commandParts = data.split("\\s+");
            String command = commandParts[0];
            if (!data.equals("")) {
                if (commandParts.length == 1) {
                    switch (command) {
                        case "clear":
                            Command clear = new Clear(dragonVectorStorage);
                            break;
                        case "exit":
                            Command exit = new Exit();
                            break;
                        case "help":
                            Command help = new Help();
                            break;
                        case "info":
                            Command info = new Info(dragonVectorStorage);
                            break;
                        case "reorder":
                            Command reorder = new Reorder(dragonVectorStorage);
                            break;
                        case "save":
                            Command save = new Save(dragonVectorStorage, worker, dragonVectorStorage.getFile());
                            break;
                        case "show":
                            Command show = new Show(dragonVectorStorage);
                            break;
                        case "print_field_descending_cave":
                            Command printFieldDescendingCave = new PrintFieldDescendingCave(dragonVectorStorage);
                            break;
                        case "add":
                            if (dragonVectorStorage.getIdCounter() <= 99) {
                                if (isConsoleMod) {
                                    dragon = CreatorOfDragons.create();
                                } else {
                                    dragon = CreatorOfDragons.create(scanner);}
                                if (dragon != null && dragon.isValid()) {
                                    Command add = new Add(dragonVectorStorage, dragon);
                                }
                            } else System.out.println("Превышено количество элементов коллекции.");
                            break;
                        default:
                            System.out.println(
                                    "Команда <"+data+"> не существует или введена неверно. " +
                                    "Для получения справки введите help"
                            );
                            break;
                    }
                } else if (commandParts.length == 2){
                    switch (command) {
                        case "update":
                            if (dragonVectorStorage.getIdCounter() > Integer.parseInt(commandParts[1])) {
                                if (isConsoleMod) {
                                    dragon = CreatorOfDragons.create();
                                } else {
                                    dragon = CreatorOfDragons.create(scanner);
                                }
                                if (dragon != null && dragon.isValid()) {
                                    Command update = new Update(
                                            dragonVectorStorage,
                                            dragon,
                                            Integer.parseInt(commandParts[1])
                                    );
                                }
                            } else
                                System.out.println("Номер элемента не может быть больше количества элементов" +
                                        " коллекции = " + dragonVectorStorage.getIdCounter());
                            break;
                        case "insert_at":
                            if (dragonVectorStorage.getIdCounter() > Integer.parseInt(commandParts[1])) {
                                if (isConsoleMod) {
                                    dragon = CreatorOfDragons.create();
                                } else {
                                    dragon = CreatorOfDragons.create(scanner);
                                }
                                if (dragon != null && dragon.isValid()) {
                                    Command insertAt = new InsertAt(dragonVectorStorage, dragon, Integer.parseInt(commandParts[1]));
                                }
                            } else
                                System.out.println(
                                        "Коллекция меньше, чем введённый номер элемента. " +
                                                "Размер коллекции: " + dragonVectorStorage.getIdCounter()
                                );
                            break;
                        case "count_by_color":
                            System.out.println("Доступные цвета - GREEN, BLUE, BLACK, ORANGE, WHITE");
                            if (Checker.checkColor(commandParts[1])) {
                                Color color = Color.valueOf(commandParts[1]);
                                Command countByColor = new CountByColor(dragonVectorStorage, color);
                            } else System.out.println("Команда введена неверно. Для получения справки введите help");
                            break;
                        case "execute_script":
                            File scriptFile = new File(commandParts[1]);
                            if (scriptFile.exists()) {
                                if (!scriptFile.canRead()){
                                    System.out.println("Отсутствуют права на чтение!");
                                } else if (!scriptFile.canWrite()) {
                                    System.out.println("Отсутствуют права на запись!");
                                }
                                if (!scriptArray.contains(commandParts[1])) {
                                    scriptArray.add(commandParts[1]);
                                    Command executeScript = new ExecuteScript(dragonVectorStorage, scriptFile);
                                    scriptArray.remove(commandParts[1]);
                                } else System.out.println("Данный скрипт уже использован.");
                            } else
                                System.out.println(
                                        "Не удалось получить данные из файла. Проверьте корректность данных."
                                );
                            break;
                        case "filter_starts_with_name":
                                Command filterStartsWithName = new FilterStartsWithName(
                                        dragonVectorStorage,
                                        commandParts[1]
                                );
                            break;
                        case "remove_by_id":
                            if (Checker.checkIntUpZero(commandParts[1])) {
                                Command removeById = new RemoveById(
                                        dragonVectorStorage,
                                        Integer.parseInt(commandParts[1])
                                );
                            } else System.out.println("Команда введена неверно. Для получения справки введите help");
                            break;
                        case "remove_lower":
                                if (Checker.checkIntUpZero(commandParts[1]) &&
                                        (Integer.parseInt(commandParts[1]) < dragonVectorStorage.getIdCounter())) {
                                    Command removeLower = new RemoveLower(
                                            dragonVectorStorage,
                                            dragonVectorStorage.getDragonVector().get(
                                                    Integer.parseInt(commandParts[1]) - 1
                                            )
                                    );
                                } else
                                    System.out.println(
                                            "Элемента с таким id не существует. " +
                                            "Введите show для просмотра существующих элементов"
                                    );
                            break;
                        default:
                            System.out.println(
                                    "Команда <"+data+"> не существует или введена неверно. " +
                                            "Для получения справки введите help"
                            );
                            break;
                    }
                }else{
                    System.out.println("Введено слишком много аргументов.");
                }
            }
        }
    }
    public static void findEndOfFile(Scanner scanner){
        if (!scanner.hasNextLine())
        {
            System.out.println();
            System.out.println("Обнаружен конец ввода. Выход из программы.");
            System.exit(20);
        }
    }*/

}
