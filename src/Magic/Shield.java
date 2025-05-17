package Magic;

import Characters.Battlefield;
import Characters.Figure;
import Characters.Cards;
import Hud.Gold;

public class Shield implements Cards {
    private final Gold gold;

    public Shield(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost()) {
            this.gold.substractGold(this.cost());
            this.gold.setGoldSpent(this.cost());

            for (Figure figure : Battlefield.getAllFiguresInBattle()) {
                if (figure.getIsAlly() && figure.getHpBar().getShield() > (figure.getMaxHP()/2)) {
                    this.gold.substractGold(0);
                } else if (figure.getIsAlly()) {
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