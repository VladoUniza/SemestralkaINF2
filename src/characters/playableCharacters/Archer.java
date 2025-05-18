package characters.playableCharacters;

import characters.Battlefield;
import characters.Cards;
import characters.Figure;
import hud.Gold;
import fri.shapesge.Manazer;

public class Archer extends Figure implements Cards {
    private static final int ARCHER_HP = 25;
    private static final int ARCHER_DAMAGE = 2;
    private static final int ARCHER_RANGE = 210;
    private static final int ARCHER_SPEED = 12;

    private Gold gold;
    private Manazer manazer;

    public Archer(int positionX, int positionY, int speed, Gold gold, Manazer manazer, int maxHP) {
        super(7, 12, "bow", positionX, positionY, speed, false, maxHP, ARCHER_HP, ARCHER_DAMAGE, ARCHER_RANGE);
        this.gold = gold;
        this.manazer = manazer;
    }

    public Archer(int positionX, int positionY, int speed, boolean isEnemy, int maxHP) {
        super(7, 12, "Ebow", positionX, positionY, speed, isEnemy, maxHP, ARCHER_HP, ARCHER_DAMAGE, ARCHER_RANGE);
    }

    @Override
    public void click() {
        if (this.gold.getCount() >= this.cost()) {
            Archer archer = new Archer(200, 900, ARCHER_SPEED, this.gold, this.manazer, ARCHER_HP);
            this.gold.takeGold(archer.cost());
            this.gold.setGoldSpent(archer.cost());
            this.manazer.spravujObjekt(archer);
        } else {
            this.gold.takeGold(0);
        }
    }

    @Override
    public int cost() {
        return 6;
    }

    @Override
    public void ability() {
        for (Figure figure : Battlefield.getAllFiguresInBattle()) {
            if (figure.getIsAlly() && (figure.getHpFromHpBar() <= figure.getMaxHP() / 2)) {
                figure.getHpBar().setShield(25);
            }
        }
    }
}