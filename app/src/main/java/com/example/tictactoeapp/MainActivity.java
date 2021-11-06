package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
int activePlayer = 0; //0 is yellow, 1 is red
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameisActive;
    Button playAgainButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameisActive = true;
         playAgainButton = findViewById(R.id.button);
    }
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int counterTag = Integer.parseInt(counter.getTag().toString());
        if (gameState[counterTag] == 2 && gameisActive) {
            gameState[counterTag] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                activePlayer = 1;
                counter.setImageResource(R.drawable.yellow);
            } else {
                activePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }


            // Log.i("Tag",counter.getTag().toString());
            counter.animate().translationYBy(1500).rotationBy(3600).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner;
                    gameisActive = false;
                    if (activePlayer == 0) {
                        winner = "Red";
                    } else {
                        winner = "Yellow";
                    }
                    playAgainButton.setVisibility(View.VISIBLE);
                    Toast.makeText(this, winner + " has Won", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    public void playAgain(View view){
        GridLayout myGridView = findViewById(R.id.gridLayout);
         playAgainButton.setVisibility(View.INVISIBLE);
        for(int i=0; i<myGridView.getChildCount(); i++) {
            ImageView counters = (ImageView)myGridView.getChildAt(i);
            counters.setImageDrawable(null);
        }
        gameisActive = true;
        Arrays.fill(gameState, 2);
    }
}