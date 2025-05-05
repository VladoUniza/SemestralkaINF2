package Magic;


import Charaters.Figure;
import Charaters.Cards;
import Charaters.Gold;

import java.util.ArrayList;

public class Thunder implements Cards {
    private final Gold gold;

    public Thunder(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost()) {
            this.gold.substractGold(this.cost() + 1);
            this.takeHalfHP();
        } else {
            this.gold.substractGold(0);
        }
    }

    public void takeHalfHP() {
        ArrayList<Figure> naZabitie = new ArrayList<>();

        for (Figure figure : Figure.getVsetkyPostavy()) {
            int halfHP = (figure.getMaxHP() / 2) + 1;
            if (figure.getHp() <= halfHP) {
                naZabitie.add(figure);
            } else {
                figure.takeHP(halfHP);
            }
        }
        for (Figure figure : naZabitie) {
            figure.takeHP(figure.getHp());
        }
    }

    @Override
    public int cost() {
        return 10;
    }
}