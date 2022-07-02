package com.iuc.morpion;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private MainActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
    }
    public void two_player(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
    public void online(View view) {
        OnlineDialog coming_soon =  new OnlineDialog(activity);
        coming_soon.getNeutralBtn().setOnClickListener(v -> {
            coming_soon.dismiss();
        });
        coming_soon.build();
    }
}
