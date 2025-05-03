package Postavy;

import Pozadie.Cards;
import Pozadie.Elixir;
import fri.shapesge.Manazer;

public class Soldier extends Figure implements Cards {
    private Elixir elixir;
    private Manazer manazer;
    private boolean damageDoubled;

    public Soldier(int polohaX, int polohaY, int rychlost,Elixir elixir, Manazer manazer, int maxHP) {
        super(7, 3, "mec", polohaX, polohaY, rychlost, false, maxHP);
        this.elixir = elixir;
        this.manazer = manazer;
        this.damageDoubled = false;
    }

    public Soldier(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "NEPmec", polohaX, polohaY, rychlost, jeNepriatel, maxHP);
        this.damageDoubled = false;
    }

    @Override
    public void click() {
        if (this.elixir.getpocet() > this.cost()) {
            Soldier vojak = new Soldier(200, 900, 8,this.elixir, this.manazer, 50);
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
        if (damageDoubled) {
            return 2;
        }
        return 1;
    }

    public void ability() {
        for (Figure figure : getVsetkyPostavy()) {
            if (figure.getHp() == figure.getMaxHP() / 2) {
                if (figure instanceof Soldier) {
                    damageDoubled = true;
                }
            }
        }
    }
}