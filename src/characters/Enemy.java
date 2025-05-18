package characters;

import characters.playableCharacters.Archer;
import characters.playableCharacters.Soldier;
import characters.playableCharacters.Spearman;
import hud.HpBar;
import fri.shapesge.BlokTextu;
import fri.shapesge.Manazer;
import fri.shapesge.StylFontu;
import level.Background;
import java.util.List;
import java.util.Random;

public class Enemy extends Character {
    private static final int POSITION_X = 1620;
    private static final int POSITION_Y = 900;

    private static final int SOLDIER_SPEED = 16;
    private static final int ARCHER_SPEED = 12;
    private static final int SPEARMAN_SPEED = 8;

    private final Manazer manazer;
    private final HpBar hpBar;
    private final Random rand;

    private boolean usedAbility75 = false;
    private boolean usedAbility25 = false;

    private final List<Figure> allUnitsInBattle;

    public Enemy(List<Figure> allUnitsInBattle) {
        super(500, 0, 0);
        this.manazer = new Manazer();
        this.hpBar = new HpBar(1400, 700, this);
        this.hpBar.changeColor("red");
        this.hpBar.show();
        this.rand = new Random();
        this.allUnitsInBattle = allUnitsInBattle;
    }

    public void countdown() {
        int archer = 0;
        int soldier = 0;
        int spearman = 0;

        for (Figure figure : this.allUnitsInBattle) {
            if (figure.getIsAlly()) {
                continue;
            }
            if (figure instanceof Archer) {
                archer++;
            } else if (figure instanceof Soldier) {
                soldier++;
            } else if (figure instanceof Spearman) {
                spearman++;
            }
        }

        if (this.allUnitsInBattle.size() <= 2) {
            int choice = this.rand.nextInt(3);
            switch (choice) {
                case 0:
                    this.manazer.spravujObjekt(new Soldier(POSITION_X, POSITION_Y, SOLDIER_SPEED, true, this.getHealth()));
                    break;
                case 1:
                    this.manazer.spravujObjekt(new Archer(POSITION_X, POSITION_Y, ARCHER_SPEED, true, this.getHealth()));
                    break;
                default:
                    this.manazer.spravujObjekt(new Spearman(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth()));
                    break;
            }
        } else if (soldier > archer || spearman > archer) {
            this.manazer.spravujObjekt(new Archer(POSITION_X, POSITION_Y, ARCHER_SPEED, true, this.getHealth()));
        } else if (archer >= 3) {
            this.manazer.spravujObjekt(new Spearman(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth()));
        } else {
            this.manazer.spravujObjekt(new Soldier(POSITION_X, POSITION_Y, SOLDIER_SPEED, true, this.getHealth()));
        }
    }

    @Override
    public void onDeath() {
        this.showDefeatMessage();
    }

    protected void updateHpBar(int amount) {
        this.hpBar.takeHpOrShield(amount);
        this.ability();
    }

    @Override
    public void ability() {
        double maxHp = 500.0;
        double currentHp = this.getHealth();

        if (!this.usedAbility75 && currentHp <= maxHp * 0.75) {
            this.usedAbility75 = true;

            switch (Background.getCurrentEra()) {
                case STONE_AGE:
                    for (int i = 0; i < 2; i++) {
                        var soldier = new Soldier(POSITION_X, POSITION_Y, SOLDIER_SPEED, true, this.getHealth());
                        this.manazer.spravujObjekt(soldier);
                    }
                    break;
                case ROMAN_EMPIRE:
                    var archer = new Archer(POSITION_X, POSITION_Y, ARCHER_SPEED, true, this.getHealth());
                    var spearman = new Spearman(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth());
                    this.manazer.spravujObjekt(spearman);
                    this.manazer.spravujObjekt(archer);
                    break;
                case DARK_AGES:
                    for (int i = 0; i < 3; i++) {
                        var spearman2 = new Spearman(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth());
                        this.manazer.spravujObjekt(spearman2);
                    }
                    break;
            }
        }

        if (!this.usedAbility25 && currentHp <= maxHp * 0.25) {
            this.usedAbility25 = true;

            switch (Background.getCurrentEra()) {
                case STONE_AGE:
                    for (int i = 0; i < 2; i++) {
                        var soldier = new Soldier(POSITION_X, POSITION_Y, SOLDIER_SPEED, true, this.getHealth());
                        this.manazer.spravujObjekt(soldier);
                    }
                    break;
                case ROMAN_EMPIRE:
                    var archer = new Archer(POSITION_X, POSITION_Y, ARCHER_SPEED, true, this.getHealth());
                    var spearman = new Spearman(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth());
                    this.manazer.spravujObjekt(spearman);
                    this.manazer.spravujObjekt(archer);
                    break;
                case DARK_AGES:
                    for (int i = 0; i < 3; i++) {
                        var spearman2 = new Spearman(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth());
                        this.manazer.spravujObjekt(spearman2);
                    }
                    break;
            }
        }
    }

    public int getX() {
        return POSITION_X;
    }

    private void showDefeatMessage() {
        BlokTextu text = new BlokTextu("DEFEAT", 500, 500);
        text.zmenPolohu(500, 500);
        text.zmenFont("Arial", StylFontu.BOLD, 200);
        text.zmenFarbu("black");
        text.zobraz();
    }
}