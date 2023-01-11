package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button mSetAlarmButton10;
    Button mSetAlarmButton20;
    Button mSetAlarmButton30;
    Button mSetAlarmButton60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // turn off night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // create alarm buttons
        mSetAlarmButton10 = (Button) findViewById(R.id.button10);
        mSetAlarmButton20 = (Button) findViewById(R.id.button20);
        mSetAlarmButton30 = (Button) findViewById(R.id.button30);
        mSetAlarmButton60 = (Button) findViewById(R.id.button60);



        mSetAlarmButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                intent.putExtra(AlarmClock.EXTRA_LENGTH, 600);
                startActivity(intent);
            }
        });

        mSetAlarmButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                intent.putExtra(AlarmClock.EXTRA_LENGTH, 600);
                startActivity(intent);
            }
        });

        mSetAlarmButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                intent.putExtra(AlarmClock.EXTRA_LENGTH, 1200);
                startActivity(intent);
            }
        });

        mSetAlarmButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                intent.putExtra(AlarmClock.EXTRA_LENGTH, 1800);
                intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Smacznego! <3");
                startActivity(intent);
            }
        });

        mSetAlarmButton60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AlarmClock.ACTION_SET_TIMER);
                intent.putExtra(AlarmClock.EXTRA_LENGTH, 3600);
                startActivity(intent);
            }
        });
    }
}