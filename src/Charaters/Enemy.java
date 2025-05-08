package Charaters;
import Charaters.PlayableCharacters.Archer;
import Charaters.PlayableCharacters.Soldier;
import Charaters.PlayableCharacters.SpearMan;
import fri.shapesge.Manazer;
import java.util.Random;

public class Enemy extends Character {

    private final Manazer manazer;
    private int x;
    private final int y;
    private final HpBar hpBar;
    private int zivoty = 50;
    private final Random rand;

    public Enemy() {
        this.manazer = new Manazer();
        this.x = 1620;
        this.y = 900;
        this.hpBar = new HpBar(1400, 700, this);
        this.hpBar.changeColor("red");
        this.hpBar.show();
        this.rand = new Random();
    }

    public void countdown() {
        int nahoda = rand.nextInt(10);
        if (nahoda <= 5) {
            var romanSoldier = new Soldier(this.x, this.y, 10, true, this.getHealth());
            this.manazer.spravujObjekt(romanSoldier);
            this.x -= 8;
        } else if(nahoda <= 8) {
            var archer = new Archer(this.x, this.y, 12, true, this.getHealth());
            this.manazer.spravujObjekt(archer);
            this.x -= 8;
        } else {
            var spearman = new SpearMan(this.x, this.y, 8, true, this.getHealth());
            this.manazer.spravujObjekt(spearman);
            this.x -= 8;
        }
    }

    public void hideHP() {
        this.hpBar.hide();
    }

    public void takeHP(int amount) {
        this.hpBar.substractHp(amount);
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