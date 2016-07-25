package de.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.textView_city) TextView textView_city;
    @Bind(R.id.textView_temperature) TextView textView_temperature;
    @Bind(R.id.textView_lastupdated) TextView textView_lastupdated;
    @Bind(R.id.textView_conditions) TextView textView_conditions;
    @Bind(R.id.button_refresh) Button button_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
