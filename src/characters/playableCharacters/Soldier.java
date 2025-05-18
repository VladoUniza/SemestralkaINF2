package characters.playableCharacters;

import characters.Battlefield;
import characters.Cards;
import characters.Figure;
import hud.Gold;
import fri.shapesge.Manazer;

public class Soldier extends Figure implements Cards {
    private static final int SOLDIER_HP = 50;
    private static final int SOLDIER_DAMAGE = 1;
    private static final int SOLDIER_RANGE = 70;
    private static final int SOLDIER_SPEED = 16;

    private Gold gold;
    private Manazer manazer;
    private boolean damageDoubled;

    public Soldier(int positionX, int positionY, int speed, Gold gold, Manazer manazer, int maxHP) {
        super(7, 3, "sword", positionX, positionY, speed, false, maxHP, SOLDIER_HP, SOLDIER_DAMAGE, SOLDIER_RANGE);
        this.gold = gold;
        this.manazer = manazer;
        this.damageDoubled = false;
    }

    public Soldier(int positionX, int positionY, int speed, boolean isEnemy, int maxHP) {
        super(7, 3, "Esword", positionX, positionY, speed, isEnemy, maxHP, SOLDIER_HP, SOLDIER_DAMAGE, SOLDIER_RANGE);
        this.damageDoubled = false;
    }

    @Override
    public void click() {
        if (this.gold.getCount() >= this.cost()) {
            Soldier soldier = new Soldier(200, 900, SOLDIER_SPEED, this.gold, this.manazer, SOLDIER_HP);
            this.gold.takeGold(soldier.cost());
            this.gold.setGoldSpent(soldier.cost());
            this.manazer.spravujObjekt(soldier);
        } else {
            this.gold.takeGold(0);
        }
    }

    @Override
    public int cost() {
        return 4;
    }

    @Override
    public int getDamage() {
        if (this.damageDoubled) {
            return 2 * super.getDamage();
        }
        return super.getDamage();
    }

    @Override
    public void ability() {
        if (!this.damageDoubled) {
            for (Figure figure : Battlefield.getAllFiguresInBattle()) {
                if (figure.getIsAlly() && figure.getHpFromHpBar() <= figure.getMaxHP() / 2) {
                    this.damageDoubled = true;
                    break;
                }
            }
        }
    }
}