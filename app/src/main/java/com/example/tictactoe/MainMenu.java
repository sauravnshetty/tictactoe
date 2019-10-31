package com.example.tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainMenu extends AppCompatActivity {

    Button playButton, quitButton, optionsButton;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText player1_et, player2_et ;
    Button ok_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        playButton = findViewById(R.id.play);
        quitButton = findViewById(R.id.quit);
        optionsButton = findViewById(R.id.options);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, GameActivity.class);
                String p1_name = player1_et.getText().toString() ;
                String p2_name = player2_et.getText().toString() ;
                int x_or_o ;//used to decide whether player1 is X(0) or O(1)
                if(radioButton.getId() == radioGroup.getCheckedRadioButtonId())
                    x_or_o = 0;
                else
                    x_or_o = 1;
                Bundle data = new Bundle();
                data.putCharSequence("Player1",p1_name);
                data.putCharSequence("Player2",p2_name);
                data.putInt("X or O",x_or_o);
                intent.putExtra("Player Info",data);
                startActivity(intent);
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final AlertDialog.Builder options_dialog = new AlertDialog.Builder(MainMenu.this) ;

        //LayoutInflater inflater = MainMenu.this.getLayoutInflater();
        View dialog_view = getLayoutInflater().inflate(R.layout.options_layout, null);
        options_dialog.setView(dialog_view);

        radioGroup = dialog_view.findViewById(R.id.radioGroup);
        player1_et = dialog_view.findViewById(R.id.editp1);
        player2_et = dialog_view.findViewById(R.id.editp2);
        radioButton = dialog_view.findViewById(radioGroup.getCheckedRadioButtonId());
        ok_btn = dialog_view.findViewById(R.id.button_ok);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog dialog = options_dialog.create();
                dialog.show();

                ok_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
