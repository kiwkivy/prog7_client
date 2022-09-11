package data;

/**
 * Класс коррдинат
 */

public class Coordinates implements Valid {
    private Long x; //Поле не может быть null
    private Double y; //Поле не может быть null


    @Override
    public boolean isValid() {
        return x != null && y != null;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x.equals(that.x) && y.equals(that.y);
    }
    /**
     * Устанавливает значение координаты x
     */
    public void setX(Long x) {
        this.x = x;
    }

    /**
     * Устанавливает значение координаты y
     */
    public void setY(Double y) {
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
