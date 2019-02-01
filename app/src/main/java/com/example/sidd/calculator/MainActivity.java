package com.example.sidd.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etNumberOne;
    EditText etNumberTwo;

    Button btnAdd;
    Button btnLogs;

    TextView tvResult;

    private List<String> log = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumberOne = findViewById(R.id.etNumberOne);
        etNumberTwo = findViewById(R.id.etNumberTwo);

        btnAdd = findViewById(R.id.btnAdd);
        btnLogs = findViewById(R.id.btnLogs);

        tvResult = findViewById(R.id.tvResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = add(etNumberOne.getText().toString(),
                        etNumberTwo.getText().toString());
                tvResult.setText(result);
                log.add("Result of Addition: " + result);
            }
        });

        btnLogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        LogsActivity.class);
                intent.putStringArrayListExtra("LogsResult",
                        (ArrayList<String>) log);
                startActivity(intent);
            }
        });
    }

    private String add(String numberOne, String numberTwo) {
        if (numberOne.equals("") || numberTwo.isEmpty()) {
            Toast.makeText(this, "Please enter a valid number",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int a = Integer.parseInt(numberOne);
        int b = Integer.parseInt(numberTwo);
        int result = a + b;
        return Integer.toString(result);
    }
}
