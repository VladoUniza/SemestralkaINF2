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
        if (this.elixir.getcount() > this.cost()) {
            this.elixir.substractElixir(this.cost() + 1);

            for (Figure figure : Figure.getVsetkyPostavy()) {
                if (figure.getIsEnemy()) {
                    figure.getHpBar().setShield(figure.getMaxHP()/2);
                }
            }
        } else {
            this.elixir.substractElixir(0);
        }
    }

    @Override
    public int cost() {
        return 3;
    }
}