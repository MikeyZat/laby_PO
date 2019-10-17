package agh.cs.lab1;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {

    public MoveDirection[] parse(String[] stringDirections){
        return Arrays.stream(stringDirections)
                .map(OptionsParser::getDirectionFromString)
                .filter(Objects::nonNull)
                .toArray(MoveDirection[]::new);
    }

    private static MoveDirection getDirectionFromString(String direction) {
        switch (direction){
            case "f":
            case "forward":
                return MoveDirection.FORWARD;
            case "b":
            case "backward":
                return MoveDirection.BACKWARD;
            case "r":
            case "right":
                return MoveDirection.RIGHT;
            case "l":
            case "left":
                return MoveDirection.LEFT;
            default:
                return null;
        }
    }
}
