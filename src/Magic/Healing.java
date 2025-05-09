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
            this.gold.substractGold(this.cost() + 1);

            for (Figure figure : Figure.getAllFiguresInBattle()) {
                if (figure.getIsNotEnemy() && figure.getHp() < figure.getMaxHP()) {
                    figure.setHP(figure.getMaxHP());
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