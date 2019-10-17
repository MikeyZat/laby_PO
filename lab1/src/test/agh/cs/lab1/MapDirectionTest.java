package agh.cs.lab1;

import org.junit.Test;
import static org.junit.Assert.*;

public class MapDirectionTest {

    private MapDirection west = MapDirection.WEST;
    private MapDirection east = MapDirection.EAST;
    private MapDirection north = MapDirection.NORTH;
    private MapDirection south = MapDirection.SOUTH;

    @Test
    public void next() {
        assertEquals(north.next(), east);
        assertEquals(east.next(), south);
        assertEquals(south.next(), west);
        assertEquals(west.next(), north);
    }

    @Test
    public void previous() {
        assertEquals(north, east.previous());
        assertEquals(east, south.previous());
        assertEquals(south, west.previous());
        assertEquals(west, north.previous());
    }
}