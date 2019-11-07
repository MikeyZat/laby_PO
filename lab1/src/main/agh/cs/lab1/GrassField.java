package agh.cs.lab1;

import java.util.ArrayList;
import java.util.Random;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private boolean changed;
    private ArrayList<Grass> grasses = new ArrayList<>();

    public GrassField(int grass_number){
        int size = (int)Math.sqrt((double) grass_number*10);

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

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        changed = super.place(animal);
        return changed;
    }

    @Override
    public void run(MoveDirection[] directions) {
        super.run(directions);
        changed = true;
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object animal = super.objectAt(position);
        if ( animal != null ) return animal;

        for (Grass grass : grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
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
        return super.toString();
    }
}