package Magic;

import Characters.Figure;
import Characters.Cards;
import Characters.Gold;

public class Shield implements Cards {
    private final Gold gold;

    public Shield(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost()) {
            this.gold.substractGold(this.cost());
            this.gold.goldSpent(this.cost());

            for (Figure figure : Figure.getAllFiguresInBattle()) {
                if (figure.getIsNotEnemy() && figure.getHpBar().getShield() > (figure.getMaxHP()/2)) {
                    this.gold.substractGold(0);
                } else if (figure.getIsNotEnemy()) {
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