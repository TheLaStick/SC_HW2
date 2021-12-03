package LaStick;

import java.util.Objects;

final public class Coord2D {
    /**
     * Горизонтальная координата математической точки
     */
    private final double x;

    /**
     * Вертикальная координата математической точки
     */
    private final double y;

    /**
     * Создает математическую точку
     * @param x Вертикальная координата
     * @param y Горизонтальная координата
     */
    public Coord2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает горизонтальная координату
     * @return Горизонтальная координата
     */
    public double getX() {
        return x;
    }

    /**
     * Возвращает вертикальную координату
     * @return Вертикальная координата
     */
    public double getY() {
        return y;
    }

    /**
     * Сравнивает объект с математической точкой
     * В случае, если объект является математической точкой,
     * сравнивает математические точки по координатам
     * @param o Объект
     * @return true - если математические точки равны по координатам, else - иначе
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord2D coord2D = (Coord2D) o;
        return Double.compare(coord2D.x, x) == 0 && Double.compare(coord2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
