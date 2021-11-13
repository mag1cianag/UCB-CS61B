package byog.Core;

public class Room {
    public Position leftBottom;
    public Position rightTop;

    public Room(Position l, Position r) {
        leftBottom = l;
        rightTop = r;
    }

    public boolean isOverlap(Room other) {
        int x1 = leftBottom.getX();
        int y1 = leftBottom.getY();
        int x2 = rightTop.getX();
        int y2 = rightTop.getY();

        return x1 <= other.rightTop.getX()
                && x2 >= other.leftBottom.getX()
                && y1 <= other.rightTop.getY()
                && y2 >= other.leftBottom.getY();

    }

    public Position center() {
        return new Position((leftBottom.getX() + rightTop.getX()) / 2
                , (leftBottom.getY() + rightTop.getY()) / 2);
    }
}

