package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer(); //Запуск работы метода runTimer()
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        if(wasRunning)
        {
            running = true;
        }
    }
    public void onStart(View view)
    {
        running = true;
    }
    public void onStop(View view)
    {
        running = false;
    }
    public void onReset(View view)
    {
        running = false;
        seconds = 0;
    }
    private void runTimer()
    {
        final TextView textView = (TextView) findViewById(R.id.textView);
        final Handler handler = new Handler();
        handler.post(new Runnable()  //Ускорение выполнения кода с помощью handler.pos()
        {
            @Override
            public void run()
            {
                int hours = seconds/3600;
                int minuts = (seconds % 3600) / 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minuts, seconds);
                textView.setText(time);
                if(running)
                {
                    seconds ++;
                }
                handler.postDelayed(this, 1000); //Код передается на повторение с задержкой 1 секунда.
            }
        });
    }

}