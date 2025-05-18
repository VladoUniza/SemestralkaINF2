package characters;

public abstract class Character {
    private int health;
    private final int range;
    private final int damage;

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
        this.updateHpBar(amount);
    }

    protected void updateHpBar(int amount) {
    }

    public int getHealth() {
        return this.health;
    }

    public int getRange() {
        return this.range;
    }

    public int getDamage() {
        return this.damage;
    }

    public abstract void ability();

    public void onDeath() {
    }
}