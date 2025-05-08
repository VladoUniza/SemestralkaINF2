package Magic;

import Charaters.Figure;
import Charaters.Cards;
import Charaters.Gold;

public class Shield implements Cards {
    private final Gold gold;

    public Shield(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost()) {
            this.gold.substractGold(this.cost() + 1);

            for (Figure figure : Figure.getAllFiguresInBattle()) {
                if (!figure.getIsEnemy() && figure.getHpBar().getShield() > (figure.getMaxHP()/2)) {
                    this.gold.substractGold(0);
                } else if (!figure.getIsEnemy()) {
                    figure.getHpBar().setShield(figure.getMaxHP()/2);
                }
            }
        } else {
            this.gold.substractGold(0);
        }
    }

    @Override
    public int cost() {
        return 3;
    }
}