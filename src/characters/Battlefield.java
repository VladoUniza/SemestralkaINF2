package characters;

import java.util.ArrayList;

public class Battlefield {
    private static final ArrayList<Figure> FIGURES_IN_BATTLE = new ArrayList<>();

    public static void addFigure(Figure figure) {
        FIGURES_IN_BATTLE.add(figure);
    }

    public static void removeFigure(Figure figure) {
        FIGURES_IN_BATTLE.remove(figure);
    }

    public static ArrayList<Figure> getAllFiguresInBattle() {
        return FIGURES_IN_BATTLE;
    }
}