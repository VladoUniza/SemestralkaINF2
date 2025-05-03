package Postavy;

import Pozadie.Cards;
import Pozadie.Elixir;
import fri.shapesge.Manazer;

public class SpearMan extends Figure implements Cards {
    private Elixir elixir;
    private Manazer manazer;
    private boolean vylieceny;

    public SpearMan(int polohaX, int polohaY, int rychlost, Elixir elixir, Manazer manazer, int maxHP) {
        super(7, 3, "mec", polohaX, polohaY, rychlost, false, maxHP);
        this.elixir = elixir;
        this.manazer = manazer;
        this.vylieceny = false;
    }

    public SpearMan(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "NEPmec", polohaX, polohaY, rychlost, jeNepriatel, maxHP);
        this.vylieceny = false;
    }

    @Override
    public void click() {
        if (this.elixir.getpocet() > this.cost()) {
            SpearMan oto = new SpearMan(200, 900, 8, this.elixir, this.manazer, 100);
            this.elixir.odpocitajElixir(oto.cost() + 1);
            this.manazer.spravujObjekt(oto);
        } else {
            this.elixir.odpocitajElixir(0);
        }
    }

    @Override
    public int cost() {
        return 8;
    }

    @Override
    public int getRange() {
        return 70;
    }

    @Override
    public int getHealth() {
        return 100;
    }

    @Override
    public int getDamage() {
        return 3;
    }

    @Override
    public void ability() {
        for (Figure figure : getVsetkyPostavy()) {
            if (figure.getHp() <= figure.getMaxHP() && vylieceny == false) {
                if (figure instanceof SpearMan) {
                    figure.setHP(figure.getMaxHP());
                    vylieceny = true;
                }
            }
        }
    }
}