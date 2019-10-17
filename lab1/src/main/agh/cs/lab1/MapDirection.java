package agh.cs.lab1;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    @Override
    public String toString() {
        switch (this) {
            case NORTH: return "Północ";
            case SOUTH: return "Południe";
            case WEST: return "Zachód";
            case EAST: return "Wschód";
            default: return "Default";
        }
    }

    public MapDirection next() {
        switch (this) {
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            case EAST: return SOUTH;
        }
        return NORTH;
    }

    public MapDirection previous() {
        switch (this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case EAST: return NORTH;
        }
        return NORTH;
    }

    public Vector2d toUnitVector() {
        int x = this == EAST ? 1 : (this == WEST ? -1 : 0);
        int y = this == NORTH ? 1 : (this == SOUTH ? -1 : 0);
        return new Vector2d(x, y);
    }
}
