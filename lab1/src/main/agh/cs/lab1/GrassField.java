package agh.cs.lab1;


import java.util.*;

public class GrassField extends AbstractWorldMap implements IWorldMap {
    private Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grass_number){
        int size = (int)Math.sqrt((double) grass_number*10);

        Random randomGenerator = new Random();
        while(grass_number > 0) {
            int x = randomGenerator.nextInt(size);
            int y = randomGenerator.nextInt(size);
            Vector2d position = new Vector2d(x, y);
            if (!(objectAt(position) instanceof Grass)){
                Grass grass = new Grass(position);
                grasses.put(position, grass);
                boundries.addToSet(position);
                grass_number--;
            }
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal);
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
        return super.toString();
    }
}