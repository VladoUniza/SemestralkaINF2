package Characters;

import Menu.Statistics;
import fri.shapesge.*;
import java.util.ArrayList;

public abstract class Figure extends Character {
    protected Obrazok picture;
    private final int pictureCount;
    private final int attackPictureCount;
    protected int animation;
    private final int dataX;

    private int x;
    private final int y;
    private final int speed;

    private final int maxHP;
    private final String name;
    private final HpBar hpBar;
    private final boolean isEnemy;
    private boolean isDead;
    private boolean alreadyUsedAbility;

    private static Player player;
    private static Enemy enemyPlayer;
    private static int numberOfDeadEnemies;
    private static final ArrayList<Figure> allFiguresInBattle = new ArrayList<>();

    private boolean endScreenBool = false;

    public Figure(int pictureCount, int attackPictureCount, String name, int x, int y, int speed, boolean isEnemy, int maxHP, int health, int damage, int range) {
        super(health, range, damage);

        this.x = x;
        this.y = y;
        this.speed = speed;

        this.name = name;
        this.maxHP = maxHP;
        this.hpBar = new HpBar(this.x, this.y, this);
        this.hpBar.show();
        this.alreadyUsedAbility = false;
        this.isDead = false;
        this.isEnemy = isEnemy;
        if (this.isEnemy) {
            this.hpBar.changeColor("red");
        }

        this.picture = new Obrazok("pics/" + this.name + "/" + this.name + "/0.png");
        this.picture.zmenPolohu(this.x, this.y);
        this.picture.zobraz();
        this.animation = 0;
        this.pictureCount = pictureCount;
        this.attackPictureCount = attackPictureCount;

        DataObrazku data = new DataObrazku("pics/" + this.name + "/" + this.name + "/0.png");
        this.dataX = data.getSirka();

        allFiguresInBattle.add(this);
    }

    public void tik() {
        if (this.isDead) {
            return;
        }

        if (this.isEnemy) {
            int vzdialenost = Math.abs(this.x - player.getX());

            if (vzdialenost <= this.getRange()) {
                this.attackCharacterOrBuilding(player);
                return;
            }
        } else {
            int vzdialenost = Math.abs(this.x - enemyPlayer.getX());

            if (vzdialenost <= this.getRange()) {
                this.attackCharacterOrBuilding(enemyPlayer);
                return;
            }
        }

        for (Figure figure : allFiguresInBattle) {
            if (figure == this || figure.isDead) {
                continue;
            }

            if (this.isEnemy != figure.isEnemy) {
                int vzdialenost = Math.abs(this.x - figure.x);
                if (vzdialenost <= this.getRange()) {
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
        this.picture.posunVodorovne(posun);
        this.x += posun;
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.move();
    }

    public void move() {
        this.picture.zmenObrazok("pics/" + this.name + "/" + this.name + "/" + (this.animation % this.pictureCount) + ".png");
        this.animation++;
        this.picture.zobraz();
    }

    public void attackCharacterOrBuilding(Character target) {
        this.picture.posunVodorovne(0);
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.picture.zmenObrazok("pics/" + this.name + "/attack" + this.name + "/" + (this.animation % this.attackPictureCount) + ".png");
        this.animation++;
        this.picture.zobraz();

        target.takeHP(this.getDamage());

        if (target.getHealth() <= 0) {
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
        if (!endScreenBool) {
            new Statistics(numberOfDeadEnemies);
            endScreenBool = true;
        }
    }

    private void showDefeatMessage() {
        BlokTextu text = new BlokTextu("DEFEAT", 500, 500);
        text.zmenPolohu(500, 500);
        text.zmenFont("Arial", StylFontu.BOLD, 200);
        text.zmenFarbu("black");
        text.zobraz();

        stopAllFigures();
        if (!endScreenBool) {
            new Statistics(numberOfDeadEnemies);
            endScreenBool = true;
        }
    }

    private void stopAllFigures() {
        for (Figure figure : allFiguresInBattle) {
            figure.stop();
        }
    }

    public void stop() {
        this.picture.zmenObrazok("pics/" + this.name + "/stop" + this.name + "/0.png");
        this.picture.zobraz();
    }

    public void takeHP(int mnozstvo) {
        if (this.hpBar == null) {
            return;
        }
        this.hpBar.substractHp(mnozstvo);
        if (this.hpBar.getHp() <= 0) {
            this.picture.skry();
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

    public abstract void ability();

    public static void initializeBuildings(Player p, Enemy n) {
        player = p;
        enemyPlayer = n;
    }

    public static ArrayList<Figure> getAllFiguresInBattle() {
        return allFiguresInBattle;
    }

    public boolean getIsNotEnemy() {
        return !this.isEnemy;
    }

    public HpBar getHpBar() {
        return this.hpBar;
    }

    public int getHpFromHpBar() {
        return this.hpBar.getHp();
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public void setHPOfHpBar(int hp) {
        this.hpBar.setHP(hp);
    }

    public void hide() {
        this.picture.skry();
        this.hpBar.hide();
    }
}