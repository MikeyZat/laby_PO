package agh.cs.lab1;

import java.util.Arrays;
public class World {
    public static void main(String[] args){
        // LAB2
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//
//        MapDirection testEnum = MapDirection.NORTH;
//        System.out.println(testEnum.next());
//        System.out.println(testEnum.previous());
//        System.out.println(testEnum.toString());
//        System.out.println(testEnum.toUnitVector());

        // LAB3
        Animal mikey = new Animal();
        OptionsParser parser = new OptionsParser();
        System.out.println(mikey);
        String[] stringOrders = {"forward", "right", "l","l", "b", "backward", "test", "right", "forward"};
        MoveDirection[] orders = parser.parse(stringOrders);
        Arrays.stream(orders).forEach(mikey::move);
        System.out.println(mikey);
    }
}
