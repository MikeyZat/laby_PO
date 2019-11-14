package agh.cs.lab1;


import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private boolean changed;
    private Map<Vector2d, Grass> grasses = new HashMap<>();

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
                grasses.put(position, grass);
                grass_number--;
            }
        }
        for (IMapObject grass : grasses.values()) {
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

        for (Grass grass : grasses.values()) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if (changed) {
            Map<Vector2d, IMapObject> objectsInMap = new HashMap<>(animals);
            objectsInMap.putAll(grasses);
            for (IMapObject objectInMap : objectsInMap.values()) {
                leftDownCorner = objectInMap.getPosition().lowerLeft(leftDownCorner);
                rightUpCorner = objectInMap.getPosition().upperRight(rightUpCorner);
            }
            changed = false;
        }
        return super.toString();
    }
}