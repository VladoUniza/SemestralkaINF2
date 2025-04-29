package Postavy.Enemies;

import Postavy.Character;
import Postavy.HpBar;
import Postavy.RomanSoldier;
import fri.shapesge.Manazer;

public class Enemy extends Character {

    private final Manazer manazer;
    private int x;
    private final int y;
    private final HpBar hpBar;
    private int zivoty = 500;

    public Enemy() {
        this.manazer = new Manazer();
        this.x = 1620;
        this.y = 900;
        this.hpBar = new HpBar(1400, 700, this);
        this.hpBar.zmenFarbu("red");
        this.hpBar.zobraz();
    }

    public void odpocet() {
        var romanSoldier = new RomanSoldier(this.x, this.y, 8, true, this.getHealth());
        this.manazer.spravujObjekt(romanSoldier);
        this.x -= 8;
    }

    public void skryHP() {
        this.hpBar.skry();
    }

    public void takeHP(int amount) {
        this.hpBar.uberHp(amount);
        this.zivoty -= amount;
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public int getHealth() {
        return this.zivoty;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    public int getX() {
        return 1670;
    }
}