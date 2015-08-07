package com.example.dpalaniswami.tennisscoringsystem;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends Activity {

    Button player1WonPoint;
    Button player2WonPoint;
    Button resetGame;

    TextView matchScore;
    int player1Score;
    int player2Score;

    private static final String TAG = "SCORECARD";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1WonPoint = (Button)findViewById(R.id.player1Btn);
        player2WonPoint = (Button)findViewById(R.id.player2Btn);
        matchScore = (TextView)findViewById(R.id.matchScore);
        resetGame = (Button)findViewById(R.id.resetBtn);

        player1WonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Player 1 got one poing
                player1Score();

            }
        });

        player2WonPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Player 2 got one point
                player2Score();
            }
        });

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set the Players score and clear the UI
                player1Score = 0;
                player2Score = 0;
                matchScore.setVisibility(View.INVISIBLE);
            }
        });
    }

    public String scoreAdapter(int point){

        switch(point){
            case 1: return "15";
            case 2: return "30";
            case 3: return "40";
            case 0:
            default: return "0";
        }
    }

    public boolean winner(int point1, int point2){
        if (point1 >= 4 && (point1 >= (point2+2)) ){
            return true;
        }else{
            return false;
        }
    }

    public boolean isPlayer1Won(){
        if (winner(player1Score, player2Score)){
            Log.d(TAG, "Player 1 Won the Game");
            return true;
        }else{
            return false;
        }
    }

    public boolean isPlayer2Won(){
        if (winner(player2Score, player1Score)){
            Log.d(TAG, "Player 2 Won the Game");
            return true;
        }else{
            return false;
        }
    }

    public boolean isGameWon(){
       return isPlayer1Won() || isPlayer2Won();
    }

    public void displayWinner(){
        if (isPlayer1Won()){
            matchScore.setText("Congrats Player 1 Won :) ");
        }else if (isPlayer2Won()){
            matchScore.setText("Congrats Player 2 Won  :) ");
        }
    }


    public void displayMatchStatus(){
        matchScore.setText(scoreAdapter(player1Score) + " - " + scoreAdapter(player2Score));
    }

    public void displayMatchResult(){

        matchScore.setVisibility(View.VISIBLE);

        if (isGameWon()){
            displayWinner();
        }else if (player1Score < 3 || player2Score < 3){
            displayMatchStatus();
        }else{ //Deuce condition
            if (player1Score == player2Score){
                matchScore.setText(" Deuce ");
            }else if (player1Score > player2Score){
                matchScore.setText(" Advantage Player 1 ");
            }else{
                matchScore.setText(" Advantage Player 2 ");
            }
        }
   }

    public void player1Score(){
        player1Score = player1Score + 1;
        displayMatchResult();
    }

    public void player2Score(){
        player2Score = player2Score + 1;
        displayMatchResult();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
