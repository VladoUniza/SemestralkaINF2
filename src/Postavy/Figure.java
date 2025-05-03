package Postavy;

import Postavy.Enemies.Enemy;
import fri.shapesge.BlokTextu;
import fri.shapesge.DataObrazku;
import fri.shapesge.Obrazok;
import fri.shapesge.StylFontu;
import java.util.ArrayList;

public class Figure extends Character {
    protected Obrazok obrazok;
    private final int pocetObrazkov;
    private final int pocetStacObrazkov;
    protected int animacia;

    private int polohaX;
    private final int polohaY;
    private final int dataX;
    private final int maxHP;

    private final String nazov;
    private final HpBar hpBar;
    private final int rychlost;
    private final boolean isEnemy;
    private boolean isDead;
    private boolean isAbilityActivated;

    private static Player player;
    private static Enemy nepriatelskyHrac;
    private static final ArrayList<Figure> vsetkyPostavy = new ArrayList<>();

    public Figure(int pocetObrazkov, int pocetStacObrazkov, String nazov, int polohaX, int polohaY, int rychlost, boolean isEnemy, int maxHP) {
        this.maxHP = maxHP;
        this.animacia = 0;
        this.nazov = nazov;
        this.pocetObrazkov = pocetObrazkov;
        this.pocetStacObrazkov = pocetStacObrazkov;
        this.rychlost = rychlost;
        this.polohaX = polohaX;
        this.polohaY = polohaY;
        this.isEnemy = isEnemy;
        this.isAbilityActivated = false;
        this.obrazok = new Obrazok("pics/" + this.nazov + "/" + this.nazov + "/0.png");

        DataObrazku data = new DataObrazku("pics/" + this.nazov + "/" + this.nazov + "/0.png");
        this.dataX = data.getSirka();
        this.obrazok.zmenPolohu(this.polohaX, this.polohaY);
        this.obrazok.zobraz();

        this.isDead = false;
        this.hpBar = new HpBar(this.polohaX, this.polohaY, this);
        this.hpBar.zobraz();
        if (this.isEnemy) {
            this.hpBar.zmenFarbu("red");
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
            int vzdialenost = Math.abs(this.polohaX - player.getX());
            if (vzdialenost <= this.getRange()) {
                this.attackBuilding(player);
                return;
            }
        } else {
            int vzdialenost = Math.abs(this.polohaX - nepriatelskyHrac.getX());
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
                int vzdialenost = Math.abs(this.polohaX - figure.polohaX);
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
                boolean jePredoMnou = (this.isEnemy && figure.polohaX < this.polohaX) || (!this.isEnemy && figure.polohaX > this.polohaX);
                int vzdialenost = Math.abs(this.polohaX - figure.polohaX);

                if (jePredoMnou && vzdialenost < this.dataX) {
                    this.stop();
                    return;
                }
            }
        }

        int posun = this.isEnemy ? -this.rychlost : this.rychlost;
        this.obrazok.posunVodorovne(posun);
        this.polohaX += posun;
        this.hpBar.posunNa(this.polohaX, this.polohaY);
        this.hpBar.zobraz();
        this.move();
    }

    public void move() {
        this.obrazok.zmenObrazok("pics/" + this.nazov + "/" + this.nazov + "/" + (this.animacia % this.pocetObrazkov) + ".png");
        this.animacia++;
        this.obrazok.zobraz();
    }

    public void attack(Figure cielovaPostava) {
        this.obrazok.posunVodorovne(0);
        this.hpBar.posunNa(this.polohaX, this.polohaY);
        this.hpBar.zobraz();
        this.obrazok.zmenObrazok("pics/" + this.nazov + "/utoc" + this.nazov + "/" + (this.animacia % this.pocetStacObrazkov) + ".png");
        this.animacia++;
        this.obrazok.zobraz();
        cielovaPostava.takeHP(this.getDamage());
    }

    public void attackBuilding(Character cielovaBudova) {
        this.obrazok.posunVodorovne(0);
        this.hpBar.posunNa(this.polohaX, this.polohaY);
        this.hpBar.zobraz();
        this.obrazok.zmenObrazok("pics/" + this.nazov + "/utoc" + this.nazov + "/" + (this.animacia % this.pocetStacObrazkov) + ".png");
        this.animacia++;
        this.obrazok.zobraz();
        cielovaBudova.takeHP(this.getDamage());

        if (cielovaBudova.getHealth() <= 0) {
            if (cielovaBudova instanceof Enemy) {
                BlokTextu text = new BlokTextu("VICTORY", 500, 500);
                text.zmenPolohu(500, 500);
                text.zmenFarbu("black");
                text.zmenFont("Arial", StylFontu.BOLD, 200);
                text.zobraz();
            }
            if (cielovaBudova instanceof Player) {
                BlokTextu text = new BlokTextu("DEFEAT", 500, 500);
                text.zmenPolohu(500, 500);
                text.zmenFont("Arial", StylFontu.BOLD, 200);
                text.zmenFarbu("black");
                text.zobraz();
            }
        }
    }

    public void stop() {
        this.obrazok.zmenObrazok("pics/" + this.nazov + "/zastav" + this.nazov + "/0.png");
        this.obrazok.zobraz();
    }

    public void takeHP(int mnozstvo) {
        this.hpBar.uberHp(mnozstvo);
        if (this.hpBar.getHp() <= 0) {
            this.obrazok.skry();
            this.hpBar.skry();
            this.isDead = true;
            vsetkyPostavy.remove(this);
        }

        if (this.hpBar.getHp() <= this.maxHP / 2 && !this.isAbilityActivated) {
            this.ability();
            this.isAbilityActivated = true;
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
        this.hpBar.nastavHp(hp);
    }

    public void hide() {
        this.obrazok.skry();
        this.hpBar.skry();
    }

    public static ArrayList<Figure> getVsetkyPostavy() {
        return vsetkyPostavy;
    }

    @Override
    public int getRange() {
        throw new UnsupportedOperationException("Current character is not defined");
    }

    @Override
    public int getHealth() {
        throw new UnsupportedOperationException("Current character is not defined");
    }

    @Override
    public int getDamage() {
        throw new UnsupportedOperationException("Current character is not defined");
    }

    @Override
    public void ability() {
        throw new UnsupportedOperationException("Current character is not defined");
    }
}