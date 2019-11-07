package agh.cs.lab1;

import java.util.ArrayList;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Vector2d rightUpCorner;
    protected Vector2d leftDownCorner;
    protected ArrayList<Animal> animals = new ArrayList<>();

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
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(leftDownCorner, rightUpCorner);
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
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }

}
