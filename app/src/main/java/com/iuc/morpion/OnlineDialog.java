package com.iuc.morpion;

import android.app.Activity;
import android.app.Dialog;
import android.view.ViewGroup;
import android.widget.Button;

public class OnlineDialog extends Dialog {
    private Button neutralBtn;
    public  OnlineDialog(Activity activity) {
        super(activity, R.style.Theme_AppCompat_Light_Dialog_Alert);
        setContentView(R.layout.online);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        this.neutralBtn = findViewById(R.id.neutralBtn);
    }
    public Button getNeutralBtn() {
        return this.neutralBtn;
    }
    public void build() {
        show();
    }
}
