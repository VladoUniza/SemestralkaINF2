package Characters;

public abstract class Character {
    protected int health;
    protected int range;
    protected int damage;

    public Character(int health, int range, int damage) {
        this.health = health;
        this.range = range;
        this.damage = damage;
    }

    public void takeHP(int amount) {
        this.health -= amount;
        if (this.health < 0) {
            this.health = 0;
        }

        updateHpBar(amount);
    }

    protected void updateHpBar(int amount) {
    }

    public int getHealth() {
        return health;
    }

    public int getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }
}