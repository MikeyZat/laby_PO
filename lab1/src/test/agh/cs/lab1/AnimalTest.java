package agh.cs.lab1;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class AnimalTest {

    private Animal normalAnimal;
    private Animal animal_4_0;
    private Animal animal_0_0;
    private Animal animal_0_4;
    private Animal animal_4_4;

    @Before
    public void init() {
        normalAnimal = new Animal();
        animal_4_0 = new Animal(4,0);
        animal_0_0 = new Animal(0,0);
        animal_4_4 = new Animal(4,4);
        animal_0_4 = new Animal(0,4);
    }

    @Test
    public void testToString() {
        assertEquals("this animal stands at (2,2) and is oriented to the Północ", normalAnimal.toString());
        assertEquals("this animal stands at (0,4) and is oriented to the Północ", animal_0_4.toString());
    }

    @Test
    public void testMove() {

        normalAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,3), normalAnimal.getPosition());

        normalAnimal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2,4), normalAnimal.getPosition());

        normalAnimal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2,3), normalAnimal.getPosition());
    }

    @Test
    public void testChangeDirection() {
        normalAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, normalAnimal.getOrientation());

        normalAnimal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, normalAnimal.getOrientation());

        normalAnimal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, normalAnimal.getOrientation());


    }

    @Test
    public void testGoBeyondMap() {
        // setting right direction
        animal_4_0.move(MoveDirection.RIGHT);
        animal_0_4.move(MoveDirection.LEFT);
        animal_0_0.move(MoveDirection.LEFT);
        animal_0_0.move(MoveDirection.LEFT);

        animal_4_0.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,0), animal_4_0.getPosition());

        animal_4_4.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4,4), animal_4_4.getPosition());

        animal_0_0.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0,0), animal_0_0.getPosition());

        animal_0_4.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0,4), animal_0_4.getPosition());
    }

    @Test
    public void testParser() {
        String[] stringOrders = {"forward", "f", "right", "r", "l","left", "backward", "test", "b",};
        MoveDirection[] expectedOrders = { MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT,
                MoveDirection.LEFT, MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.BACKWARD, };
        OptionsParser parser = new OptionsParser();
        assertArrayEquals(parser.parse(stringOrders), expectedOrders);
    }
}