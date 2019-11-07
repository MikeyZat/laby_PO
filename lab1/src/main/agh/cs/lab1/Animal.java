package agh.cs.lab1;

public class Animal implements IMapObject{
    private Vector2d position;
    private MapDirection orientation;
    private IWorldMap map;

    public Animal() {
        this(2, 2);
    }

    public Animal(int x, int y) {
        position = new Vector2d(x, y);
        orientation = MapDirection.NORTH;
    }

    public Animal(IWorldMap map) {
        this();
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initPosition) {
        this.map = map;
        this.position = initPosition;
        orientation = MapDirection.NORTH;
    }

    @Override
    public String toString() {
        switch (orientation) {
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case WEST:
                return "W";
            case EAST:
                return "E";
            default:
                return "Default";
        }
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT:
                orientation = orientation.next();
                break;
            case LEFT:
                orientation = orientation.previous();
                break;
            case FORWARD:
            case BACKWARD:
                Vector2d delta = orientation.toUnitVector();
                if (direction == MoveDirection.BACKWARD) delta = delta.opposite();
                Vector2d newPosition = position.add(delta);
                if (map.canMoveTo(newPosition))
                    position = newPosition;
                break;
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }
}
