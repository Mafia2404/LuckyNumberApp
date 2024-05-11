package com.example.luckynumberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        //intialize the view
        TextView welcomeText = findViewById(R.id.textView2);
        TextView Luckynumber = findViewById(R.id.textView3);
        Button shareButton = findViewById(R.id.button);

        //Recieving the data from the MainActivity
        Intent intent = getIntent();
        String username = intent.getStringExtra("name");

        //genrate random number
        int randomNumber = genrateNumber();
        Luckynumber.setText("" + randomNumber);

        //share button
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(username,randomNumber);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void shareData(String username,int randomNumber) {
        //implicit intent
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        //additonal info
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "His lucky number is " + randomNumber);

        startActivity(Intent.createChooser(shareIntent, "Share using"));

    }
    public int genrateNumber(){
        Random random = new Random();
        int upperLimit = 100;
        int number = random.nextInt(100);
        return number;
    }
}