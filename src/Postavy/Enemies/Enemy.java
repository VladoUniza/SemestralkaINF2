package Postavy.Enemies;
import Postavy.Archer;
import Postavy.Character;
import Postavy.HpBar;
import Postavy.Soldier;
import fri.shapesge.Manazer;
import java.util.Random;

public class Enemy extends Character {

    private final Manazer manazer;
    private int x;
    private final int y;
    private final HpBar hpBar;
    private int zivoty = 500;
    private Random rand;

    public Enemy() {
        this.manazer = new Manazer();
        this.x = 1620;
        this.y = 900;
        this.hpBar = new HpBar(1400, 700, this);
        this.hpBar.zmenFarbu("red");
        this.hpBar.zobraz();
        this.rand = new Random();
    }

    public void odpocet() {
        int nahoda = rand.nextInt(10);
        if (nahoda <= 5) {
            var romanSoldier = new Soldier(this.x, this.y, 8, true, this.getHealth());
            this.manazer.spravujObjekt(romanSoldier);
            this.x -= 8;
        } else if(nahoda <= 8) {
            var archer = new Archer(this.x, this.y, 8, true, this.getHealth());
            this.manazer.spravujObjekt(archer);
            this.x -= 8;
        } else {
            var archer = new Archer(this.x, this.y, 8, true, this.getHealth());
            this.manazer.spravujObjekt(archer);
            this.x -= 8;
        }
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

    @Override
    public void ability() {

    }

    public int getX() {
        return 1670;
    }
}