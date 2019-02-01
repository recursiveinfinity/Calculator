package com.example.sidd.calculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        TextView tvLogs = findViewById(R.id.tvLogs);

        ArrayList<String> logs =
                getIntent().getStringArrayListExtra("LogsResult");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < logs.size(); i++) {
            stringBuilder.append(logs.get(i));
            stringBuilder.append("\n");
        }

        tvLogs.setText(stringBuilder.toString());
    }

}
