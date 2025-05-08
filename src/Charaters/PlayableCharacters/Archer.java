package Charaters.PlayableCharacters;

import Charaters.Cards;
import Charaters.Figure;
import Charaters.Gold;
import fri.shapesge.Manazer;

public class Archer extends Figure implements Cards {
    private Gold gold;
    private Manazer manazer;

    public Archer(int polohaX, int polohaY, int rychlost, Gold gold, Manazer manazer, int maxHP) {
        super(7, 12, "bow", polohaX, polohaY, rychlost, false, maxHP,25, 2, 210);
        this.gold = gold;
        this.manazer = manazer;
    }

    public Archer(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 12, "Ebow", polohaX, polohaY, rychlost, jeNepriatel, maxHP, 25, 2, 210);
    }
    public void click() {
        if (this.gold.getcount() > this.cost() && (this.gold.getcount() - (this.cost() + 1)) >= 0) {
            Archer lukostrelec = new Archer(200, 900, 12, this.gold, this.manazer, 25);
            this.gold.substractGold(lukostrelec.cost());
            this.gold.goldSpent(lukostrelec.cost());
            this.manazer.spravujObjekt(lukostrelec);
        } else {
            this.gold.substractGold(0);
        }
    }

    public int cost() {
        return 6;
    }

    public void ability() {
        for (Figure figure : getAllFiguresInBattle()) {
            if (!figure.getIsEnemy() && figure instanceof Archer && (figure.getHp() <= figure.getMaxHP() / 2)) {
                figure.getHpBar().setShield(25);
            }
        }
    }
}