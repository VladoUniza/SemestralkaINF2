package Postavy;

import Pozadie.Cards;
import Pozadie.Elixir;
import fri.shapesge.Manazer;

public class Soldier extends Figure implements Cards {
    private Elixir elixir;
    private Manazer manazer;
    private boolean damageDoubled;

    public Soldier(int polohaX, int polohaY, int rychlost, Elixir elixir, Manazer manazer, int maxHP) {
        super(7, 3, "sword", polohaX, polohaY, rychlost, false, maxHP, 50, 1, 70);
        this.elixir = elixir;
        this.manazer = manazer;
        this.damageDoubled = false;
    }

    public Soldier(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "Esword", polohaX, polohaY, rychlost, jeNepriatel, maxHP, 50, 2, 70);
        this.damageDoubled = false;
    }

    @Override
    public void click() {
        if (this.elixir.getcount() >= this.cost()) {
            Soldier vojak = new Soldier(200, 900, 8, this.elixir, this.manazer, 50);
            this.elixir.substractElixir(vojak.cost() + 1);
            this.manazer.spravujObjekt(vojak);
        }
    }

    @Override
    public int cost() {
        return 4;
    }

    @Override
    public int getDamage() {
        if (damageDoubled) {
            return 2 * super.getDamage();
        }
        return super.getDamage();
    }

    @Override
    public void ability() {
        if (!damageDoubled) {
            for (Figure figure : getVsetkyPostavy()) {
                if (!figure.getIsEnemy() && figure instanceof Soldier && figure.getHp() <= figure.getMaxHP() / 2) {
                    damageDoubled = true;
                    break;
                }
            }
        }
    }
}