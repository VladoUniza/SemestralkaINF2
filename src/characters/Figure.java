package characters;

import hud.HpBar;
import menu.Statistics;
import fri.shapesge.Obrazok;
import fri.shapesge.DataObrazku;

public abstract class Figure extends Character {

    private final Obrazok picture;
    private final int pictureCount;
    private final int attackPictureCount;
    private int animation;
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

    private boolean endScreenBool = true;

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

        Battlefield.addFigure(this);
    }

    public abstract void ability();

    public void tik() {
        if (this.isDead) {
            return;
        }

        if (this.isEnemy) {
            int maxRange = Math.abs(this.x - player.getX());
            if (maxRange <= this.getRange()) {
                this.attackCharacterOrPlayer(player);
                return;
            }
        } else {
            int maxRange = Math.abs(this.x - enemyPlayer.getX());
            if (maxRange <= this.getRange()) {
                this.attackCharacterOrPlayer(enemyPlayer);
                return;
            }
        }

        for (Figure figure : Battlefield.getAllFiguresInBattle()) {
            if (figure == this || figure.isDead) {
                continue;
            }

            if (this.isEnemy != figure.isEnemy) {
                int maxRange = Math.abs(this.x - figure.x);
                if (maxRange <= this.getRange()) {
                    this.attackCharacterOrPlayer(figure);
                    return;
                }
            }
        }

        for (Figure figure : Battlefield.getAllFiguresInBattle()) {
            if (figure == this || figure.isDead) {
                continue;
            }

            if (this.isEnemy == figure.isEnemy) {
                boolean isInFront = (this.isEnemy && figure.x < this.x) || (!this.isEnemy && figure.x > this.x);
                int maxRange = Math.abs(this.x - figure.x);

                if (isInFront && maxRange < this.dataX) {
                    this.stopAnimation();
                    return;
                }
            }
        }

        int move;
        if (this.isEnemy) {
            move = -this.speed;
        } else {
            move = this.speed;
        }

        this.picture.posunVodorovne(move);
        this.x += move;
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.move();
    }

    public void move() {
        this.picture.zmenObrazok("pics/" + this.name + "/" + this.name + "/" + (this.animation % this.pictureCount) + ".png");
        this.animation++;
        this.picture.zobraz();
    }

    public void attackCharacterOrPlayer(Character target) {
        this.picture.posunVodorovne(0);
        this.hpBar.moveTo(this.x, this.y);
        this.hpBar.show();
        this.picture.zmenObrazok("pics/" + this.name + "/attack" + this.name + "/" + (this.animation % this.attackPictureCount) + ".png");
        this.animation++;
        this.picture.zobraz();

        target.takeHP(this.getDamage());
        if (target.getHealth() <= 0) {
            target.onDeath();

            if (this.endScreenBool) {
                this.endScreenBool = false;
                new Statistics(numberOfDeadEnemies);
            }
        }
    }

    public void stopAnimation() {
        this.picture.zmenObrazok("pics/" + this.name + "/stop" + this.name + "/0.png");
        this.picture.zobraz();
    }

    @Override
    public void takeHP(int amount) {
        if (this.hpBar == null) {
            return;
        }
        this.hpBar.takeHpOrShield(amount);
        if (this.hpBar.getHp() <= 0) {
            this.picture.skry();
            this.hpBar.hide();
            this.isDead = true;
            Battlefield.removeFigure(this);

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

    public static void initializeBuildings(Player p, Enemy n) {
        player = p;
        enemyPlayer = n;
    }

    public static void incrementNumberOfDeadEnemies(int number) {
        numberOfDeadEnemies += number;
    }

    public static int getNumberOfDeadEnemies() {
        return numberOfDeadEnemies;
    }

    public boolean getIsAlly() {
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