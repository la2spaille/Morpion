package com.iuc.morpion;

import android.graphics.drawable.Drawable;

public class Player {
    public String name;
    public Drawable symbol;
    public Drawable outline;
    public Drawable turn;
    public Player (String name, Drawable symbol,Drawable outline, Drawable turn) {
        this.name = name;
        this.symbol = symbol;
        this.outline = outline;
        this.turn = turn;
    }

}
