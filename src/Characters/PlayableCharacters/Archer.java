package Characters.PlayableCharacters;

import Characters.Battlefield;
import Characters.Cards;
import Characters.Figure;
import Hud.Gold;
import fri.shapesge.Manazer;

public class Archer extends Figure implements Cards {
    private static final int ARCHER_HP = 25;
    private static final int ARCHER_DAMAGE = 2;
    private static final int ARCHER_RANGE = 210;
    private static final int ARCHER_SPEED = 12;

    private Gold gold;
    private Manazer manazer;

    public Archer(int polohaX, int polohaY, int rychlost, Gold gold, Manazer manazer, int maxHP) {
        super(7, 12, "bow", polohaX, polohaY, rychlost, false, maxHP,ARCHER_HP, ARCHER_DAMAGE, ARCHER_RANGE);
        this.gold = gold;
        this.manazer = manazer;
    }

    public Archer(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 12, "Ebow", polohaX, polohaY, rychlost, jeNepriatel, maxHP, ARCHER_HP, ARCHER_DAMAGE, ARCHER_RANGE);
    }

    @Override
    public void click() {
        if (this.gold.getcount() >= this.cost()) {
            Archer lukostrelec = new Archer(200, 900, ARCHER_SPEED, this.gold, this.manazer, ARCHER_HP);
            this.gold.substractGold(lukostrelec.cost());
            this.gold.setGoldSpent(lukostrelec.cost());
            this.manazer.spravujObjekt(lukostrelec);
        } else {
            this.gold.substractGold(0);
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