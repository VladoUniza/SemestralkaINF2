package Level;

import Hud.Gold;
import fri.shapesge.Obrazok;

public class Background {
    private final Obrazok screen;
    private static Era currentEra;

    public Background() {
        screen = new Obrazok("pics/BG/Background1.png");
        screen.zmenPolohu(0, 0);
        screen.zobraz();
        currentEra = Era.STONE_AGE;
    }

    public void tikBackground() {
        if (Gold.getGoldSpent() >= 100) {
            screen.zmenObrazok("pics/BG/Background2.png");
            currentEra = Era.ROMAN_EMPIRE;
        }
    }

    public void loadBG(int era) {
        switch (era) {
            case 1:
                screen.zmenObrazok("pics/BG/Background1.png");
                currentEra = Era.STONE_AGE;
                break;
            case 2:
                screen.zmenObrazok("pics/BG/Background2.png");
                currentEra = Era.ROMAN_EMPIRE;
                break;
        }
    }

    public static Era getCurrentEra() {
        return currentEra;
    }
}