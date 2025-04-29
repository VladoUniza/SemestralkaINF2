package Pozadie;

import fri.shapesge.*;
import fri.shapesge.StylFontu;

public class Elixir {
    private int pocet = 0;
    private BlokTextu textPoctu;

    public Elixir() {
        this.textPoctu = new BlokTextu(String.valueOf(this.pocet), 50, 300);
        this.textPoctu.zmenFarbu("white");
        this.textPoctu.zmenFont("Arial", StylFontu.BOLD, 50);
        this.textPoctu.zobraz();
    }

    public void tikElixiru() {
        if (this.pocet > 10) {
            this.pocet = 10;
        } else {
            this.textPoctu.skry();
            this.textPoctu = new BlokTextu(String.valueOf(this.pocet), 50, 300);
            this.textPoctu.zmenFont("Arial", StylFontu.BOLD, 50);
            this.textPoctu.zmenFarbu("white");
            this.pocet++;
            this.textPoctu.zobraz();
        }
    }

    public void odpocitajElixir(int odpocet) {
        this.pocet -= odpocet;
    }

    public int getpocet() {
        return this.pocet;
    }
}