package Menu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Magic.Healing;
import Magic.Shield;
import Magic.Thunder;
import Level.Background;

import Characters.Figure;
import Characters.Cards;
import Hud.Gold;
import Characters.PlayableCharacters.Soldier;
import Characters.PlayableCharacters.Archer;
import Characters.PlayableCharacters.SpearMan;

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
    private final ArrayList<Cards> karty = new ArrayList<>();
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

        Soldier vojak = new Soldier(0, 0, 0, this.gold, this.manazer, 50);
        Archer luk = new Archer(0, 0, 0, this.gold, this.manazer, 25);
        SpearMan spearMan = new SpearMan(0, 0, 0, this.gold, this.manazer, 100);
        Thunder thunder = new Thunder(this.gold);
        Healing healing  = new Healing(this.gold);
        Shield shield = new Shield(this.gold);

        vojak.hide();
        luk.hide();
        spearMan.hide();

        this.karty.add(vojak);
        this.karty.add(luk);
        this.karty.add(spearMan);
        this.karty.add(thunder);
        this.karty.add(healing);
        this.karty.add(shield);

        int x = this.x;

        for (int i = 0; i < 3; i++) {
            Obdlznik ikona = new Obdlznik();
            ikona.zmenFarbu("black");
            ikona.zmenStrany(this.width, this.height);
            ikona.zmenPolohu(x, this.y);
            ikona.zobraz();
            this.icons.add(ikona);
            this.coordinations.add(new int[]{x, this.y});
            x += 150;
        }

        this.soldierIcon.zobraz();
        this.archerIcon.zobraz();
        this.spearmanIcon.zobraz();

        for (int i = 0; i < 3; i++) {
            Obdlznik spell = new Obdlznik();
            spell.zmenFarbu("black");
            spell.zmenStrany(this.width, this.height);
            spell.zmenPolohu(x + 900, this.y);
            spell.zobraz();
            this.icons.add(spell);
            this.coordinations.add(new int[]{x + 900, this.y});
            x += 150;
        }

        this.lightning.zobraz();
        this.shield.zobraz();
        this.healing.zobraz();

        this.pause.zobraz();

        this.icons.add(saveIcon);
        this.coordinations.add(new int[]{900, this.y});
    }

    public void ulozHru() throws IOException {
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
            int ikonax = this.coordinations.get(i)[0];
            int ikonay = this.coordinations.get(i)[1];

            if (i == this.icons.size() - 1) {
                ikona.zmenFarbu("black");
                continue;
            }

            if (x >= ikonax && x <= (ikonax + this.width) && y >= ikonay && y <= (ikonay + this.height)) {
                ikona.zmenFarbu("white");
            } else {
                ikona.zmenFarbu("black");
            }
        }
    }

    public void clickOnIcon(int x, int y) throws IOException {
        for (int i = 0; i < this.karty.size(); i++) {
            int ikonax = this.coordinations.get(i)[0];
            int ikonay = this.coordinations.get(i)[1];

            if (x >= ikonax && x <= (ikonax + this.width) && y >= ikonay && y <= (ikonay + this.height)) {
                this.karty.get(i).click();
                return;
            }
        }

        int saveIndex = this.coordinations.size() - 1;
        int ikonax = this.coordinations.get(saveIndex)[0];
        int ikonay = this.coordinations.get(saveIndex)[1];

        if (x >= ikonax && x <= (ikonax + this.width) && y >= ikonay && y <= (ikonay + this.height)) {
            this.ulozHru();
            System.exit(0);
        }
    }
}