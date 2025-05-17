package Characters.PlayableCharacters;

import Characters.Cards;
import Characters.Figure;
import Characters.Gold;
import fri.shapesge.Manazer;

public class Soldier extends Figure implements Cards {
    private static final int SOLDIER_HP = 50;
    private static final int SOLDIER_DAMAGE = 1;
    private static final int SOLDIER_RANGE = 70;
    private static final int SOLDIER_SPEED = 16;

    private Gold gold;
    private Manazer manazer;
    private boolean damageDoubled;

    public Soldier(int polohaX, int polohaY, int rychlost, Gold gold, Manazer manazer, int maxHP) {
        super(7, 3, "sword", polohaX, polohaY, rychlost, false, maxHP, SOLDIER_HP, SOLDIER_DAMAGE, SOLDIER_RANGE);
        this.gold = gold;
        this.manazer = manazer;
        this.damageDoubled = false;
    }

    public Soldier(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "Esword", polohaX, polohaY, rychlost, jeNepriatel, maxHP, SOLDIER_HP, SOLDIER_DAMAGE, SOLDIER_RANGE);
        this.damageDoubled = false;
    }

    @Override
    public void click() {
        if (this.gold.getcount() >= this.cost()) {
            Soldier vojak = new Soldier(200, 900, SOLDIER_SPEED, this.gold, this.manazer, SOLDIER_HP);
            this.gold.substractGold(vojak.cost());
            this.gold.goldSpent(vojak.cost());
            this.manazer.spravujObjekt(vojak);
        } else {
            this.gold.substractGold(0);
        }
    }

    @Override
    public int cost() {
        return 4;
    }

    @Override
    public int getDamage() {
        if (damageDoubled) {
            return 2 * super.getDamage();
        }
        return super.getDamage();
    }

    @Override
    public void ability() {
        if (!damageDoubled) {
            for (Figure figure : getAllFiguresInBattle()) {
                if (figure.getIsNotEnemy() && figure instanceof Soldier && figure.getHpFromHpBar() <= figure.getMaxHP() / 2) {
                    damageDoubled = true;
                    break;
                }
            }
        }
    }
}