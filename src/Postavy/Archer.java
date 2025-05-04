package Postavy;

import Pozadie.Cards;
import Pozadie.Elixir;
import fri.shapesge.Manazer;

public class Archer extends Figure implements Cards {
    private Elixir elixir;
    private Manazer manazer;

    public Archer(int polohaX, int polohaY, int rychlost, Elixir elixir, Manazer manazer, int maxHP) {
        super(7, 12, "bow", polohaX, polohaY, rychlost, false, maxHP,25, 2, 210);
        this.elixir = elixir;
        this.manazer = manazer;
    }

    public Archer(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 12, "Ebow", polohaX, polohaY, rychlost, jeNepriatel, maxHP, 25, 2, 210);
    }
    public void click() {
        if (this.elixir.getcount() > this.cost()) {
            Archer lukostrelec = new Archer(200, 900, 8, this.elixir, this.manazer, 25);
            this.elixir.substractElixir(lukostrelec.cost() + 1);
            this.manazer.spravujObjekt(lukostrelec);
        } else {
            this.elixir.substractElixir(0);
        }
    }

    public int cost() {
        return 5;
    }

    public void ability() {
        for (Figure figure : getVsetkyPostavy()) {
            if (!figure.getIsEnemy() && figure instanceof Archer && (figure.getHp() <= figure.getMaxHP() / 2)) {
                figure.getHpBar().setShield(25);
            }
        }
    }
}