package characters;

import characters.playableCharacters.Archer;
import characters.playableCharacters.Spearman;
import hud.Gold;
import hud.HpBar;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;
import fri.shapesge.Manazer;

public class Player extends Character {
    private static final int POSITION_X = 200;
    private static final int POSITION_Y = 900;

    private static final int ARCHER_SPEED = 12;
    private static final int SPEARMAN_SPEED = 8;

    private final HpBar hpBar;
    private final Manazer manazer;
    private final Gold gold;

    private boolean usedAbility75 = false;
    private boolean usedAbility25 = false;

    public Player(Gold gold) {
        super(500, 0, 0);

        this.gold = gold;
        this.manazer = new Manazer();
        this.hpBar = new HpBar(10, 700, this);
        this.hpBar.show();
    }

    @Override
    public void onDeath() {
        this.showVictoryMessage();
    }

    protected void updateHpBar(int amount) {
        this.hpBar.takeHpOrShield(amount);
        this.ability();
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

    @Override
    public void ability() {
        double maxHp = 500.0;
        double currentHp = this.getHealth();

        if (!this.usedAbility25 && currentHp <= maxHp * 0.75 || !this.usedAbility75 && currentHp <= maxHp * 0.25) {
            this.usedAbility25 = true;
            this.usedAbility75 = true;
            var archer = new Archer(POSITION_X, POSITION_Y, ARCHER_SPEED, this.gold, this.manazer, this.getHealth());
            var spearman = new Spearman(POSITION_X, POSITION_Y, SPEARMAN_SPEED, this.gold, this.manazer, this.getHealth());
            this.manazer.spravujObjekt(spearman);
            this.manazer.spravujObjekt(archer);
        }
    }
}