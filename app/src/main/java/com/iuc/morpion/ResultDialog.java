package com.iuc.morpion;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ResultDialog extends Dialog {
    private String title, color,description;
    private Drawable symbol;
    private TextView descriptionView, titleView;
    private View symbolView;
    private Button yesBtn, noBtn;

    public ResultDialog(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Light_Dialog_Alert);
        setContentView(R.layout.result);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.yesBtn = findViewById(R.id.yesBtn);
        this.noBtn = findViewById(R.id.noBtn);
        this.title = "It's a win!";
        this.description = "TAKE THE ROUND";
        this.descriptionView = findViewById(R.id.description);
        this.titleView = findViewById(R.id.title);
        this.symbolView = findViewById(R.id.symbol);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSymbol(Drawable symbol) {
        this.symbol = symbol;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Button getYesBtn() {
        return this.yesBtn;
    }

    public Button getNoBtn() {
        return this.noBtn;
    }

    public void build() {
        show();
        titleView.setText(title);
        descriptionView.setTextColor(Color.parseColor(color));
        descriptionView.setText(description);
        symbolView.setBackground(symbol);
    }
}
