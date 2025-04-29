package Magic;

import Postavy.Figure;
import Pozadie.Cards;
import Pozadie.Elixir;

public class Healing implements Cards {
    private final Elixir elixir;

    public Healing(Elixir elixir) {
        this.elixir = elixir;
    }

    @Override
    public void click() {
        if (this.elixir.getpocet() > this.cost()) {
            this.elixir.odpocitajElixir(this.cost() + 1);

            for (Figure figure : Figure.getVsetkyPostavy()) {
                if (!figure.getIsEnemy() && figure.getHp() < figure.getMaxHP()) {
                    figure.setHP(figure.getMaxHP());
                }
            }
        } else {
            this.elixir.odpocitajElixir(0);
        }
    }

    @Override
    public int cost() {
        return 1;
    }
}