package com.iuc.morpion;

import android.graphics.drawable.Drawable;

public class Player {
    public Drawable symbol;
    public Drawable dark;
    public Drawable turn;
    public int win;
    public String color;
    public Player ( Drawable symbol,Drawable dark, Drawable turn,String color) {
        this.symbol = symbol;
        this.dark =  dark;
        this.turn = turn;
        this.color = color;
        this.win = 0;
    }

}
