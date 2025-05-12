package Magic;

import Characters.Figure;
import Characters.Cards;
import Characters.Gold;

public class Healing implements Cards {
    private final Gold gold;

    public Healing(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost()) {
            this.gold.substractGold(this.cost());
            this.gold.goldSpent(this.cost());

            for (Figure figure : Figure.getAllFiguresInBattle()) {
                if (figure.getIsNotEnemy() && figure.getHpFromHpBar() < figure.getMaxHP()) {
                    figure.setHPOfHpBar(figure.getMaxHP());
                }
            }
        } else {
            this.gold.substractGold(0);
        }
    }

    @Override
    public int cost() {
        return 5;
    }
}