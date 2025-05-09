package Menu;

import Magic.Healing;
import Magic.Shield;
import Magic.Thunder;
import Characters.Cards;
import Characters.Gold;
import Characters.PlayableCharacters.Soldier;
import Characters.PlayableCharacters.Archer;
import Characters.PlayableCharacters.SpearMan;
import fri.shapesge.Manazer;
import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import java.util.ArrayList;

public class Menu {

    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private final ArrayList<Obdlznik> icons;
    private final ArrayList<int[]> coordinations;
    private final Obrazok soldierIcon;
    private final Obrazok archerIcon;
    private final Obrazok spearmanIcon;
    private final Obrazok healing;
    private final Obrazok lightning;
    private final Obrazok shield;
    private final Manazer manazer;
    private final Gold gold;
    private final ArrayList<Cards> karty = new ArrayList<>();

    public Menu(Gold gold) {
        this.width = 100;
        this.height = 100;
        this.x = 50;
        this.y = 50;
        this.icons = new ArrayList<>();
        this.coordinations = new ArrayList<>();
        this.soldierIcon = new Obrazok("pics/HeadIcon/Soldier.png");
        this.soldierIcon.zmenPolohu(50, 50);
        this.archerIcon = new Obrazok("pics/HeadIcon/Archer.png");
        this.archerIcon.zmenPolohu(200, 50);
        this.spearmanIcon = new Obrazok("pics/HeadIcon/Spearman.png");
        this.spearmanIcon.zmenPolohu(350, 50);

        this.lightning = new Obrazok("pics/Magic/Lightning.png");
        this.lightning.zmenPolohu(1400, 50);
        this.healing = new Obrazok("pics/Magic/Healing.png");
        this.healing.zmenPolohu(1550, 50);
        this.shield = new Obrazok("pics/Magic/Shield.png");
        this.shield.zmenPolohu(1700, 50);

        Obdlznik obdlznik = new Obdlznik();
        obdlznik.zmenStrany(2000, 200);
        obdlznik.zmenPolohu(0, 0);
        obdlznik.zobraz();
        obdlznik.zmenFarbu("black");

        this.manazer = new Manazer();
        this.gold = gold;

        this.cardsOfCharacters();
    }

    public void cardsOfCharacters() {
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
    }

    public void mouseMovement(int x, int y) {
        for (int i = 0; i < this.icons.size(); i++) {
            Obdlznik ikona = this.icons.get(i);
            int ikonax = this.coordinations.get(i)[0];
            int ikonay = this.coordinations.get(i)[1];

            if (x >= ikonax && x <= (ikonax + this.width) && y >= ikonay && y <= (ikonay + this.height)) {
                ikona.zmenFarbu("white");
            } else {
                ikona.zmenFarbu("black");
            }
        }
    }

    public void clickOnIcon(int x, int y) {
        for (int i = 0; i < this.karty.size(); i++) {
            int ikonax = this.coordinations.get(i)[0];
            int ikonay = this.coordinations.get(i)[1];

            if (x >= ikonax && x <= (ikonax + this.width) && y >= ikonay && y <= (ikonay + this.height)) {
                this.karty.get(i).click();
            }
        }
    }
}