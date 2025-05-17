package Main;

import Characters.*;
import Hud.Gold;
import Level.Background;
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

        Enemy enemy = new Enemy(Battlefield.getAllFiguresInBattle());
        Player player = new Player();
        Figure.initializeBuildings(player, enemy);

        manazer.spravujObjekt(menu);
        manazer.spravujObjekt(gold);
        manazer.spravujObjekt(enemy);
        manazer.spravujObjekt(player);
        manazer.spravujObjekt(bg);
    }

    public void nacitajProgres(int goldSpent, int kills, int era) {
        Manazer manazer = new Manazer();

        Gold gold = new Gold();
        gold.setGoldSpent(goldSpent);
        Background bg = new Background();
        bg.loadBG(era);

        Menu menu = new Menu(gold);

        Enemy enemy = new Enemy(Battlefield.getAllFiguresInBattle());
        Player player = new Player();
        Figure.initializeBuildings(player, enemy);
        Figure.incrementNumberOfDeadEnemies(kills);

        manazer.spravujObjekt(menu);
        manazer.spravujObjekt(gold);
        manazer.spravujObjekt(enemy);
        manazer.spravujObjekt(player);
        manazer.spravujObjekt(bg);
    }
}