package com.iuc.morpion;


import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class PlayActivity extends AppCompatActivity {
    private Player player1;
    private Player player2;
    private Player[] players = new Player[2];
    private PlayActivity activity;
    private View turn;
    static int[] arr ;
    private Button[] plateau = new Button[9];

    private int p = 0;
    private int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        activity = this;

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.test);

        ImageView iv = new ImageView(this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(30, 40);
        params.leftMargin = 50;
        params.topMargin = 60;
        rl.addView(iv, params);



        turn = findViewById(R.id.turn);

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
        players = initPlayer();
        turn.setBackground(players[p].turn);
        n=0;
        arr = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        for (Button button : plateau) {
            button.setBackgroundColor(Color.parseColor("#1F3540"));
            button.setClickable(true);
        }
        turn(plateau);
    }

    public Player[] initPlayer() {
        int n = 1;
        if (n == 1) {
            player1 = new Player(getDrawable(R.drawable.x), getDrawable(R.drawable.x_black), getDrawable(R.drawable.x_grey),"#31C3BD");
            player2 = new Player(getDrawable(R.drawable.o), getDrawable(R.drawable.o_black), getDrawable(R.drawable.o_grey),"#F2B137");
        } else {
            player1 = new Player(getDrawable(R.drawable.o), getDrawable(R.drawable.o_black), getDrawable(R.drawable.o_grey),"#F2B137");
            player2 = new Player(getDrawable(R.drawable.x), getDrawable(R.drawable.x_black), getDrawable(R.drawable.x_grey),"#31C3BD");
            p = 1;
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
                return;
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
    public void result(boolean draw) {
        ResultDialog  result_dialog = new ResultDialog(activity);
        String title = !draw ? "Player 1 win!": "it's a tie";
        result_dialog.setTitle(title);
        result_dialog.setColor(players[p].color);
        result_dialog.setSymbol(players[p].symbol);

        result_dialog.getNoBtn().setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        result_dialog.getYesBtn().setOnClickListener(view -> {
            result_dialog.dismiss();
            init(plateau);
        });
        result_dialog.build();
    }
    public void turn(Button[] plateau) {
        for (Button button : plateau) {
            button.setOnClickListener(view -> {
                button.setBackground(players[p].symbol);
                button.setClickable(false);
                draw((Button) view);
                n++;
                if (n == 9 || test()) {
                    result(false);
                } else {
                    p = p == 0 ? 1 : 0;
                    turn.setBackground(players[p].turn);
                }
                if(n == 9 ) {
                    result(true);
                }
            });

        }
    }
}

