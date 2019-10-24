package agh.cs.lab1;


import java.util.ArrayList;

public class RectangularMap implements IWorldMap {
    private final int START_X = 0;
    private final int START_Y = 0;
    private Vector2d rightUpCorner;
    private Vector2d leftDownCorner;
    private ArrayList<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height) {
        rightUpCorner = new Vector2d(START_X + width, START_Y + height);
        leftDownCorner = new Vector2d(START_X, START_Y);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position) &&
                position.precedes(rightUpCorner) && position.follow(leftDownCorner);
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public void run(MoveDirection[] directions) {
        int animalsSize = animals.size();
        int directionsSize = directions.length;
        if (animalsSize == 0) {
            System.out.println("No animals in map");
            return;
        }
        for (int i = 0; i < directionsSize; i++) {
            animals.get(i % animalsSize).move(directions[i]);
        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(leftDownCorner, rightUpCorner);
    }
}
