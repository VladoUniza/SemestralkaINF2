package Postavy;

import Pozadie.Cards;
import Pozadie.Elixir;
import fri.shapesge.Manazer;

public class Archer extends Figure implements Cards {
    private Elixir elixir;
    private Manazer manazer;

    public Archer(int polohaX, int polohaY, int rychlost, Elixir elixir, Manazer manazer, int maxHP) {
        super(7, 12, "luk", polohaX, polohaY, rychlost, false, maxHP);
        this.elixir = elixir;
        this.manazer = manazer;
    }

    public Archer(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 12, "NEPluk", polohaX, polohaY, rychlost, jeNepriatel, maxHP);
    }
    public void click() {
        if (this.elixir.getpocet() > this.cost()) {
            Archer lukostrelec = new Archer(200, 900, 8, this.elixir, this.manazer, 25);
            this.elixir.odpocitajElixir(lukostrelec.cost() + 1);
            this.manazer.spravujObjekt(lukostrelec);
        } else {
            this.elixir.odpocitajElixir(0);
        }
    }

    public int cost() {
        return 5;
    }

    @Override
    public int getRange() {
        return 210;
    }

    @Override
    public int getHealth() {
        return 25;
    }

    @Override
    public int getDamage() {
        return 2;
    }

    public void ability() {
        for (Figure figure : getVsetkyPostavy()) {
            if ((figure.getHp() == figure.getMaxHP() / 2)) {
                if (figure instanceof Archer) {
                    figure.getHpBar().setShield(25);
                }
            }
        }
    }
}


