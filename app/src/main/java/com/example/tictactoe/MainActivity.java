package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    // 0 -> X
    // 1 -> O
    // -1 -> blank

    int activePlayer  = 0;
    int[] gameState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7},{2,5,8},
                            {0,4,8}, {2,4,6}};
    public void playerTap(View view){
        ImageView img =(ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == -1 && gameActive){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText("0's turn. Tap to play");
                activePlayer = 1;
            }
            else{
                img.setImageResource(R.drawable.o);
                TextView status = findViewById(R.id.status);
                status.setText("X's turn. Tap to play");
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        // check for winning conditions
        for(int[] winPosition: winPositions ){
            if(gameState[winPosition[0]] == gameState[winPosition[1]]
                 && gameState[winPosition[1]] == gameState[winPosition[2]]
                 && gameState[winPosition[0]] != -1){

                // check who has won
                String winner;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winner = "X has won!";
                }else{
                    winner = "0 has won!";
                }

                // Update the status for winner
                TextView status = findViewById(R.id.status);
                status.setText(winner);
            }else{
                TextView status = findViewById(R.id.status);
                status.setText("Game Tied! Tap reset to play again");
            }
        }

    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i< gameState.length; i++){
            gameState[i] = -1;
        }
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's turn. Tap to play");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}