package com.example.hellosharedprefs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.example.hellosharedprefs.constants.Constants;
import com.example.hellosharedprefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    int count = 0;
    int color;

    SharedPreferences preferences;
    String sharedPreferencesFile = "com.example.android.hellosharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE);

//        if(savedInstanceState != null) {
//            count = savedInstanceState.getInt(Constants.COUNT_KEY);
//            if (count != 0) {
//                binding.tvCount.setText(""+count);
//            }
//            color = savedInstanceState.getInt(Constants.COLOR_KEY);
//            binding.tvCount.setTextColor(color);
//        }

        count = preferences.getInt(Constants.COUNT_KEY, 0);
        binding.tvCount.setText(""+count);
        color = preferences.getInt(Constants.COLOR_KEY, color);
        binding.tvCount.setTextColor(color);

        binding.btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                binding.tvCount.setText(""+count);
            }
        });

        binding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                binding.tvCount.setText(""+count);
                binding.tvCount.setTextColor(getResources().getColor(R.color.black));

                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
            }
        });

    }

    public void changeBackground(View view) {
        switch (view.getId()) {
            case R.id.btn_black:
                color = getResources().getColor(R.color.black);
                break;
            case R.id.btn_red:
                color = getResources().getColor(R.color.red);
                break;
            case R.id.btn_blue:
                color = getResources().getColor(R.color.blue);
                break;
            default:
                color = getResources().getColor(R.color.yellow);
        }
        binding.tvCount.setTextColor(color);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(Constants.COUNT_KEY, count);
        outState.putInt(Constants.COLOR_KEY, color);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Constants.COUNT_KEY, count);
        editor.putInt(Constants.COLOR_KEY, color);
        editor.apply();
    }
}
