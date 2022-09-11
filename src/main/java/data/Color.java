package data;

/**
 * enum цветов
 */
public enum Color {
    GREEN ("green"),
    BLACK ("black"),
    BLUE ("blue"),
    ORANGE ("orange"),
    WHITE ("white");

    private final String name;

    Color(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}