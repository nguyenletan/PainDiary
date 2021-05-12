package com.example.paindiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.paindiary.WeatherResponse;
import com.example.paindiary.WeatherService;
import com.example.paindiary.databinding.HomePageBinding;
import com.example.paindiary.viewmodel.SharedViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePage extends Fragment {
    private SharedViewModel model;
    private HomePageBinding addBinding;
    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "886705b4c1182eb1c69f28eb8c520e20";
    public static String lat = "-37.814";
    public static String lon = "144.9633";

    private TextView weatherData;

    public HomePage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = HomePageBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        weatherData = addBinding.textMessage;
        getCurrentData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }

    void getCurrentData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService service = retrofit.create(WeatherService.class);
        Call call = service.getCurrentWeatherData(lat, lon, AppId);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = (WeatherResponse) response.body();
                    assert weatherResponse != null;

                    String stringBuilder =
                                    "City: Melbourne" +
                                    "\n" +
                                    "Temperature: " +
                                    weatherResponse.main.temp +
                                    "\n" +
                                    "Humidity: " +
                                    weatherResponse.main.humidity +
                                    "\n" +
                                    "Pressure: " +
                                    weatherResponse.main.pressure;

                    weatherData.setText(stringBuilder);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                weatherData.setText(t.getMessage());
            }
        });
    }
}
