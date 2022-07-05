package com.iuc.morpion;


import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/* —————— findViewByID is used to retrieve elements of the application —————— */
public class PlayActivity extends AppCompatActivity {

    /* —— int —— */
    private int P = 0, p; // Player's index/
    private int n = 0; // Number of items filled in the grid
    private int ties = 0; // Tie number

    /* —— array —— */
    private Player[] players = new Player[2]; // Array of both two players
    private TextView[] score_xo = new TextView[2]; // Array of both two players score
    private Button[] grid = new Button[9]; // Array of Tic Tac Toe's grid
    private int[] vGrid; // The virtual Tic Tac Toe's grid

    /* —— object —— */
    private CountDownTimer T;
    private CountDownTimer D;
    private PlayActivity activity;
    private TextView score_t;
    private View turnIndicator; // The turn indicator
    private Player player1, player2; // The both two player


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        /* —————————————————————————————————— */
        setTimeout();
        /* —————————————————————————————————— */
        activity = this;
        /* —————————————————————————————————— */
        turnIndicator = findViewById(R.id.turn);
        /* —————————————————————————————————— */
        score_xo[0] = findViewById(R.id.x_score);
        score_xo[1] = findViewById(R.id.o_score);
        /* —————————————————————————————————— */
        score_t = findViewById(R.id.ties);
        /* —————————————————————————————————— */
        grid[0] = findViewById(R.id.button0);
        grid[1] = findViewById(R.id.button1);
        grid[2] = findViewById(R.id.button2);
        grid[3] = findViewById(R.id.button3);
        grid[4] = findViewById(R.id.button4);
        grid[5] = findViewById(R.id.button5);
        grid[6] = findViewById(R.id.button6);
        grid[7] = findViewById(R.id.button7);
        grid[8] = findViewById(R.id.button8);
        /* —————————————————————————————————— */
        init(grid);
    }

    private void setTimeout() {
        T = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {
                if (n < 9) {
                    updTimer(l);
                }
            }
            @Override
            public void onFinish() {
                ia();
            }
        };
    }

    /* Method to initiate the party order */
    private void init(Button[] grid) {
        if (players[0] == null) {
            players = initPlayer();
        }
        p = P;
        turnIndicator.setBackground(players[p].turn);
        n = 0;
        vGrid = new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2};
        for (Button btn : grid) {
            btn.setBackgroundColor(Color.parseColor("#1F3540"));
            btn.setClickable(true);
        }
        turn(grid);
    }

    private void gameOver() {
        for (Button btn : grid) {
            btn.setClickable(false);
        }
        T.cancel();
        updTimer(3000);
        P = P == 0 ? 1 : 0;

    }

    /* Method to initiate player order */
    private Player[] initPlayer() {
        int n = 1;
        if (n == 1) {
            player1 = new Player(getDrawable(R.drawable.x), getDrawable(R.drawable.x_black), getDrawable(R.drawable.x_grey), "#31C3BD");
            player2 = new Player(getDrawable(R.drawable.o), getDrawable(R.drawable.o_black), getDrawable(R.drawable.o_grey), "#F2B137");
        } else {
            player1 = new Player(getDrawable(R.drawable.o), getDrawable(R.drawable.o_black), getDrawable(R.drawable.o_grey), "#F2B137");
            player2 = new Player(getDrawable(R.drawable.x), getDrawable(R.drawable.x_black), getDrawable(R.drawable.x_grey), "#31C3BD");
            P = 1;
        }
        players[0] = player1;
        players[1] = player2;
        return players;
    }

    /* Method to update the virtual grid */
    private void upd_vGrid(Button btn) {
        switch (btn.getId()) {
            case R.id.button0:
                vGrid[0] = p;
                break;
            case R.id.button1:
                vGrid[1] = p;
                break;
            case R.id.button2:
                vGrid[2] = p;
                break;
            case R.id.button3:
                vGrid[3] = p;
                break;
            case R.id.button4:
                vGrid[4] = p;
                break;
            case R.id.button5:
                vGrid[5] = p;
                break;
            case R.id.button6:
                vGrid[6] = p;
                break;
            case R.id.button7:
                vGrid[7] = p;
                break;
            case R.id.button8:
                vGrid[8] = p;
                break;
            default:
                return;
        }
    }

    /* Method to announce the virtual grid */
    private void winResult() {
        ResultDialog result_dialog = new ResultDialog(activity);
        String title = "Player " + (p + 1) + " win! \uD83C\uDF89";
        result_dialog.setTitle(title);
        result_dialog.setColor(players[p].color);
        result_dialog.setSymbol(players[p].symbol);
        score_xo[p].setText(String.valueOf(players[p].win));

        result_dialog.getNoBtn().setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        result_dialog.getYesBtn().setOnClickListener(view -> {
            result_dialog.dismiss();
            init(grid);
        });

        D = new CountDownTimer(500, 100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                result_dialog.build();
            }
        };
        D.start();
        gameOver();
    }

    private void tieResult() {
        TieDialog tie_dialog = new TieDialog(activity);

        tie_dialog.getNoBtn().setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        tie_dialog.getYesBtn().setOnClickListener(view -> {
            tie_dialog.dismiss();
            init(grid);
        });
        D = new CountDownTimer(500, 100) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                tie_dialog.build();
            }
        };
        D.start();

        gameOver();

    }

    /* Method to run the timeout */
    private void runTimeout() {
        T.start();
    }

    private void updTimer(long l) {
        int s = (int) (l / 1000) % 60;
        TextView timerView = findViewById(R.id.timer);
        String t = String.valueOf(s);
        timerView.setText(t);
    }

    private void ia() {
        int a;
        a = (int) (Math.random() * 8);
        while (vGrid[a] != 2) {
            a = (int) (Math.random() * 8);
        }
        draw(grid[a]);
        upd_vGrid(grid[a]);
        updateTurn();
    }

    private void updateTurn() {
        T.cancel();
        runTimeout();
        n++;
        if (test()) {
            players[p].win++;
            winResult();
            return;
        } else {
            p = p == 0 ? 1 : 0;
            turnIndicator.setBackground(players[p].turn);
        }
        if (n == 9) {
            ties++;
            tieResult();
        }
    }


    /* Method to update the grid */
    private void draw(Button btn) {
        btn.setBackground(players[p].symbol);
        btn.setClickable(false);
    }

    /* Method to manage the game */
    private void turn(Button[] grid) {
        for (Button button : grid) {
            button.setOnClickListener(view -> {
                draw(button);
                upd_vGrid(button);
                updateTurn();
            });
        }
    }


    /* Methods to verify if we are a winner */
    private boolean h() {
        if (vGrid[0] == p && vGrid[3] == p && vGrid[6] == p) {
            return true;
        }
        if (vGrid[1] == p && vGrid[4] == p && vGrid[7] == p) {
            return true;
        }
        return vGrid[2] == p && vGrid[5] == p && vGrid[8] == p;
    }

    private boolean v() {
        if (vGrid[0] == p && vGrid[1] == p && vGrid[2] == p) {
            return true;
        }
        if (vGrid[3] == p && vGrid[4] == p && vGrid[5] == p) {
            return true;
        }
        return vGrid[6] == p && vGrid[7] == p && vGrid[8] == p;
    }

    private boolean d() {
        if (vGrid[0] == p && vGrid[4] == p && vGrid[8] == p) {
            return true;
        }
        return vGrid[2] == p && vGrid[4] == p && vGrid[6] == p;
    }

    private boolean test() {
        if (d()) {
            return true;
        }
        if (v()) {
            return true;
        }
        return h();
    }
}

