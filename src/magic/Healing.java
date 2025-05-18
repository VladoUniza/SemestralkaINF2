package magic;

import characters.Battlefield;
import characters.Figure;
import characters.Cards;
import hud.Gold;

public class Healing implements Cards {
    private final Gold gold;

    public Healing(Gold gold) {
        this.gold = gold;
    }

    @Override
    public void click() {
        if (this.gold.getCount() > this.cost()) {
            this.gold.takeGold(this.cost());
            this.gold.setGoldSpent(this.cost());

            for (Figure figure : Battlefield.getAllFiguresInBattle()) {
                if (figure.getIsAlly() && figure.getHpFromHpBar() < figure.getMaxHP()) {
                    figure.setHPOfHpBar(figure.getMaxHP());
                }
            }
        } else {
            this.gold.takeGold(0);
        }
    }

    @Override
    public int cost() {
        return 5;
    }
}