package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    int count = 1; // to know whether the image to be displayed is x or o
    int[][] flag = {{0,0,0}, {0,0,0}, {0,0,0}};//to check whether a button is clicked
    boolean gameEnd = false;//to know if  the game has ended
    boolean p1 = false, p2 = false ;//to check which player won
    int x_or_o;//decides whether to display x or o for player1
    int p1_count = 0, p2_count = 0;
    String p1_name, p2_name;//player1 and player2 names
    String P1_TEXT, P2_TEXT;//string variables to hold the text to be displayed in the textview for player1 and player2
    TextView player1Name, player2Name ;
    int o , x ;

    ImageButton imageButton00;
    ImageButton imageButton01;
    ImageButton imageButton02;
    ImageButton imageButton10;
    ImageButton imageButton11;
    ImageButton imageButton12;
    ImageButton imageButton20;
    ImageButton imageButton21;
    ImageButton imageButton22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            p1_count = savedInstanceState.getInt("p1_count");
            p2_count = savedInstanceState.getInt("p2_count");
        }
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle data = new Bundle();
        data = intent.getBundleExtra("Player Info");
        x_or_o = data.getInt("X or O");

        player1Name = findViewById(R.id.player1_name);
        player2Name = findViewById(R.id.player2_name);
        p1_name = data.getCharSequence("Player1").toString();
        p2_name = data.getCharSequence("Player2").toString();

        if(x_or_o == 1) {//indicates that player1 is O
            P1_TEXT = p1_name + "(O) -" + p1_count;
            P2_TEXT = p2_name + "(X) -" + p2_count;
            o = 1; x = 2;//assigns O as player1 and X as player2
        }
        else{//indicates player1 is X
            P1_TEXT = p1_name + "(X) -" + p1_count;
            P2_TEXT = p2_name + "(O) -" + p2_count;
            o = 2; x = 1;//assigns O as player2 and X as player1
        }
        player1Name.setText(P1_TEXT);
        player2Name.setText(P2_TEXT);

        imageButton00 = findViewById(R.id.imageButton00);
        imageButton01 = findViewById(R.id.imageButton01);
        imageButton02 = findViewById(R.id.imageButton02);
        imageButton10 = findViewById(R.id.imageButton10);
        imageButton11 = findViewById(R.id.imageButton11);
        imageButton12 = findViewById(R.id.imageButton12);
        imageButton20 = findViewById(R.id.imageButton20);
        imageButton21 = findViewById(R.id.imageButton21);
        imageButton22 = findViewById(R.id.imageButton22);
        if(flag[0][0] == 0) {
            onImageClicked(imageButton00,0,0);
        }
        if(flag[0][1] == 0) {
            onImageClicked(imageButton01,0,1);
        }
        if(flag[0][2] == 0) {
            onImageClicked(imageButton02,0,2);
        }
        if(flag[1][0] == 0) {
            onImageClicked(imageButton10,1,0);
        }
        if(flag[1][1] == 0) {
            onImageClicked(imageButton11,1,1);
        }
        if(flag[1][2] == 0) {
            onImageClicked(imageButton12,1,2);
        }
        if(flag[2][0] == 0) {
            onImageClicked(imageButton20,2,0);
        }
        if(flag[2][1] == 0) {
            onImageClicked(imageButton21,2,1);
        }
        if(flag[2][2] == 0) {
            onImageClicked(imageButton22,2,2);
        }

    }

    private void onImageClicked(final ImageButton imgbtn, final int i, final int j) {

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count % 2 ==  x_or_o) {
                    imgbtn.setImageResource(R.drawable.o_image);
                    flag[i][j] = o;
                }
                else {
                    imgbtn.setImageResource(R.drawable.x_image);
                    flag[i][j] = x;
                }
                imgbtn.setEnabled(false);
                count++;
                checkWinner(i, j);
                if(gameEnd){
                    //Toast.makeText(GameActivity.this, "Game Over",Toast.LENGTH_LONG).show();
                    imageButton00.setEnabled(false);
                    imageButton01.setEnabled(false);
                    imageButton02.setEnabled(false);
                    imageButton10.setEnabled(false);
                    imageButton11.setEnabled(false);
                    imageButton12.setEnabled(false);
                    imageButton20.setEnabled(false);
                    imageButton21.setEnabled(false);
                    imageButton22.setEnabled(false);
                    if(count <= 10) {
                        if (flag[i][j] == 1)
                            p1 = true;
                        else if (flag[i][j] == 2)
                            p2 = true;
                    }
                    setDialogBox();
                }
            }
        });
    }

    private void checkWinner(int i, int j) {
        if( (flag[i][0] == flag[i][1]) && (flag[i][0] == flag[i][2]) ) {
            gameEnd= true ;
        }
        else if( (flag[0][j] == flag[1][j]) && (flag[0][j] == flag[2][j]) ) {
            gameEnd = true ;
        }
        else if ( (flag[0][0] == flag[1][1]) && (flag[0][0] == flag[2][2]) && (i == j) ) {
            gameEnd = true ;
        }
        else if( (flag[0][2] == flag[1][1]) && (flag[0][2] == flag[2][0]) && (i+j == 2) ) {
            gameEnd = true;
        }
        else if(count > 9) {
            gameEnd = true ;
            count++;
        }
    }

    private void setDialogBox() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this) ;
        String message ;
        if(p1) {
            message = p1_name + " has won the game!!!" ;
            p1_count++;
        }
        else if(p2){
            message = p2_name + " has won the game!!!" ;
            p2_count++;
        }
        else {
            message = "Its a tie!!";
        }

        builder.setMessage(message) ;
        builder.setCancelable(false);
        builder.setPositiveButton("Rematch", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /*Intent intent = getIntent();
                finish();
                startActivity(intent);*/
                recreate();
            }
        });

        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setTitle("Game Over!");
        dialog.show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("p1_count", p1_count);
        outState.putInt("p2_count", p2_count);
        Log.d("Saving","data is saved!");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        p1_count = savedInstanceState.getInt("p1_count");
        p2_count = savedInstanceState.getInt("p2_count");
        Log.d("onRestoreInstance","called dude!");
    }
}

