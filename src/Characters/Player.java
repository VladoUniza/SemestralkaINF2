package Characters;

public class Player extends Character {

    private final HpBar hpBar;

    public Player() {
        super(500,0,0);

        this.hpBar = new HpBar(10, 700, this);
        this.hpBar.show();
    }

    @Override
    protected void updateHpBar(int amount) {
        this.hpBar.substractHp(amount);
    }

    public int getX() {
        return 200;
    }
}