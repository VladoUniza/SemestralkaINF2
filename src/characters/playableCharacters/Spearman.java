package characters.playableCharacters;

import characters.Battlefield;
import characters.Cards;
import characters.Figure;
import hud.Gold;
import fri.shapesge.Manazer;

public class Spearman extends Figure implements Cards {
    private static final int SPEARMAN_HP = 100;
    private static final int SPEARMAN_DAMAGE = 4;
    private static final int SPEARMAN_RANGE = 70;
    private static final int SPEARMAN_SPEED = 8;

    private Gold gold;
    private Manazer manazer;

    public Spearman(int positionX, int positionY, int speed, Gold gold, Manazer manazer, int maxHP) {
        super(7, 3, "spear", positionX, positionY, speed, false, maxHP, SPEARMAN_HP, SPEARMAN_DAMAGE, SPEARMAN_RANGE);
        this.gold = gold;
        this.manazer = manazer;
    }

    public Spearman(int positionX, int positionY, int speed, boolean isEnemy, int maxHP) {
        super(7, 3, "Espear", positionX, positionY, speed, isEnemy, maxHP, SPEARMAN_HP, SPEARMAN_DAMAGE, SPEARMAN_RANGE);
    }

    @Override
    public void click() {
        if (this.gold.getCount() >= this.cost()) {
            Spearman oto = new Spearman(200, 900, SPEARMAN_SPEED, this.gold, this.manazer, SPEARMAN_HP);
            this.gold.takeGold(oto.cost());
            this.gold.setGoldSpent(oto.cost());
            this.manazer.spravujObjekt(oto);
        } else {
            this.gold.takeGold(0);
        }
    }

    @Override
    public int cost() {
        return 8;
    }

    @Override
    public void ability() {
        for (Figure figure : Battlefield.getAllFiguresInBattle()) {
            if (figure.getIsAlly() && figure.getHpFromHpBar() <= figure.getMaxHP() / 2) {
                figure.setHPOfHpBar(figure.getMaxHP());
            }
        }
    }
}