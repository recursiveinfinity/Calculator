package com.example.sidd.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        TextView tvLogs = findViewById(R.id.tvLogs);


        Bus bus = (Bus) getIntent().getSerializableExtra("Bus");


        Room room = getIntent().getParcelableExtra("Room");
        tvLogs.setText(readDataFromFile());
    }

    private String readDataFromFile() {
        File file = new File(getFilesDir(), "Logs.txt");
        int size = (int) file.length();
        byte[] contents = new byte[size];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(contents);
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return new String(contents);
    }

}
