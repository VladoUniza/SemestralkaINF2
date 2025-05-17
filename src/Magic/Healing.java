package Magic;

import Characters.Battlefield;
import Characters.Figure;
import Characters.Cards;
import Hud.Gold;

public class Healing implements Cards {
    private final Gold gold;

    public Healing(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getcount() > this.cost()) {
            this.gold.substractGold(this.cost());
            this.gold.setGoldSpent(this.cost());

            for (Figure figure : Battlefield.getAllFiguresInBattle()) {
                if (figure.getIsAlly() && figure.getHpFromHpBar() < figure.getMaxHP()) {
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