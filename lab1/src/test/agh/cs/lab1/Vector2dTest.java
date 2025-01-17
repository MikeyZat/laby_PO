package agh.cs.lab1;

import org.junit.Test;

import static org.junit.Assert.*;

public class Vector2dTest {

    private Vector2d v_1_4 = new Vector2d(1,4);
    private Vector2d v_3_1 = new Vector2d(3, 1);
    private Vector2d v_2_4 = new Vector2d(2, 4);

    @Test
    public void testToString() {
        assertEquals("(1,4)", v_1_4.toString());
    }

    @Test
    public void precedes() {
        assertTrue(v_1_4.precedes(v_2_4));
        assertFalse(v_2_4.precedes(v_1_4));
        assertFalse(v_1_4.precedes(v_3_1));
    }

    @Test
    public void follow() {
        assertFalse(v_1_4.follow(v_2_4));
        assertTrue(v_2_4.follow(v_1_4));
        assertFalse(v_1_4.follow(v_3_1));
    }

    @Test
    public void upperRight() {
        assertEquals(v_1_4.upperRight(v_2_4), v_2_4);
        assertEquals(v_1_4.upperRight(v_3_1), new Vector2d(3,4));
    }

    @Test
    public void lowerLeft() {
        assertEquals(v_1_4.lowerLeft(v_2_4), v_1_4);
        assertEquals(v_1_4.lowerLeft(v_3_1), new Vector2d(1,1));
    }

    @Test
    public void add() {
        assertEquals(v_1_4.add(v_3_1), new Vector2d(4, 5));
        assertEquals(v_2_4.add(v_3_1), new Vector2d(5, 5));
    }

    @Test
    public void substract() {
        assertEquals(v_1_4.substract(v_3_1), new Vector2d(-2, 3));
        assertEquals(v_2_4.substract(v_3_1), new Vector2d(-1, 3));
    }

    @Test
    public void testEquals() {
        assertNotEquals(v_1_4, v_3_1);
        assertEquals(v_1_4, v_1_4);
        assertEquals(v_2_4, new Vector2d(2,4));
    }

    @Test
    public void opposite() {
        assertEquals(v_2_4, new Vector2d(2,4));
        assertEquals(v_1_4, new Vector2d(1,4));
        assertEquals(v_3_1, new Vector2d(3,1));
    }
}