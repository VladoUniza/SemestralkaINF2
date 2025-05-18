package menu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import magic.Healing;
import magic.Shield;
import magic.Thunder;
import level.Background;

import characters.Figure;
import characters.Cards;
import hud.Gold;
import characters.playableCharacters.Soldier;
import characters.playableCharacters.Archer;
import characters.playableCharacters.Spearman;

import fri.shapesge.Manazer;
import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import fri.shapesge.BlokTextu;
import fri.shapesge.StylFontu;

public class Menu {
    private final int width;
    private final int height;
    private final int x;
    private final int y;

    private final Manazer manazer;
    private final Gold gold;
    private final ArrayList<Cards> cards = new ArrayList<>();
    private final ArrayList<Obdlznik> icons;
    private final ArrayList<int[]> coordinations;

    private final Obrazok soldierIcon;
    private final Obrazok archerIcon;
    private final Obrazok spearmanIcon;

    private final Obrazok healing;
    private final Obrazok lightning;
    private final Obrazok shield;

    private final Obrazok pause;

    public Menu(Gold gold) {
        this.width = 100;
        this.height = 100;
        this.x = 50;
        this.y = 50;
        this.icons = new ArrayList<>();
        this.coordinations = new ArrayList<>();
        this.manazer = new Manazer();
        this.gold = gold;

        this.soldierIcon = new Obrazok("pics/HeadIcon/Soldier.png");
        this.soldierIcon.zmenPolohu(50, this.y);
        this.archerIcon = new Obrazok("pics/HeadIcon/Archer.png");
        this.archerIcon.zmenPolohu(200, this.y);
        this.spearmanIcon = new Obrazok("pics/HeadIcon/Spearman.png");
        this.spearmanIcon.zmenPolohu(350, this.y);

        this.lightning = new Obrazok("pics/Magic/Lightning.png");
        this.lightning.zmenPolohu(1400, this.y);
        this.healing = new Obrazok("pics/Magic/Healing.png");
        this.healing.zmenPolohu(1550, this.y);
        this.shield = new Obrazok("pics/Magic/Shield.png");
        this.shield.zmenPolohu(1700, this.y);

        this.pause = new Obrazok("pics/PauseButton.png");
        this.pause.zmenPolohu(900, this.y);

        Obdlznik obdlznik = new Obdlznik();
        obdlznik.zmenStrany(2000, 200);
        obdlznik.zmenPolohu(0, 0);
        obdlznik.zobraz();
        obdlznik.zmenFarbu("black");

        BlokTextu text1 = new BlokTextu("Gold: ", 500, 150);
        text1.zmenFarbu("white");
        text1.zmenFont("Arial", StylFontu.BOLD, 30);
        text1.zobraz();

        BlokTextu text2 = new BlokTextu("Gold spent: ", 500, 100);
        text2.zmenFarbu("white");
        text2.zmenFont("Arial", StylFontu.BOLD, 30);
        text2.zobraz();

        this.cardsOfCharacters();
    }

    public void cardsOfCharacters() {
        Obdlznik saveIcon = new Obdlznik();
        saveIcon.zmenStrany(this.width, this.height);
        saveIcon.zmenPolohu(900, this.y);
        saveIcon.zobraz();

        Soldier soldier = new Soldier(0, 0, 0, this.gold, this.manazer, 50);
        Archer archer = new Archer(0, 0, 0, this.gold, this.manazer, 25);
        Spearman spearMan = new Spearman(0, 0, 0, this.gold, this.manazer, 100);
        Thunder thunderSpell = new Thunder(this.gold);
        Healing healingSpell  = new Healing(this.gold);
        Shield shieldSpell = new Shield(this.gold);

        soldier.hide();
        archer.hide();
        spearMan.hide();

        this.cards.add(soldier);
        this.cards.add(archer);
        this.cards.add(spearMan);
        this.cards.add(thunderSpell);
        this.cards.add(healingSpell);
        this.cards.add(shieldSpell);

        int positionOfIcons = this.x;

        for (int i = 0; i < 3; i++) {
            Obdlznik ikona = new Obdlznik();
            ikona.zmenFarbu("black");
            ikona.zmenStrany(this.width, this.height);
            ikona.zmenPolohu(positionOfIcons, this.y);
            ikona.zobraz();
            this.icons.add(ikona);
            this.coordinations.add(new int[]{positionOfIcons, this.y});
            positionOfIcons += 150;
        }

        this.soldierIcon.zobraz();
        this.archerIcon.zobraz();
        this.spearmanIcon.zobraz();

        for (int i = 0; i < 3; i++) {
            Obdlznik spell = new Obdlznik();
            spell.zmenFarbu("black");
            spell.zmenStrany(this.width, this.height);
            spell.zmenPolohu(positionOfIcons + 900, this.y);
            spell.zobraz();
            this.icons.add(spell);
            this.coordinations.add(new int[]{positionOfIcons + 900, this.y});
            positionOfIcons += 150;
        }

        this.lightning.zobraz();
        this.shield.zobraz();
        this.healing.zobraz();

        this.pause.zobraz();

        this.icons.add(saveIcon);
        this.coordinations.add(new int[]{900, this.y});
    }

    public void saveGame() throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("gameData.txt"));
        writer.println("Meno: " + StartingMenu.getName());
        writer.println("GoldSpent: " + Gold.getGoldSpent());
        writer.println("Kills: " + Figure.getNumberOfDeadEnemies());
        writer.println("Era: " + Background.getCurrentEra());
        writer.close();
    }

    public void mouseMovement(int x, int y) {
        for (int i = 0; i < this.icons.size(); i++) {
            Obdlznik ikona = this.icons.get(i);
            int iconX = this.coordinations.get(i)[0];
            int iconY = this.coordinations.get(i)[1];

            if (i == this.icons.size() - 1) {
                ikona.zmenFarbu("black");
                continue;
            }

            if (x >= iconX && x <= (iconX + this.width) && y >= iconY && y <= (iconY + this.height)) {
                ikona.zmenFarbu("white");
            } else {
                ikona.zmenFarbu("black");
            }
        }
    }

    public void clickOnIcon(int x, int y) throws IOException {
        for (int i = 0; i < this.cards.size(); i++) {
            int iconX = this.coordinations.get(i)[0];
            int iconY = this.coordinations.get(i)[1];

            if (x >= iconX && x <= (iconX + this.width) && y >= iconY && y <= (iconY + this.height)) {
                this.cards.get(i).click();
                return;
            }
        }

        int saveIndex = this.coordinations.size() - 1;
        int iconX = this.coordinations.get(saveIndex)[0];
        int iconY = this.coordinations.get(saveIndex)[1];

        if (x >= iconX && x <= (iconX + this.width) && y >= iconY && y <= (iconY + this.height)) {
            this.saveGame();
            System.exit(0);
        }
    }
}