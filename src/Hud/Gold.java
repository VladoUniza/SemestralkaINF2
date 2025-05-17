package Hud;

import fri.shapesge.*;

public class Gold {
    private int count = 0;
    private BlokTextu numberOfGold;
    private BlokTextu numberOfGoldSpent;
    private static int goldSpent;

    public Gold() {
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
    }

    public void tikGoldSpent() {
        this.numberOfGoldSpent.skry();
        this.numberOfGoldSpent = new BlokTextu(String.valueOf(goldSpent),675 , 100);
        this.numberOfGoldSpent.zmenFont("Arial", StylFontu.BOLD, 40);
        this.numberOfGoldSpent.zmenFarbu("white");
        this.numberOfGoldSpent.zobraz();
    }

    public void substractGold(int odpocet) {
        this.count -= odpocet;
    }

    public int getcount() {
        return this.count;
    }

    public void setGoldSpent(int number) {
        goldSpent += number;
    }

    public static int getGoldSpent() {
        return goldSpent;
    }
}
