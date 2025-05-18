package magic;

import characters.Battlefield;
import characters.Figure;
import characters.Cards;
import hud.Gold;

public class Shield implements Cards {
    private final Gold gold;

    public Shield(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getCount() > this.cost()) {
            this.gold.takeGold(this.cost());
            this.gold.setGoldSpent(this.cost());

            for (Figure figure : Battlefield.getAllFiguresInBattle()) {
                if (figure.getIsAlly() && figure.getHpBar().getShield() > (figure.getMaxHP() / 2)) {
                    this.gold.takeGold(0);
                } else if (figure.getIsAlly()) {
                    figure.getHpBar().setShield(figure.getMaxHP() / 2);
                }
            }
        } else {
            this.gold.takeGold(0);
        }
    }

    @Override
    public int cost() {
        return 3;
    }
}