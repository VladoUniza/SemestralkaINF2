package Main;

import Magic.Thunder;
import Postavy.Player;
import Postavy.Enemies.Enemy;
import Postavy.Figure;
import Pozadie.Background;
import Pozadie.Elixir;
import Pozadie.Menu;
import fri.shapesge.Manazer;

public class Starter {

    public Starter() {
        Manazer manazer = new Manazer();
        Background background = new Background();
        Elixir elixir = new Elixir();

        Menu menu = new Menu(elixir);

        Enemy enemy = new Enemy();
        enemy.skryHP();
        Player budovaHrac = new Player();
        Enemy budovaNepriatel = new Enemy();
        Figure.initializeBuildings(budovaHrac, budovaNepriatel);

        Thunder thumder = new Thunder(elixir);

        manazer.spravujObjekt(menu);
        manazer.spravujObjekt(elixir);
        manazer.spravujObjekt(this);
        manazer.spravujObjekt(enemy);
        manazer.spravujObjekt(budovaHrac);
    }
}