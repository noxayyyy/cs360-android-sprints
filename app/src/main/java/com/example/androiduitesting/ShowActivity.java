package com.example.androiduitesting;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView cityNameTextView = findViewById(R.id.textView_city_name);
        Button backButton = findViewById(R.id.button_back);

        // Retrieve the city name passed from MainActivity
        String cityName = getIntent().getStringExtra("CITY_NAME");
        if (cityName != null) {
            cityNameTextView.setText(cityName);
        }

        // Finish the current activity and go back to MainActivity when clicked
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}