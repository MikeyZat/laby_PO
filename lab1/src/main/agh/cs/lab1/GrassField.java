package agh.cs.lab1;

import java.util.ArrayList;
import java.util.Random;

public class GrassField implements IWorldMap {
    private Vector2d rightUpCorner;
    private Vector2d leftDownCorner;
    private boolean changed;
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Grass> grasses = new ArrayList<>();

    public GrassField(int grass_number){
        int size = size(grass_number);

        Random randomGenerator = new Random();
        while(grass_number > 0) {
            int x = randomGenerator.nextInt(size);
            int y = randomGenerator.nextInt(size);
            Vector2d position = new Vector2d(x, y);
            if (!(objectAt(position) instanceof Grass)){
                leftDownCorner = position;
                rightUpCorner = position;
                Grass grass = new Grass(position);
                grasses.add(grass);
                grass_number--;
            }
        }
        for (IMapObject grass : grasses) {
            leftDownCorner = grass.getPosition().lowerLeft(leftDownCorner);
            rightUpCorner = grass.getPosition().upperRight(rightUpCorner);
        }
        changed = false;
    }

    private int size(int n){
        return (int)Math.sqrt((double) n*10);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            changed = true;
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
        changed = true;
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

        for (Grass grass : grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        if (changed) {
            for (IMapObject animal : animals) {
                leftDownCorner = animal.getPosition().lowerLeft(leftDownCorner);
                rightUpCorner = animal.getPosition().upperRight(rightUpCorner);
            }
            for (IMapObject grass : grasses) {
                leftDownCorner = grass.getPosition().lowerLeft(leftDownCorner);
                rightUpCorner = grass.getPosition().upperRight(rightUpCorner);
            }
            changed = false;
        }
        return visualizer.draw(leftDownCorner, rightUpCorner);
    }
}