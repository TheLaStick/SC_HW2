package LaStick;

public class Point {
    /**
     * Координата точки в некотором Origin/Space
     */
    private Coord2D position;

    /**
     * Создает точку в некотором Origin/Space
     * @param position Координаты
     */
    public Point(Coord2D position) {
        this.position = position;
    }

    /**
     * Возвращает позицию точки
     * @return Позиция точки
     */
    public Coord2D getPosition() {
        return position;
    }

    /**
     * Задает позицию в точке
     * @param position Позиция
     */
    public void setPosition(Coord2D position) {
        this.position = position;
    }

    /**
     * Подсчитывает BoundBox для точки
     * @return Вырожденный случай BoundBox'а точки
     */
    public BoundBox bound() {
        return new BoundBox(position, position);
    }
}
