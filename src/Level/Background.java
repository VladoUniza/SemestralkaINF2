package Level;

import Characters.Gold;
import fri.shapesge.Obrazok;

public class Background {

    private final Obrazok screen;

    public Background() {
        screen = new Obrazok("pics/Background1.png");
        screen.zmenPolohu(0, 0);
        screen.zobraz();
    }

    public void tikBackground() {
        if (Gold.getGoldSpent() >= 100) {
            screen.zmenObrazok("pics/Background2.png");
            screen.zmenPolohu(0, 0);

        }
    }
}
