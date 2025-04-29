package Magic;

import Postavy.Figure;
import Pozadie.Cards;
import Pozadie.Elixir;

public class Shield implements Cards {
    private final Elixir elixir;

    public Shield(Elixir elixir) {
        this.elixir = elixir;
    }

    @Override
    public void click() {
        if (this.elixir.getpocet() > this.cost()) {
            this.elixir.odpocitajElixir(this.cost() + 1);

            for (Figure figure : Figure.getVsetkyPostavy()) {
                if (!figure.getIsEnemy()) {
                    figure.getHpBar().setShield(figure.getMaxHP()/2);
                }
            }
        } else {
            this.elixir.odpocitajElixir(0);
        }
    }

    @Override
    public int cost() {
        return 3;
    }
}