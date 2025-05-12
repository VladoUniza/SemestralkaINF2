package Characters.PlayableCharacters;

import Characters.Cards;
import Characters.Figure;
import Characters.Gold;
import fri.shapesge.Manazer;

public class SpearMan extends Figure implements Cards {
    private static final int SPEARMAN_HP = 100;
    private static final int SPEARMAN_DAMAGE = 4;
    private static final int SPEARMAN_RANGE = 70;
    private static final int SPEARMAN_SPEED = 8;

    private Gold gold;
    private Manazer manazer;

    public SpearMan(int polohaX, int polohaY, int rychlost, Gold gold, Manazer manazer, int maxHP) {
        super(7, 3, "spear", polohaX, polohaY, rychlost, false, maxHP, SPEARMAN_HP, SPEARMAN_DAMAGE, SPEARMAN_RANGE);
        this.gold = gold;
        this.manazer = manazer;
    }

    public SpearMan(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "Espear", polohaX, polohaY, rychlost, jeNepriatel, maxHP, SPEARMAN_HP, SPEARMAN_DAMAGE, SPEARMAN_RANGE);
    }

    @Override
    public void click() {
        if (this.gold.getcount() >= this.cost()) {
            SpearMan oto = new SpearMan(200, 900, SPEARMAN_SPEED, this.gold, this.manazer, SPEARMAN_HP);
            this.gold.substractGold(oto.cost());
            this.gold.goldSpent(oto.cost());
            this.manazer.spravujObjekt(oto);
        } else {
            this.gold.substractGold(0);
        }
    }

    @Override
    public int cost() {
        return 8;
    }

    @Override
    public void ability() {
        for (Figure figure : getAllFiguresInBattle()) {
            if (figure.getIsNotEnemy() && figure instanceof SpearMan && figure.getHpFromHpBar() <= figure.getMaxHP() / 2) {
                figure.setHPOfHpBar(figure.getMaxHP());
            }
        }
    }
}