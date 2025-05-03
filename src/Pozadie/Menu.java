package Pozadie;

import Magic.Healing;
import Magic.Shield;
import Magic.Thunder;
import Postavy.Soldier;
import Postavy.Archer;
import Postavy.SpearMan;
import fri.shapesge.Manazer;
import fri.shapesge.Obdlznik;
import fri.shapesge.Obrazok;
import java.util.ArrayList;

public class Menu {

    private final int velkostx;
    private final int velkosty;
    private final int polohaX;
    private final int polohaY;
    private final ArrayList<Obdlznik> ikony;
    private final ArrayList<int[]> suradnice;
    private final Obrazok rimHlava;
    private final Obrazok lukostrelecHlava;
    private final Obrazok kopijnikHlava;
    private final Obrazok healing;
    private final Obrazok lightning;
    private final Obrazok shield;
    private final Manazer manazer;
    private final Elixir elixir;
    private final ArrayList<Cards> karty = new ArrayList<>();

    public Menu(Elixir elixir) {
        this.velkostx = 100;
        this.velkosty = 100;
        this.polohaX = 50;
        this.polohaY = 50;
        this.ikony = new ArrayList<>();
        this.suradnice = new ArrayList<>();
        this.rimHlava = new Obrazok("pics/IkonaHlavy/Riman.png");
        this.rimHlava.zmenPolohu(50, 50);
        this.lukostrelecHlava = new Obrazok("pics/IkonaHlavy/Lukostrelec.png");
        this.lukostrelecHlava.zmenPolohu(200, 50);
        this.kopijnikHlava = new Obrazok("pics/IkonaHlavy/Kopijnik.png");
        this.kopijnikHlava.zmenPolohu(350, 50);

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
        this.elixir = elixir;

        this.kartyPostav();
    }

    public void kartyPostav() {
        Soldier vojak = new Soldier(0, 0, 0, this.elixir, this.manazer, 50);
        Archer luk = new Archer(0, 0, 0, this.elixir, this.manazer, 25);
        SpearMan spearMan = new SpearMan(0, 0, 0, this.elixir, this.manazer, 100);
        Thunder thunder = new Thunder(this.elixir);
        Healing healing  = new Healing(this.elixir);
        Shield shield = new Shield(this.elixir);

        vojak.hide();
        luk.hide();
        spearMan.hide();

        this.karty.add(vojak);
        this.karty.add(luk);
        this.karty.add(spearMan);
        this.karty.add(thunder);
        this.karty.add(healing);
        this.karty.add(shield);

        int x = this.polohaX;
        for (int i = 0; i < 3; i++) {
            Obdlznik ikona = new Obdlznik();
            ikona.zmenFarbu("black");
            ikona.zmenStrany(this.velkostx, this.velkosty);
            ikona.zmenPolohu(x, this.polohaY);
            ikona.zobraz();
            this.ikony.add(ikona);
            this.suradnice.add(new int[]{x, this.polohaY});
            x += 150;
        }
        this.rimHlava.zobraz();
        this.lukostrelecHlava.zobraz();
        this.kopijnikHlava.zobraz();

        for (int i = 0; i < 3; i++) {
            Obdlznik kuzlo = new Obdlznik();
            kuzlo.zmenFarbu("black");
            kuzlo.zmenStrany(this.velkostx, this.velkosty);
            kuzlo.zmenPolohu(x + 900, this.polohaY);
            kuzlo.zobraz();
            this.ikony.add(kuzlo);
            this.suradnice.add(new int[]{x + 900, this.polohaY});
            x += 150;
        }

        this.lightning.zobraz();
        this.shield.zobraz();
        this.healing.zobraz();
    }

    public void pohybMysi(int x, int y) {
        for (int i = 0; i < this.ikony.size(); i++) {
            Obdlznik ikona = this.ikony.get(i);
            int ikonax = this.suradnice.get(i)[0];
            int ikonay = this.suradnice.get(i)[1];

            if (x >= ikonax && x <= (ikonax + this.velkostx) && y >= ikonay && y <= (ikonay + this.velkosty)) {
                ikona.zmenFarbu("white");
            } else {
                ikona.zmenFarbu("black");
            }
        }
    }

    public void stlacHlavu(int x, int y) {
        for (int i = 0; i < this.karty.size(); i++) {
            int ikonax = this.suradnice.get(i)[0];
            int ikonay = this.suradnice.get(i)[1];

            if (x >= ikonax && x <= (ikonax + this.velkostx) && y >= ikonay && y <= (ikonay + this.velkosty)) {
                this.karty.get(i).click();
            }
        }
    }
}