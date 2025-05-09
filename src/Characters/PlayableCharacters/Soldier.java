package Characters.PlayableCharacters;

import Characters.Cards;
import Characters.Figure;
import Characters.Gold;
import fri.shapesge.Manazer;

public class Soldier extends Figure implements Cards {
    private Gold gold;
    private Manazer manazer;
    private boolean damageDoubled;

    public Soldier(int polohaX, int polohaY, int rychlost, Gold gold, Manazer manazer, int maxHP) {
        super(7, 3, "sword", polohaX, polohaY, rychlost, false, maxHP, 50, 1, 70);
        this.gold = gold;
        this.manazer = manazer;
        this.damageDoubled = false;
    }

    public Soldier(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "Esword", polohaX, polohaY, rychlost, jeNepriatel, maxHP, 50, 2, 70);
        this.damageDoubled = false;
    }

    @Override
    public void click() {
        System.out.println(this.gold.getcount());
        System.out.println(this.cost());
        if (this.gold.getcount() >= this.cost() && (this.gold.getcount() - (this.cost() + 1)) >= 0) {
            Soldier vojak = new Soldier(200, 900, 10, this.gold, this.manazer, 5);
            this.gold.substractGold(vojak.cost());
            this.gold.goldSpent(vojak.cost());
            this.manazer.spravujObjekt(vojak);
        }
    }

    @Override
    public int cost() {
        return 4;
    }

    @Override
    public int Damage() {
        if (damageDoubled) {
            return 2 * super.Damage();
        }
        return super.Damage();
    }

    @Override
    public void ability() {
        if (!damageDoubled) {
            for (Figure figure : getAllFiguresInBattle()) {
                if (figure.getIsNotEnemy() && figure instanceof Soldier && figure.getHp() <= figure.getMaxHP() / 2) {
                    damageDoubled = true;
                    break;
                }
            }
        }
    }
}