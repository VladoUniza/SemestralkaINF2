package Characters;

import Menu.Statistics;
import fri.shapesge.*;
import java.util.ArrayList;

public class Figure extends Character {
    protected Obrazok obrazok;
    private final int pictureCount;
    private final int staticPictureCount;
    protected int animation;
    private boolean endingBool = false;
    private static int numberOfDeadEnemies;

    private int x;
    private final int y;
    private final int dataX;
    private final int maxHP;

    private final String name;
    private final HpBar hpBar;
    private final int speed;
    private final boolean isEnemy;
    private boolean isDead;
    private boolean alreadyUsedAbility;

    private static Player player;
    private static Enemy nepriatelskyHrac;
    private static final ArrayList<Figure> allFiguresInBattle = new ArrayList<>();

    public Figure(int pictureCount, int attackPictureCount, String name, int x, int y, int speed, boolean isEnemy, int maxHP, int health, int damage, int range) {
        super(health, range, damage);
        this.maxHP = maxHP;

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
        allFiguresInBattle.add(this);
    }

    public static void initializeBuildings(Player p, Enemy n) {
        player = p;
        nepriatelskyHrac = n;
    }

    public void tik() {
        if (this.isDead) {
            return;
        }

        if (this.isEnemy) {
            int vzdialenost = Math.abs(this.x - player.getX());

            if (vzdialenost <= this.Range()) {
                this.attackCharacterOrBuilding(player);
                return;
            }
        } else {
            int vzdialenost = Math.abs(this.x - nepriatelskyHrac.getX());

            if (vzdialenost <= this.Range()) {
                this.attackCharacterOrBuilding(nepriatelskyHrac);
                return;
            }
        }

        for (Figure figure : allFiguresInBattle) {
            if (figure == this || figure.isDead) {
                continue;
            }

            if (this.isEnemy != figure.isEnemy) {
                int vzdialenost = Math.abs(this.x - figure.x);
                if (vzdialenost <= this.Range()) {
                    this.attackCharacterOrBuilding(figure);
                    return;
                }
            }
        }

        for (Figure figure : allFiguresInBattle) {
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

    public void attackCharacterOrBuilding(Character target) {
        this.obrazok.posunVodorovne(0);
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.obrazok.zmenObrazok("pics/" + this.name + "/attack" + this.name + "/" + (this.animation % this.staticPictureCount) + ".png");
        this.animation++;
        this.obrazok.zobraz();

        target.takeHP(this.Damage());

        if (target.Health() <= 0) {
            if (target instanceof Enemy) {
                showVictoryMessage();
            } else if (target instanceof Player) {
                showDefeatMessage();
            }
        }
    }

    private void showVictoryMessage() {
        BlokTextu text = new BlokTextu("VICTORY", 500, 500);
        text.zmenPolohu(500, 500);
        text.zmenFarbu("black");
        text.zmenFont("Arial", StylFontu.BOLD, 200);
        text.zobraz();

        stopAllFigures();
        if (!endingBool) {
            new Statistics(numberOfDeadEnemies);
            endingBool = true;
        }
    }

    private void showDefeatMessage() {
        BlokTextu text = new BlokTextu("DEFEAT", 500, 500);
        text.zmenPolohu(500, 500);
        text.zmenFont("Arial", StylFontu.BOLD, 200);
        text.zmenFarbu("black");
        text.zobraz();

        stopAllFigures();
        if (!endingBool) {
            new Statistics(numberOfDeadEnemies);
            endingBool = true;
        }
    }

    private void stopAllFigures() {
        for (Figure figure : allFiguresInBattle) {
            figure.stop();
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
            allFiguresInBattle.remove(this);

            if (!this.isEnemy) {
                return;
            }
            numberOfDeadEnemies++;
        }

        if (this.hpBar.getHp() <= this.maxHP / 2 && !this.alreadyUsedAbility) {
            this.ability();
            this.alreadyUsedAbility = true;
        }
    }

    public static ArrayList<Figure> getAllFiguresInBattle() {
        return allFiguresInBattle;
    }

    public void ability() {
        for (Figure figure : getAllFiguresInBattle()) {
            if (figure.getIsNotEnemy() && figure.getHp() <= figure.getMaxHP() / 2) {
                figure.setHP(figure.getMaxHP());
            }
        }
    }

    public boolean getIsNotEnemy() {
        return !this.isEnemy;
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
}