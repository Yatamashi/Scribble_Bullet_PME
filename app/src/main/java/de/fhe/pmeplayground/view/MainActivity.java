package de.fhe.pmeplayground.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import de.fhe.pmeplayground.R;
import de.fhe.pmeplayground.core.MyApplication;
import de.fhe.pmeplayground.core.SettingsHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToSecond = findViewById(R.id.button_go_to_second_activity);

        goToSecond.setOnClickListener( view -> {
            Intent i = new Intent(this, SecondActivity.class);
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        MyApplication app = (MyApplication)getApplication();
        SettingsHandler settingsHandler = app.getSettingsHandler();

        TextView textView = findViewById(R.id.activity_main_textview);
        String textViewValue = getString(R.string.text_main_counter_value,
                settingsHandler.getCounterValue());
        textView.setText(textViewValue);
    }
}