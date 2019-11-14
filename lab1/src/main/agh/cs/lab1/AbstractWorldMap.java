package agh.cs.lab1;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Vector2d rightUpCorner;
    protected Vector2d leftDownCorner;
    protected Map<Vector2d, Animal> animals = new HashMap<>();

    @Override
    public void run(MoveDirection[] directions) {
        int animalsSize = animals.size();
        int directionsSize = directions.length;
        if (animalsSize == 0) {
            System.out.println("No animals in map");
//            return;
        }
//        for (int i = 0; i < directionsSize; i++) {
//            animals.get(i % animalsSize).move(directions[i]);
//        }
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
        if (animals.containsKey(position)){
            return animals.get(position);
        }
        return null;
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        if (canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition() + " is already taken.");
    }

}
