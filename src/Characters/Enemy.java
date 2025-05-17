package Characters;

import Characters.PlayableCharacters.Archer;
import Characters.PlayableCharacters.Soldier;
import Characters.PlayableCharacters.SpearMan;
import fri.shapesge.Manazer;

import java.util.List;
import java.util.Random;

public class Enemy extends Character {
    private static final int POSITION_X = 1620;
    private static final int POSITION_Y = 900;

    private static final int SOLDIER_SPEED = 10;
    private static final int ARCHER_SPEED = 12;
    private static final int SPEARMAN_SPEED = 8;

    private final Manazer manazer;
    private final HpBar hpBar;
    private final Random rand;

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
        int archers = 0;
        int soldiers = 0;
        int spearmans = 0;

        for (Figure figure : allUnitsInBattle) {
            if (figure.getIsNotEnemy()) {
                continue;
            }
            if (figure instanceof Archer) {
                archers++;
            } else if (figure instanceof Soldier) {
                soldiers++;
            } else if (figure instanceof SpearMan) {
                spearmans++;
            }
        }

        if (allUnitsInBattle.size() <= 2) {
            int choice = rand.nextInt(3);
            switch (choice) {
                case 0:
                    manazer.spravujObjekt(new Soldier(POSITION_X, POSITION_Y, SOLDIER_SPEED, true, this.getHealth()));
                    break;
                case 1:
                    manazer.spravujObjekt(new Archer(POSITION_X, POSITION_Y, ARCHER_SPEED, true, this.getHealth()));
                    break;
                default:
                    manazer.spravujObjekt(new SpearMan(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth()));
                    break;
            }
        } else if (soldiers > archers || spearmans > archers) {
            manazer.spravujObjekt(new Archer(POSITION_X, POSITION_Y, ARCHER_SPEED, true, this.getHealth()));
        } else if (archers >= 3) {
            manazer.spravujObjekt(new SpearMan(POSITION_X, POSITION_Y, SPEARMAN_SPEED, true, this.getHealth()));
        } else {
            manazer.spravujObjekt(new Soldier(POSITION_X, POSITION_Y, SOLDIER_SPEED, true, this.getHealth()));
        }
    }

    @Override
    protected void updateHpBar(int amount) {
        this.hpBar.substractHp(amount);
    }

    public int getX() {
        return POSITION_X;
    }
}
