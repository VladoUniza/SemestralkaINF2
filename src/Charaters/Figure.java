package Charaters;

import fri.shapesge.BlokTextu;
import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import java.util.ArrayList;

public class Figure extends Character {
    protected Obrazok obrazok;
    private final int pictureCount;
    private final int staticPictureCount;
    protected int animation;

    private int x;
    private final int y;
    private final int dataX;
    private final int maxHP;

    private final int range;
    private final int health;
    private final int damage;

    private final String name;
    private final HpBar hpBar;
    private final int speed;
    private final boolean isEnemy;
    private boolean isDead;
    private boolean alreadyUsedAbility;

    private static Player player;
    private static Enemy nepriatelskyHrac;
    private static final ArrayList<Figure> vsetkyPostavy = new ArrayList<>();

    public Figure(int pictureCount, int attackPictureCount, String name, int x, int y, int speed, boolean isEnemy, int maxHP, int health, int damage, int range) {

        this.maxHP = maxHP;
        this.health = health;
        this.damage = damage;
        this.range = range;

        this.animation = 0;
        this.name = name;
        this.pictureCount = pictureCount;
        this.staticPictureCount = attackPictureCount;
        this.speed = speed;

        this.x = x;
        this.y = y;
        this.isEnemy = isEnemy;
        this.alreadyUsedAbility = false;
        this.obrazok = new Obrazok("pics/" + this.name + "/" + this.name + "/0.png");

        DataObrazku data = new DataObrazku("pics/" + this.name + "/" + this.name + "/0.png");
        this.dataX = data.getSirka();
        this.obrazok.zmenPolohu(this.x, this.y);
        this.obrazok.zobraz();

        this.isDead = false;
        this.hpBar = new HpBar(this.x, this.y, this);
        this.hpBar.show();
        if (this.isEnemy) {
            this.hpBar.changeColor("red");
        }
        vsetkyPostavy.add(this);
    }

    public static void initializeBuildings(Player h, Enemy n) {
        player = h;
        nepriatelskyHrac = n;
    }

    public boolean getIsEnemy() {
        return this.isEnemy;
    }

    public void tik() {
        if (this.isDead) {
            return;
        }

        if (this.isEnemy) {
            int vzdialenost = Math.abs(this.x - player.getX());
            if (vzdialenost <= this.getRange()) {
                this.attackBuilding(player);
                return;
            }
        } else {
            int vzdialenost = Math.abs(this.x - nepriatelskyHrac.getX());
            if (vzdialenost <= this.getRange()) {
                this.attackBuilding(nepriatelskyHrac);
                return;
            }
        }

        for (Figure figure : vsetkyPostavy) {
            if (figure == this || figure.isDead) {
                continue;
            }

            if (this.isEnemy != figure.isEnemy) {
                int vzdialenost = Math.abs(this.x - figure.x);
                if (vzdialenost <= this.getRange()) {
                    this.attack(figure);
                    return;
                }
            }
        }

        for (Figure figure : vsetkyPostavy) {
            if (figure == this || figure.isDead) {
                continue;
            }

            if (this.isEnemy == figure.isEnemy) {
                boolean jePredoMnou = (this.isEnemy && figure.x < this.x) || (!this.isEnemy && figure.x > this.x);
                int vzdialenost = Math.abs(this.x - figure.x);

                if (jePredoMnou && vzdialenost < this.dataX) {
                    this.stop();
                    return;
                }
            }
        }

        int posun = this.isEnemy ? -this.speed : this.speed;
        this.obrazok.posunVodorovne(posun);
        this.x += posun;
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.move();
    }

    public void move() {
        this.obrazok.zmenObrazok("pics/" + this.name + "/" + this.name + "/" + (this.animation % this.pictureCount) + ".png");
        this.animation++;
        this.obrazok.zobraz();
    }

    public void attack(Figure cielovaPostava) {
        this.obrazok.posunVodorovne(0);
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.obrazok.zmenObrazok("pics/" + this.name + "/attack" + this.name + "/" + (this.animation % this.staticPictureCount) + ".png");
        this.animation++;
        this.obrazok.zobraz();
        cielovaPostava.takeHP(this.getDamage());
    }

    public void attackBuilding(Character cielovaBudova) {
        this.obrazok.posunVodorovne(0);
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.obrazok.zmenObrazok("pics/" + this.name + "/attack" + this.name + "/" + (this.animation % this.staticPictureCount) + ".png");
        this.animation++;
        this.obrazok.zobraz();
        cielovaBudova.takeHP(this.getDamage());

        if (cielovaBudova.getHealth() <= 0) {
            if (cielovaBudova instanceof Enemy) {
                BlokTextu text = new BlokTextu("VICTORY", 500, 500);
                text.zmenPolohu(500, 500);
                text.zmenFarbu("black");
                text.zmenFont("Arial", StylFontu.BOLD, 200);
                text.zobraz();
                for (Figure figure : vsetkyPostavy) {
                    figure.stop();
                }
            }
            if (cielovaBudova instanceof Player) {
                BlokTextu text = new BlokTextu("DEFEAT", 500, 500);
                text.zmenPolohu(500, 500);
                text.zmenFont("Arial", StylFontu.BOLD, 200);
                text.zmenFarbu("black");
                text.zobraz();
                for (Figure figure : vsetkyPostavy) {
                    figure.stop();
                }
            }
        }
    }

    public void stop() {
        this.obrazok.zmenObrazok("pics/" + this.name + "/stop" + this.name + "/0.png");
        this.obrazok.zobraz();
    }

    public void takeHP(int mnozstvo) {
        this.hpBar.substractHp(mnozstvo);
        if (this.hpBar.getHp() <= 0) {
            this.obrazok.skry();
            this.hpBar.hide();
            this.isDead = true;
            vsetkyPostavy.remove(this);
        }

        if (this.hpBar.getHp() <= this.maxHP / 2 && !this.alreadyUsedAbility) {
            this.ability();
            this.alreadyUsedAbility = true;
        }
    }

    public HpBar getHpBar() {
        return this.hpBar;
    }

    public int getHp() {
        return this.hpBar.getHp();
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public void setHP(int hp) {
        this.hpBar.setHP(hp);
    }

    public void hide() {
        this.obrazok.skry();
        this.hpBar.hide();
    }

    public static ArrayList<Figure> getVsetkyPostavy() {
        return vsetkyPostavy;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void ability() {
        System.out.println("Current character does not have any abilities");
    }
}