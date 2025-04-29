package Pozadie;

import fri.shapesge.Obrazok;

public class Background {
    public Background() {
        Obrazok obrazok = new Obrazok("pics/Background1.png");
        obrazok.zmenPolohu(0, 0);
        obrazok.zobraz();
    }
}
