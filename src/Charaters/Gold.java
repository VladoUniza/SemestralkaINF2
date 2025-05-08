package Charaters;

import fri.shapesge.*;
import fri.shapesge.StylFontu;

public class Gold {
    private int count = 0;
    private BlokTextu numberOfGold;
    private BlokTextu numberOfGoldSpent;
    private final BlokTextu text1;
    private final BlokTextu text2;
    private static int goldSpent;

    public Gold() {
        text1 = new BlokTextu("Gold: ", 500, 150);
        text1.zmenFarbu("white");
        text1.zmenFont("Arial", StylFontu.BOLD, 30);

        text2 = new BlokTextu("Gold spent: ", 500, 100);
        text2.zmenFarbu("white");
        text2.zmenFont("Arial", StylFontu.BOLD, 30);

        this.numberOfGold = new BlokTextu(String.valueOf(this.count), 580, 150);
        this.numberOfGold.zmenFarbu("white");
        this.numberOfGold.zmenFont("Arial", StylFontu.BOLD, 40);
        this.numberOfGold.zobraz();

        this.numberOfGoldSpent = new BlokTextu(String.valueOf(this.count), 675, 100);
        this.numberOfGoldSpent.zmenFarbu("white");
        this.numberOfGoldSpent.zmenFont("Arial", StylFontu.BOLD, 40);
        this.numberOfGoldSpent.zobraz();
    }

    public void tikOfGold() {
        if (this.count > 10) {
            this.count = 10;
        } else {
            this.numberOfGold.skry();
            this.numberOfGold = new BlokTextu(String.valueOf(this.count), 580, 150);
            this.numberOfGold.zmenFont("Arial", StylFontu.BOLD, 40);
            this.numberOfGold.zmenFarbu("white");
            this.count++;
            this.numberOfGold.zobraz();
        }
        text1.zobraz();
    }

    public void tikGoldSpent() {
        this.numberOfGoldSpent.skry();
        this.numberOfGoldSpent = new BlokTextu(String.valueOf(this.goldSpent),675 , 100);
        this.numberOfGoldSpent.zmenFont("Arial", StylFontu.BOLD, 40);
        this.numberOfGoldSpent.zmenFarbu("white");
        this.numberOfGoldSpent.zobraz();
        text2.zobraz();
    }

    public void substractGold(int odpocet) {
        this.count -= odpocet;
    }

    public int getcount() {
        return this.count;
    }

    public void goldSpent(int number) {
        goldSpent += number;
    }

    public static int getGoldSpent() {
        return goldSpent;
    }
}