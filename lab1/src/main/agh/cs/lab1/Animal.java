package agh.cs.lab1;

public class Animal {
    private Vector2d position;
    private MapDirection orientation;

    public Animal() {
        this(2, 2);
    }

    public Animal(int x, int y) {
        position = new Vector2d(x, y);
        orientation = MapDirection.NORTH;
    }

    @Override
    public String toString() {
        return "this animal stands at " + position.toString() + " and is oriented to the " + orientation.toString();
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
                if (newPosition.precedes(new Vector2d(4,4)) && newPosition.follow(new Vector2d(0, 0))) {
                    position = newPosition;
                }
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
