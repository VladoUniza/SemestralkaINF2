package Level;

import Charaters.Gold;
import fri.shapesge.Obrazok;

public class Background {

    private final Gold gold;
    private final Obrazok screen;

    public Background(Gold gold) {
        this.gold = gold;
        screen = new Obrazok("pics/Background1.png");
        screen.zmenPolohu(0, 0);
        screen.zobraz();
    }

    public void tikBackground() {
        if (this.gold.getGoldSpent() >= 100) {
            screen.zmenObrazok("pics/Background2.png");
            screen.zmenPolohu(0, 0);

        }
    }
}
