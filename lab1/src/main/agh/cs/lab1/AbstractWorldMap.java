package agh.cs.lab1;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap,  IPositionChangeObserver {
    protected Vector2d rightUpCorner;
    protected Vector2d leftDownCorner;
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected List<Animal> animalsList = new ArrayList<>();
    protected MapBoundary boundries = new MapBoundary();

    @Override
    public void run(MoveDirection[] directions) {
        int animalsSize = animalsList.size();
        int directionsSize = directions.length;
        if (animalsSize == 0) {
            System.out.println("No animals in map");
            return;
        }
        for (int i = 0; i < directionsSize; i++) {
            Animal animal = animalsList.get(i % animalsSize);
            animal.move(directions[i]);
        }
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft(), upperRight());
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

    public Vector2d lowerLeft(){
        return boundries.leftCorner();
    }

    public Vector2d upperRight(){
        return boundries.rightCorner();
    }

    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.put(position, animal);
            boundries.addToSet(position);
            animalsList.add(animal);
            animal.addObserver(this);
            animal.addObserver(boundries);
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition() + " is already taken.");
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animals.get(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

}
