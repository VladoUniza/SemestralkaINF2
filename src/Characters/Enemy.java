package Characters;
import Characters.PlayableCharacters.Archer;
import Characters.PlayableCharacters.Soldier;
import Characters.PlayableCharacters.SpearMan;
import fri.shapesge.Manazer;
import java.util.Random;

public class Enemy extends Character {

    private final Manazer manazer;
    private int x;
    private final int y;
    private final HpBar hpBar;
    private final Random rand;

    public Enemy() {
        super(500, 0,0);
        this.manazer = new Manazer();
        this.x = 1620;
        this.y = 900;
        this.hpBar = new HpBar(1400, 700, this);
        this.hpBar.changeColor("red");
        this.hpBar.show();
        this.rand = new Random();
    }

    public void countdown() {
        int nahoda = rand.nextInt(10);
        if (nahoda <= 5) {
            var romanSoldier = new Soldier(this.x, this.y, 10, true, this.Health());
            this.manazer.spravujObjekt(romanSoldier);
            this.x -= 8;
        } else if(nahoda <= 8) {
            var archer = new Archer(this.x, this.y, 12, true, this.Health());
            this.manazer.spravujObjekt(archer);
            this.x -= 8;
        } else {
            var spearman = new SpearMan(this.x, this.y, 8, true, this.Health());
            this.manazer.spravujObjekt(spearman);
            this.x -= 8;
        }
    }

    @Override
    protected void updateHpBar(int amount) {
        this.hpBar.substractHp(amount);
    }

    public int getX() {
        return 1670;
    }
}