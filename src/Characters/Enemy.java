package Characters;

import Characters.PlayableCharacters.Archer;
import Characters.PlayableCharacters.Soldier;
import Characters.PlayableCharacters.SpearMan;
import fri.shapesge.Manazer;
import java.util.Random;

public class Enemy extends Character {
    private static final int POLOHAX = 1620;
    private static final int POLOHAY = 900;

    private static final int SOLDIER_SPEED = 10;
    private static final int ARCHER_SPEED = 12;
    private static final int SPEARMAN_SPEED = 8;

    private final Manazer manazer;
    private final HpBar hpBar;
    private final Random rand;

    public Enemy() {
        super(500, 0,0);
        this.manazer = new Manazer();
        this.hpBar = new HpBar(1400, 700, this);
        this.hpBar.changeColor("red");
        this.hpBar.show();
        this.rand = new Random();
    }

    public void countdown() {
        int nahoda = rand.nextInt(10);
        if (nahoda <= 5) {
            var romanSoldier = new Soldier(POLOHAX, POLOHAY, SOLDIER_SPEED, true, this.getHealth());
            this.manazer.spravujObjekt(romanSoldier);
        } else if(nahoda <= 8) {
            var archer = new Archer(POLOHAX, POLOHAY, ARCHER_SPEED, true, this.getHealth());
            this.manazer.spravujObjekt(archer);
        } else {
            var spearman = new SpearMan(POLOHAX, POLOHAY, SPEARMAN_SPEED, true, this.getHealth());
            this.manazer.spravujObjekt(spearman);
        }
    }

    @Override
    protected void updateHpBar(int amount) {
        this.hpBar.substractHp(amount);
    }

    public int getX() {
        return POLOHAX;
    }
}