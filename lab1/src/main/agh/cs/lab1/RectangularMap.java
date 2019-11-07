package agh.cs.lab1;


import java.util.ArrayList;

public class RectangularMap extends AbstractWorldMap implements IWorldMap {
    private final int START_X = 0;
    private final int START_Y = 0;

    public RectangularMap(int width, int height) {
        rightUpCorner = new Vector2d(START_X + width, START_Y + height);
        leftDownCorner = new Vector2d(START_X, START_Y);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) &&
                position.precedes(rightUpCorner) && position.follow(leftDownCorner);
    }

}
