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
        if (this.elixir.getcount() > this.cost()) {
            this.elixir.substractElixir(this.cost() + 1);

            for (Figure figure : Figure.getVsetkyPostavy()) {
                if (figure.getIsEnemy() && figure.getHp() < figure.getMaxHP()) {
                    figure.setHP(figure.getMaxHP());
                }
            }
        } else {
            this.elixir.substractElixir(0);
        }
    }

    @Override
    public int cost() {
        return 5;
    }
}