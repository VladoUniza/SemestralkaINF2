package Postavy;

import Pozadie.Cards;
import Pozadie.Elixir;
import fri.shapesge.Manazer;

public class RomanSoldier extends Figure implements Cards {
    private Elixir elixir;
    private Manazer manazer;

    public RomanSoldier(int polohaX, int polohaY, int rychlost, Elixir elixir, Manazer manazer, int maxHP) {
        super(7, 3, "mec", polohaX, polohaY, rychlost, false, maxHP);
        this.elixir = elixir;
        this.manazer = manazer;
    }

    public RomanSoldier(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "NEPmec", polohaX, polohaY, rychlost, jeNepriatel, maxHP);
    }

    @Override
    public void click() {
        if (this.elixir.getpocet() > this.cost()) {
            RomanSoldier vojak = new RomanSoldier(200, 900, 8, this.elixir, this.manazer, 50);
            this.elixir.odpocitajElixir(vojak.cost() + 1);
            this.manazer.spravujObjekt(vojak);
        } else {
            this.elixir.odpocitajElixir(0);
        }
    }

    @Override
    public int cost() {
        return 4;
    }

    @Override
    public int getRange() {
        return 70;
    }

    @Override
    public int getHealth() {
        return 50;
    }

    @Override
    public int getDamage() {
        return 1;
    }
}