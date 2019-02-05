package com.example.sidd.calculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNumberOne;
    EditText etNumberTwo;
    EditText etName;

    Button btnAdd;
    Button btnLogs;
    Button btnSave;

    TextView tvResult;
    Group nameGroup;

    private List<String> log = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumberOne = findViewById(R.id.etNumberOne);
        etNumberTwo = findViewById(R.id.etNumberTwo);
        etName = findViewById(R.id.etName);

        btnAdd = findViewById(R.id.btnAdd);
        btnLogs = findViewById(R.id.btnLogs);
        btnSave = findViewById(R.id.btnSave);

        tvResult = findViewById(R.id.tvResult);

        nameGroup = findViewById(R.id.groupName);

        btnAdd.setOnClickListener(this);

        btnSave.setOnClickListener(this);

        btnLogs.setOnClickListener(this);

        readNameFromSharedPreferences();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                handleSetName();
                break;
            case R.id.btnAdd:
                handleAddClick();
                break;
            case R.id.btnLogs:
                showLogs();
                break;
        }
    }

    private void handleSetName() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", etName.getText().toString());
        editor.apply();
    }

    private void handleAddClick() {
        String result = add(etNumberOne.getText().toString(),
                etNumberTwo.getText().toString());
        tvResult.setText(result);
        log.add("Result of Addition: " + result);
    }

    private void showLogs() {
        Intent intent = new Intent(MainActivity.this,
                LogsActivity.class);
        intent.putStringArrayListExtra("LogsResult",
                (ArrayList<String>) log);
        Bus bus = new Bus("Grey", "Black");
        intent.putExtra("Bus", bus);
        Room room = new Room("Training Room", 4);
        intent.putExtra("Room", room);
        startActivity(intent);
    }

    private void readNameFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences",
                Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "");
        if (!name.isEmpty()) {
           nameGroup.setVisibility(View.GONE);
        }
        tvResult.setText(name);
    }
}
