package data;

/**
 * Класс для проверки корректности данных
 */
public class Checker {

    /**
     * Метод проверки на положительное число типа int.
     *
     * @return результат корректности
     */
    public static boolean checkIntUpZero(String data){
        try {
            Integer.parseInt(data);
            if (Integer.parseInt(data) <= 0){
                return false;
            }
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Метод проверки на число типа long.
     *
     * @return результат корректности
     */
    public static boolean checkLong(String data){
        try {
            Long.parseLong(data);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод проверки на число типа double.
     *
     * @return результат корректности
     */
    public static boolean checkDouble(String data){
        try {
            Double.parseDouble(data);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * Метод проверяет, является ли строка элементом Color.
     *
     * @return результат корректности
     */
    public static boolean checkColor(String data){
        try{
            Color.valueOf(data);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    /**
     * Метод проверяет, является ли строка элементом DragonType.
     *
     * @return результат корректности
     */
    public static boolean checkType(String data){
        try{
            DragonType.valueOf(data);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    /**
     * Метод проверяет, является ли строка элементом CheckCharacter.
     *
     * @return результат корректности
     */
    public static boolean checkCharacter(String data){
        try{
            DragonCharacter.valueOf(data);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    /**
     * Метод проверки на положительное число типа double.
     *
     * @return результат корректности
     */
    public static boolean checkDoublePositive(String data){
        try {
            Double.parseDouble(data);
            if (Double.parseDouble(data) <= 0) {
                return false;
            }
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
