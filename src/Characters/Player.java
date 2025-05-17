package Characters;

import Hud.HpBar;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

public class Player extends Character {

    private final HpBar hpBar;

    public Player() {
        super(5,0,0);

        this.hpBar = new HpBar(10, 700, this);
        this.hpBar.show();
    }

    @Override
    public void onDeath() {
        showVictoryMessage();
    }

    @Override
    protected void updateHpBar(int amount) {
        this.hpBar.substractHp(amount);
    }

    public int getX() {
        return 200;
    }

    private void showVictoryMessage() {
        BlokTextu text = new BlokTextu("VICTORY", 500, 500);
        text.zmenPolohu(500, 500);
        text.zmenFarbu("black");
        text.zmenFont("Arial", StylFontu.BOLD, 200);
        text.zobraz();
    }
}