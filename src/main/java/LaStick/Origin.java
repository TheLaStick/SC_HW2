package LaStick;

import java.util.HashSet;
import java.util.Set;

public class Origin extends Point {
    /**
     * Множество дочерних узлов. Обычно задается в виде HashSet
     */
    private Set<Point> children;

    /**
     * Создает узел
     * @param position Позиция узла в каком-то Origin/Space
     */
    public Origin(Coord2D position) {
        super(position);
        children = new HashSet<>();
    }

    /**
     * Возвращает копию множества дочерних точек/узлов
     * @return Копия множества дочерних точек/узлов
     */
    public Set<Point> getChildren() {
        return new HashSet<>(children);
    }

    /**
     * Проверяет, зацикленны ли узлы
     * Задаёт дочерние точки/узлы в узле.
     * @param children Множество дочерних точек/узлов
     * @throws DAGConstraintException В случае зацикливании дочерних узлов, выбрасывается исключение
     */
    public void setChildren(Set<Point> children) throws DAGConstraintException {
        graphParse(this, children);

        this.children = children;
    }

    /**
     * Проверяет граф на циклы
     * @param head Корневой узел, который проверяется на равенство с потомками
     * @param children Потомки узла
     * @throws DAGConstraintException При зацикливании графа выбрасывается исключение
     */
    private static void graphParse(Origin head, Set<Point> children) throws DAGConstraintException {
        for (Point child : children) {
            if (child == head) {
                throw new DAGConstraintException("Found ring");
            }

            if (child instanceof Origin childOrigin) {
                graphParse(head, childOrigin.children);
                return;
            }
        }
    }

    /**
     * Считает BoundBox этого узла
     * @return BoundBox этого узла
     */
    @Override
    public BoundBox bound() {
        if (children.size() == 0) {
            return new BoundBox();
        }

        BoundBox result = new BoundBox();
        for (Point child : children) {
            BoundBox childBox = child.bound();

            result = BoundBox.merge(result, childBox);
        }

        return BoundBox.moveBox(result, getPosition());
    }
}
