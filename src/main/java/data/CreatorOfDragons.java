package data;

import java.util.Scanner;

/**
 * Класс для создания объектов типа Dragon
 */
public class CreatorOfDragons {

    /**
     * Метод создаёт объект типа Dragon на основе пользовательского ввода.
     *
     * @return dragon
     */
    public static Dragon create() {
        boolean isWrong = true;
        Scanner scanner = new Scanner(System.in);
        String data = "";
        while (isWrong) {
            System.out.print("Введите имя вашего дракона: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (!data.equals("")) {
                isWrong = false;
            }
        }
        String name = data;
        isWrong = true;

        System.out.println("Коррдинаты вашего дракона.");
        while (isWrong) {
            System.out.print("Введите x: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkLong(data)) {
                isWrong = false;
            } else if (!data.equals("")){
                System.out.println("Доступный диапазон: от –9 223 372 036 854 775 808 до 9 223 372 036 854 775 807");
            }
        }
        Long x = Long.parseLong(data);
        isWrong = true;

        while (isWrong) {
            System.out.print("Введите y: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkDouble(data)) {
                isWrong = false;
            } else if (!data.equals("")) {
                System.out.println("Доступный диапазон:  от ±4.9*10^(-324) до ±1.8*10^(308)");
            }
        }
        Double y = Double.parseDouble(data);
        isWrong = true;

        Coordinates coordinates = new Coordinates();
        coordinates.setX(x);
        coordinates.setY(y);

        while (isWrong) {
            System.out.print("Введите возраст дракона: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkIntUpZero(data)) {
                isWrong = false;
            } else if (!data.equals("")){
                System.out.println("Диапазон зачений для глубины: от 1 до 2147483647");
            }
        }
        int age = Integer.parseInt(data);
        isWrong = true;

        while (isWrong) {
            System.out.println("Доступные цвета - GREEN, BLUE, BLACK, ORANGE, WHITE ");
            System.out.print("Введите цвет вашего дракона: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkColor(data)) {
                isWrong = false;
            } else if (!data.equals("")){
                System.out.println("Данного цвета не существует. Попробуйте ещё раз.");
            }
        }
        Color color = Color.valueOf(data);
        isWrong = true;

        while (isWrong) {
            System.out.println("Доступные типы - AIR, UNDERGROUND, FIRE ");
            System.out.print("Введите тип вашего дракона: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkType(data)) {
                isWrong = false;
            } else if (!data.equals("")){
                System.out.println("Данного типа драконов не существует. Попробуйте ещё раз.");
            }
        }
        DragonType type = DragonType.valueOf(data);
        isWrong = true;


        while (isWrong) {
            System.out.println("Доступные характеры - WISE, CHAOTIC_EVIL, FICKLE");
            System.out.print("Введите характер вашего дракона: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkCharacter(data)) {
                isWrong = false;
            } else if (!data.equals("")){
                System.out.println("Данного характера не существует. Попробуйте ещё раз.");
            }
        }
        DragonCharacter character = DragonCharacter.valueOf(data);
        isWrong = true;

        System.out.println("Информация о пещере.");
        while (isWrong) {
            System.out.print("Введите количество сокровищ: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkDoublePositive(data)) {
                isWrong = false;
            } else if (!data.equals("")) {
                System.out.println("Доступный диапазон:  от 4.9*10^(-324) до 1.8*10^(308)");
            }
        }
        Double numberOfTreasures = Double.parseDouble(data);
        isWrong = true;

        while (isWrong) {
            System.out.print("Введите глубину пещер: ");
            //Interpreter.findEndOfFile(scanner);
            data = scanner.nextLine();
            if (Checker.checkDouble(data)) {
                isWrong = false;
            } else if (!data.equals("")){
                System.out.println("Диапазон зачений для глубины: от –9 223 372 036 854 775 808 до 9 223 372 036 854 775 807");
            }
        }
        Long depth = Long.parseLong(data);

        System.out.println();
        DragonCave cave = new DragonCave();
        cave.setNumberOfTreasures(numberOfTreasures);
        cave.setDepth(depth);

        Dragon dragon = new Dragon(name, coordinates, age, color, type, character, cave);
        return dragon;
    }

    /**
     * Метод создаёт объект типа Dragon на основе значений из файла.
     *
     * @return dragon
     */
    public static Dragon create(Scanner scanner) {
        try {
            String name = scanner.nextLine();
            Coordinates coordinates = new Coordinates();
            Long x = Long.parseLong(scanner.nextLine());
            Double y = Double.parseDouble(scanner.nextLine());
            coordinates.setX(x);
            coordinates.setY(y);
            int age = Integer.parseInt(scanner.nextLine());
            Color color = Color.valueOf(scanner.nextLine());
            DragonType type = DragonType.valueOf(scanner.nextLine());
            DragonCharacter character = DragonCharacter.valueOf(scanner.nextLine());
            DragonCave cave = new DragonCave();
            Double numberOfTreasures = Double.parseDouble(scanner.nextLine());
            Long depth = Long.parseLong(scanner.nextLine());
            cave.setDepth(depth);
            cave.setNumberOfTreasures(numberOfTreasures);

            Dragon dragon = new Dragon(name, coordinates, age, color, type, character, cave);
            return dragon;
        }catch (Exception e){
            System.out.println("Некорректный ввод дракона");
            return null;
        }
    }

}
