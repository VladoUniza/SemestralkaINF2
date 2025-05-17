package Magic;


import Characters.Battlefield;
import Characters.Figure;
import Characters.Cards;
import Hud.Gold;

import java.util.ArrayList;

public class Thunder implements Cards {
    private final Gold gold;

    public Thunder(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost()) {
            this.gold.substractGold(this.cost());
            this.gold.setGoldSpent(this.cost());
            this.takeHalfHP();
        } else {
            this.gold.substractGold(0);
        }
    }

    public void takeHalfHP() {
        ArrayList<Figure> copy = new ArrayList<>(Battlefield.getAllFiguresInBattle());
        for (Figure figure : copy) {
            int halfHP = (figure.getMaxHP() / 2);

            if (figure.getHpFromHpBar() <= halfHP) {
                figure.takeHP(100);
                Figure.incrementNumberOfDeadEnemies(1);
            } else {
                figure.takeHP(halfHP);
            }
        }
    }

    @Override
    public int cost() {
        return 10;
    }
}