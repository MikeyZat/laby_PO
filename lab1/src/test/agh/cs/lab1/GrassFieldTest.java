package agh.cs.lab1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrassFieldTest {
    private IWorldMap map;
    private Animal animal1;
    private Animal animal2;
    @Before
    public void init(){
        String[] customArgs = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(customArgs);
        map = new GrassField(10);
        animal1 = new Animal(map);
        animal2 = new Animal(map, new Vector2d(3, 4));
        map.place(animal1);
        map.place(animal2);
        map.run(directions);
    }

    public void objectAt() {
        assertNull(map.objectAt(new Vector2d(1, 1)));
        assertEquals(animal1, map.objectAt(animal1.getPosition()));
        assertEquals(animal2, map.objectAt(animal2.getPosition()));
    }

    @Test
    public void isOccupied() {
        assertTrue(map.isOccupied(animal1.getPosition()));
        assertTrue(map.isOccupied(animal2.getPosition()));
        assertFalse(map.isOccupied(new Vector2d(1, 3)));
    }

    @Test
    public void canMoveTo() {
        assertFalse(map.canMoveTo(animal1.getPosition()));
        assertFalse(map.canMoveTo(animal2.getPosition()));
        assertTrue(map.canMoveTo(new Vector2d(8,4)));
        assertTrue(map.canMoveTo(new Vector2d(3, 11)));
    }


    @Test
    public void place() {
        Animal newAnimal3 = new Animal(map, new Vector2d(9,3));
        Animal newAnimal4 = new Animal(map, new Vector2d(3,43));
        assertTrue(map.place(newAnimal3));
        assertTrue(map.place(newAnimal4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void placePositionTaken() {
        Animal newAnimal1 = new Animal(map, animal1.getPosition());
        assertFalse(map.place(newAnimal1));
    }

    @Test
    public void run() {
        Vector2d oldCord = animal1.getPosition();
        String[] customArgs = {"f", "b", "r", "l", "f", "l", "r", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(customArgs);
        map.run(directions);
        assertEquals("N", map.objectAt(new Vector2d(1, -1)).toString());
        assertEquals("S", map.objectAt(new Vector2d(3, 3)).toString());
        assertTrue(map.canMoveTo(new Vector2d(3, 4)));
        assertTrue(map.canMoveTo(oldCord));

    }
}