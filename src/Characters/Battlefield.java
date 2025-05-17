package Characters;

import java.util.ArrayList;

public class Battlefield {
    private static final ArrayList<Figure> figuresInBattle = new ArrayList<>();

    public static void addFigure(Figure figure) {
        figuresInBattle.add(figure);
    }

    public static void removeFigure(Figure figure) {
        figuresInBattle.remove(figure);
    }

    public static ArrayList<Figure> getAllFiguresInBattle() {
        return figuresInBattle;
    }
}