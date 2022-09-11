package data;

/**
 * enum типов
 */

public enum DragonType {
    UNDERGROUND ("underground"),
    AIR ("air"),
    FIRE ("fire");

    private final String name;

    DragonType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
