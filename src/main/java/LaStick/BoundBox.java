package LaStick;

import java.util.Objects;

import static java.lang.Math.min;
import static java.lang.Math.max;

final public class BoundBox {
    /**
     * Нижняя точка ограничивающего прямоугольника
     */
    private final Coord2D lowPoint;

    /**
     * Верхняя точка ограничивающего прямоугольника
     */
    private final Coord2D highPoint;

    /**
     * Возвращает нижнюю точку прямоугольника
     * @return Нижняя точка прямоугольника
     */
    public Coord2D getLowPoint() {
        return lowPoint;
    }

    /**
     * Возвращает верхнюю точку прямоугольника
     * @return Верхняя точка прямоугольника
     */
    public Coord2D getHighPoint() {
        return highPoint;
    }


    /**
     * Создает пустой ограничивающий прямоугольник
     */
    public BoundBox() {
        lowPoint = new Coord2D(Double.MAX_VALUE, Double.MAX_VALUE);
        highPoint = new Coord2D(Double.MIN_VALUE, Double.MIN_VALUE);
    }

    /**
     * Создает пустой ограничивающий прямоугольник
     * @param lowPoint
     * @param highPoint
     */
    public BoundBox(Coord2D lowPoint, Coord2D highPoint) {
        this.lowPoint = lowPoint;
        this.highPoint = highPoint;
    }

    /**
     * Перемещает прямоугольник oldBox на некоторую точку shift
     * @param oldBox Ограничивающий прямоугольник
     * @param shift Точка, в которую перемещают прямоугольник
     * @return Новый BoundBox на новой точке
     */
    static public BoundBox moveBox(BoundBox oldBox, Coord2D shift) {
        // Проверка на пустой BoundBox;
        if (oldBox.lowPoint.getX() == Double.MAX_VALUE &&
                oldBox.lowPoint.getY() == Double.MAX_VALUE &&
                oldBox.highPoint.getX() == Double.MIN_VALUE &&
                oldBox.highPoint.getY() == Double.MIN_VALUE) {
            return oldBox;
        }

        double minX = oldBox.lowPoint.getX() + shift.getX();
        double minY = oldBox.lowPoint.getY() + shift.getY();
        double maxX = oldBox.highPoint.getX() + shift.getX();
        double maxY = oldBox.highPoint.getY() + shift.getY();
        Coord2D newMin = new Coord2D(minX, minY);
        Coord2D newMax = new Coord2D(maxX, maxY);
        return new BoundBox(newMin, newMax);
    }

    /**
     * Соединяет два BoundBox'а в один большой посредством сравнивания их точек
     * @param firstBox Первый BoundBox
     * @param secondBox Второй BoundBox
     * @return Объединенный BoundBox
     */
    static public BoundBox merge(BoundBox firstBox, BoundBox secondBox) {
        /**
         * Здесь пустой BoundBox как раз не мешает другому BoundBox'у,
         * ведь его нижняя точка всегда больше, а верхняя - меньше.
         * Тем самым условие пустоты работает.
         */
        double minX = min(firstBox.lowPoint.getX(), secondBox.lowPoint.getX());
        double minY = min(firstBox.lowPoint.getY(), secondBox.lowPoint.getY());
        Coord2D lowPoint = new Coord2D(minX, minY);

        double maxX = max(firstBox.highPoint.getX(), secondBox.highPoint.getX());
        double maxY = max(firstBox.highPoint.getY(), secondBox.highPoint.getY());
        Coord2D highPoint = new Coord2D(maxX, maxY);

        return new BoundBox(lowPoint, highPoint);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoundBox boundBox = (BoundBox) o;
        return Objects.equals(lowPoint, boundBox.lowPoint) && Objects.equals(highPoint, boundBox.highPoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowPoint, highPoint);
    }
}
