package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.ZoneId;
import java.time.ZonedDateTime;


public class Dragon implements Valid{
    @JsonIgnore
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @JsonIgnore
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer age; //Значение поля должно быть больше 0, Поле не может быть null
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле не может быть null
    private DragonCave cave; //Поле не может быть null

    public Dragon() {
        this.id = 1;
        this.creationDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    public Dragon(String name,
                  Coordinates coordinates,
                  int age,
                  Color color,
                  DragonType type,
                  DragonCharacter character,
                  DragonCave cave) {
        creationDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        this.name = name;
        this.coordinates = coordinates;
        this.age = age;
        this.color = color;
        this.type = type;
        this.character = character;
        this.cave = cave;
        this.id = 1;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return age
     */
    public int getAge() {
        return age;
    }


    /**
     * @return type
     */
    public DragonType getType() {
        return type;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return type
     */
    public DragonCharacter getCharacter() {
        return character;
    }

    /**
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return cave
     */
    public DragonCave getCave() {
        return cave;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Устанавливает значение id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean isValid() {
        return (
                id > 0 && name != null && coordinates != null && age != null && age > 0
                        && color != null && type != null && character != null && cave != null && creationDate != null
        ) && coordinates.isValid() && cave.isValid();
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates={" + coordinates + "}"+
                ", creationDate=" + creationDate +
                ", age=" + age +
                ", color=" + color +
                ", type=" + type +
                ", character=" + character +
                ", cave={" + cave + "}";
    }


}


