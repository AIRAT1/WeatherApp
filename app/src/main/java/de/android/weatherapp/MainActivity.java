package de.android.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.android.weatherapp.data.modele.Query;
import de.android.weatherapp.data.modele.Weather;
import de.android.weatherapp.data.remote.WeatherAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_refresh) public void onClick_button_refresh() {
        WeatherAPI.Factory.getInstance().getWeather().enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Query query = response.body().getQuery();
                textView_temperature.setText(query.getResults().getChannel().getItem().getCondition().getTemp());
                textView_city.setText(query.getResults().getChannel().getLocation().getCity());
                textView_lastupdated.setText(query.getResults().getChannel().getLastBuildDate());
                textView_conditions.setText(query.getResults().getChannel().getItem().getCondition().getText());
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e("Failed", t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onClick_button_refresh();
    }
}
