package Main;

import Characters.Player;
import Characters.Enemy;
import Characters.Figure;
import Level.Background;
import Characters.Gold;
import Menu.Menu;
import fri.shapesge.Manazer;

public class Starter {

    public Starter() {
    }

    public void spusti() {
        Manazer manazer = new Manazer();

        Gold gold = new Gold();
        Background bg = new Background();
        Menu menu = new Menu(gold);

        Enemy enemy = new Enemy();
        Player player = new Player();
        Figure.initializeBuildings(player, enemy);

        manazer.spravujObjekt(this);
        manazer.spravujObjekt(menu);
        manazer.spravujObjekt(gold);
        manazer.spravujObjekt(enemy);
        manazer.spravujObjekt(player);
        manazer.spravujObjekt(bg);
    }
}