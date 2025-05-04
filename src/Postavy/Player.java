package Postavy;

public class Player extends Character {

    private final HpBar hpBar;
    private int health = 500;

    public Player() {
        this.hpBar = new HpBar(10, 700, this);
        this.hpBar.show();
    }

    public void takeHP(int amount) {
        this.hpBar.substractHp(amount);
        this.health -= amount;
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getDamage() {
        return 0;
    }

    @Override
    public void ability() {
    }

    public int getX() {
        return 200;
    }
}