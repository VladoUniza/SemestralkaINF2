package Postavy;

import Pozadie.Cards;
import Pozadie.Elixir;
import fri.shapesge.Manazer;

public class SpearMan extends Figure implements Cards {
    private Elixir elixir;
    private Manazer manazer;

    public SpearMan(int polohaX, int polohaY, int rychlost, Elixir elixir, Manazer manazer, int maxHP) {
        super(7, 3, "spear", polohaX, polohaY, rychlost, false, maxHP, 100, 3, 70);
        this.elixir = elixir;
        this.manazer = manazer;
    }

    public SpearMan(int polohaX, int polohaY, int rychlost, boolean jeNepriatel, int maxHP) {
        super(7, 3, "Espear", polohaX, polohaY, rychlost, jeNepriatel, maxHP, 100, 3, 70);
    }

    @Override
    public void click() {
        if (this.elixir.getcount() > this.cost()) {
            SpearMan oto = new SpearMan(200, 900, 8, this.elixir, this.manazer, 100);
            this.elixir.substractElixir(oto.cost() + 1);
            this.manazer.spravujObjekt(oto);
        } else {
            this.elixir.substractElixir(0);
        }
    }

    @Override
    public int cost() {
        return 8;
    }

    @Override
    public void ability() {
        for (Figure figure : getVsetkyPostavy()) {
            if (!figure.getIsEnemy() && figure instanceof SpearMan && figure.getHp() <= figure.getMaxHP() / 2) {
                figure.setHP(figure.getMaxHP());
            }
        }
    }
}