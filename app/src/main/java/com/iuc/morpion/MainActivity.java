package com.iuc.morpion;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static Random rand = new Random();
    private Player player1;
    private Player player2;
    private Player[] players = new Player[2];

    private View turn;
    static int[] arr = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    private int p = 0;
    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turn = findViewById(R.id.turn);

        Button[] plateau = new Button[9];
        plateau[0] = findViewById(R.id.button0);
        plateau[1] = findViewById(R.id.button1);
        plateau[2] = findViewById(R.id.button2);
        plateau[3] = findViewById(R.id.button3);
        plateau[4] = findViewById(R.id.button4);
        plateau[5] = findViewById(R.id.button5);
        plateau[6] = findViewById(R.id.button6);
        plateau[7] = findViewById(R.id.button7);
        plateau[8] = findViewById(R.id.button8);

        init(plateau);
    }

    public void init(Button[] plateau) {
        players = initPlayer("Wilfried", "Trash");
        turn.setBackground(players[p].turn);
        turn(plateau);

    }

    public Player[] initPlayer(String pseudo1, String pseudo2) {
        int n = rand.nextInt(2);
        if (n == 1) {
            player1 = new Player(pseudo2, getDrawable(R.drawable.o), getDrawable(R.drawable.o_outline), getDrawable(R.drawable.o_grey));
            player2 = new Player(pseudo1, getDrawable(R.drawable.x), getDrawable(R.drawable.x_outline), getDrawable(R.drawable.x_grey));
        } else {
            player1 = new Player(pseudo1, getDrawable(R.drawable.o), getDrawable(R.drawable.o_outline), getDrawable(R.drawable.o_grey));
            player2 = new Player(pseudo2, getDrawable(R.drawable.x), getDrawable(R.drawable.x_outline), getDrawable(R.drawable.x_grey));
        }
        players[0] = player1;
        players[1] = player2;
        return players;
    }

    public void draw(Button btn) {
        switch (btn.getId()) {
            case R.id.button0:
                arr[0] = p;
                break;
            case R.id.button1:
                arr[1] = p;
                break;
            case R.id.button2:
                arr[2] = p;
                break;
            case R.id.button3:
                arr[3] = p;
                break;
            case R.id.button4:
                arr[4] = p;
                break;
            case R.id.button5:
                arr[5] = p;
                break;
            case R.id.button6:
                arr[6] = p;
                break;
            case R.id.button7:
                arr[7] = p;
                break;
            case R.id.button8:
                arr[8] = p;
                break;
            default:
                System.out.println("default");

        }
    }

    public boolean h() {
        if (arr[0] == p && arr[3] == p && arr[6] == p) {
            return true;
        }
        if (arr[1] == p && arr[4] == p && arr[7] == p) {
            return true;
        }
        return arr[2] == p && arr[5] == p && arr[8] == p;
    }

    public boolean v() {
        if (arr[0] == p && arr[1] == p && arr[2] == p) {
            return true;
        }
        if (arr[3] == p && arr[4] == p && arr[5] == p) {
            return true;
        }
        return arr[6] == p && arr[7] == p && arr[8] == p;
    }

    public boolean d() {
        if (arr[0] == p && arr[4] == p && arr[8] == p) {
            return true;
        }
        return arr[2] == p && arr[4] == p && arr[6] == p;
    }

    public boolean test() {
        if (d()) {
            return true;
        }
        if (v()) {
            return true;
        }
        return h();
    }

    public void turn(Button[] plateau) {
        for (Button button : plateau) {
            button.setOnClickListener(view -> {
                button.setBackground(players[p].symbol);
                button.setClickable(false);
                draw((Button) view);
                n++;
                if (n == 9 || test()) {
                    return;
                } else {
                    p = p == 0 ? 1 : 0;
                    turn.setBackground(players[p].turn);
                }
            });

        }
    }
}
