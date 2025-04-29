package Magic;


import Postavy.Figure;
import Pozadie.Cards;
import Pozadie.Elixir;

import java.util.ArrayList;

public class Thunder implements Cards {
    private final Elixir elixir;

    public Thunder(Elixir elixir) {
        this.elixir = elixir;
    }

    @Override
    public void click() {
        if (this.elixir.getpocet() > this.cost()) {
            this.elixir.odpocitajElixir(this.cost() + 1);
            this.takeHalfHP();
        } else {
            this.elixir.odpocitajElixir(0);
        }
    }

    public void takeHalfHP() {
        ArrayList<Figure> naZabitie = new ArrayList<>();

        for (Figure figure : Figure.getVsetkyPostavy()) {
            int halfHP = (figure.getMaxHP() / 2) + 1;
            if (figure.getHp() <= halfHP) {
                naZabitie.add(figure);
            } else {
                figure.takeHP(halfHP);
            }
        }
        for (Figure figure : naZabitie) {
            figure.takeHP(figure.getHp());
        }
    }

    @Override
    public int cost() {
        return 1;
    }
}