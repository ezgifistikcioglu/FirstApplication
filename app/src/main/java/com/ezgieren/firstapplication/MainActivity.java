package com.ezgieren.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    String textViewText = "Your age:  ";
    String uniqueKey = "storedAge";

    EditText editText;
    TextView textView;

    SharedPreferences sharedPreferences;

    public int getStoredSaveAge() {
        return sharedPreferences.getInt(uniqueKey, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextAge);
        textView = findViewById(R.id.textView);

        sharedPreferencesUsing();
    }

    public void saveAge(View view) {
        if (!editText.getText().toString().matches("")) {
            int userAge = Integer.parseInt(editText.getText().toString());
            textView.setText(textViewText + userAge);
            // Save and show the stored data in your textView
            sharedPreferences.edit().putInt(uniqueKey, userAge).apply();
        }
    }

    public void deleteAge(View view) {
        int storedDeleteAge = getStoredSaveAge();
        if (storedDeleteAge != 0) {
            sharedPreferences.edit().remove(uniqueKey).apply();
            textView.setText(textViewText);
        }
    }

    public void sharedPreferencesUsing() {
        sharedPreferences = this.getSharedPreferences("com.ezgieren.firstapplication", Context.MODE_PRIVATE);
        int storedSaveAge= sharedPreferences.getInt(uniqueKey, 0);
        if (storedSaveAge == 0) {
            textView.setText(textViewText);
        } else {
            textView.setText(textViewText + storedSaveAge);
        }
    }
}