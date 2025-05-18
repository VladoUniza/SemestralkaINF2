package hud;

import characters.Character;
import fri.shapesge.Obdlznik;

public class HpBar {
    private int hp;
    private int shield;
    private final Obdlznik internalShield;
    private final Obdlznik internalHp;
    private final Obdlznik externalHp;

    public HpBar(int x, int y, Character character) {
        this.hp = character.getHealth();
        this.shield = 0;
        this.externalHp = new Obdlznik();
        this.internalHp = new Obdlznik();
        this.internalShield = new Obdlznik();

        this.internalHp.zmenPolohu(x, y);
        this.internalHp.zmenStrany(this.hp, 8);
        this.internalHp.zmenFarbu("green");

        this.internalShield.zmenPolohu(x, y);
        this.internalShield.zmenStrany(0, 8);
        this.internalShield.zmenFarbu("blue");

        this.externalHp.zmenPolohu(x + 2, y + 2);
        this.externalHp.zmenStrany(this.hp + 4, 12);
        this.externalHp.zmenFarbu("black");
    }

    public void takeHpOrShield(int amount) {
        if (this.shield > 0) {
            if (amount <= this.shield) {
                this.shield -= amount;
            } else {
                int remaining = amount - this.shield;
                this.shield = 0;
                this.hp -= remaining;
            }
        } else {
            this.hp -= amount;
        }
        this.updateHealth();
    }

    public void setHP(int amount) {
        this.hp = amount;
        this.updateHealth();
    }

    public void setShield(int amount) {
        this.shield = amount;
        this.updateHealth();
    }

    public int getShield() {
        return this.shield;
    }

    private void updateHealth() {
        this.internalHp.zmenStrany(Math.max(this.hp, 0), 8);
        this.internalShield.zmenStrany(this.shield, 8);
    }

    public void moveTo(int x, int y) {
        this.externalHp.zmenPolohu(x, y);
        this.internalHp.zmenPolohu(x, y);
        this.internalShield.zmenPolohu(x, y);
    }

    public int getHp() {
        return this.hp;
    }

    public void changeColor(String color) {
        this.internalHp.zmenFarbu(color);
    }

    public void show() {
        this.externalHp.zobraz();
        this.internalHp.zobraz();
        this.internalShield.zobraz();
    }

    public void hide() {
        this.externalHp.skry();
        this.internalHp.skry();
        this.internalShield.skry();
    }
}