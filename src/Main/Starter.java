package Main;

import Charaters.Player;
import Charaters.Enemy;
import Charaters.Figure;
import Level.Background;
import Charaters.Gold;
import Menu.Menu;
import fri.shapesge.Manazer;

public class Starter {

    public Starter() {
    }

    public void spusti() {
        Manazer manazer = new Manazer();

        Gold gold = new Gold();
        Background bg = new Background(gold);
        Menu menu = new Menu(gold);

        Enemy enemy = new Enemy();
        enemy.hideHP();

        Player budovaHrac = new Player();
        Enemy budovaNepriatel = new Enemy();
        Figure.initializeBuildings(budovaHrac, budovaNepriatel);

        manazer.spravujObjekt(menu);
        manazer.spravujObjekt(gold);
        manazer.spravujObjekt(this);
        manazer.spravujObjekt(enemy);
        manazer.spravujObjekt(budovaHrac);
        manazer.spravujObjekt(bg);
    }
}