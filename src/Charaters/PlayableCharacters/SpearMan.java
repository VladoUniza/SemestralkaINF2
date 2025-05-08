package Charaters.PlayableCharacters;

import Charaters.Cards;
import Charaters.Figure;
import Charaters.Gold;
import fri.shapesge.Manazer;

public class SpearMan extends Figure implements Cards {
    private Gold gold;
    private Manazer manazer;

    public SpearMan(int polohaX, int polohaY, int rychlost, Gold gold, Manazer manazer, int maxHP) {
        super(7, 3, "spear", polohaX, polohaY, rychlost, false, maxHP, 1, 3, 70);
        this.gold = gold;
        this.manazer = manazer;
    }

    public SpearMan(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "Espear", polohaX, polohaY, rychlost, jeNepriatel, maxHP, 1, 3, 70);
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost() && (this.gold.getcount() - (this.cost() + 1)) >= 0) {
            SpearMan oto = new SpearMan(200, 900, 8, this.gold, this.manazer, 1);
            this.gold.substractGold(oto.cost());
            this.gold.goldSpent(oto.cost());
            this.manazer.spravujObjekt(oto);
        } else {
            this.gold.substractGold(0);
        }
    }

    @Override
    public int cost() {
        return 8;
    }

    @Override
    public void ability() {
        for (Figure figure : getAllFiguresInBattle()) {
            if (!figure.getIsEnemy() && figure instanceof SpearMan && figure.getHp() <= figure.getMaxHP() / 2) {
                figure.setHP(figure.getMaxHP());
            }
        }
    }
}