package data;

/**
 * enum характеров
 */

public enum DragonCharacter {
    WISE ("wise"),
    CHAOTIC_EVIL ("chaotic_evil"),
    FICKLE ("fickle");

    private final String name;

    DragonCharacter(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
