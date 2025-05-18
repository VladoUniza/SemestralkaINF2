package level;

import hud.Gold;
import fri.shapesge.Obrazok;

public class Background {
    private final Obrazok screen;
    private static Era currentEra;

    public Background() {
        this.screen = new Obrazok("pics/BG/Background1.png");
        this.screen.zmenPolohu(0, 0);
        this.screen.zobraz();
        currentEra = Era.STONE_AGE;
    }

    public void tikBackground() {
        if (Gold.getGoldSpent() >= 100) {
            this.screen.zmenObrazok("pics/BG/Background2.png");
            currentEra = Era.ROMAN_EMPIRE;
        }
        if (Gold.getGoldSpent() >= 200) {
            this.screen.zmenObrazok("pics/BG/Background3.png");
            currentEra = Era.ROMAN_EMPIRE;
        }
    }

    public void loadBG(int era) {
        switch (era) {
            case 1:
                this.screen.zmenObrazok("pics/BG/Background1.png");
                currentEra = Era.STONE_AGE;
                break;
            case 2:
                this.screen.zmenObrazok("pics/BG/Background2.png");
                currentEra = Era.ROMAN_EMPIRE;
                break;
            case 3:
                this.screen.zmenObrazok("pics/BG/Background3.png");
                currentEra = Era.ROMAN_EMPIRE;
                break;
        }
    }

    public static Era getCurrentEra() {
        return currentEra;
    }
}