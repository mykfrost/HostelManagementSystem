package com.example.hostelmanagementsystem;

import static com.example.hostelmanagementsystem.R.id.button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startSecondActivity();
            }
        });
    }
    // This method is called when the button is clicked (as defined in XML)
    public void startSecondActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}