package com.iuc.morpion;

import android.app.Activity;
import android.app.Dialog;
import android.view.ViewGroup;
import android.widget.Button;

public class TieDialog extends Dialog {
    private Button yesBtn, noBtn;

    public TieDialog(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Light_Dialog_Alert);
        setContentView(R.layout.tie);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.yesBtn = findViewById(R.id.yesBtn);
        this.noBtn = findViewById(R.id.noBtn);
    }

    public Button getYesBtn() {
        return this.yesBtn;
    }

    public Button getNoBtn() {
        return this.noBtn;
    }

    public void build() {
        show();
    }
}
