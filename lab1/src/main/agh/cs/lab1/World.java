package agh.cs.lab1;

import java.util.Arrays;

public class World {
    public static void main(String[] args) {

        String[] customArgs = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f"};
        try {
            MoveDirection[] directions = new OptionsParser().parse(customArgs);
            IWorldMap map = new GrassField(10);
            map.place(new Animal(map));
            map.place(new Animal(map));
            map.run(directions);
            System.out.println(map);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }
}
