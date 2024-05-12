package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import model.ToDoItem;

public class ToDoActivity extends AppCompatActivity {

    private ToDoItem tdi;
    private EditText etNev;
    private EditText etEv;
    private EditText monthText;
    private EditText etNap;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        etEv = findViewById(R.id.etEv);
        monthText = findViewById(R.id.month_text);
        etNap = findViewById(R.id.etNap);
        etNev = findViewById(R.id.etNev);
        intent = getIntent();

        tdi = (ToDoItem) intent.getSerializableExtra("tdi");
        if (tdi != null) {
            etNev.setText(tdi.getName());
            etEv.setText(tdi.getYear() + "");
            monthText.setText(tdi.getMonth() + "");
            etNap.setText(tdi.getDay() + "");
        }

    }

    public void megsem(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void clicked(View view) {
        if (tdi == null) {
            tdi = new ToDoItem();
        }

        tdi.setName(etNev.getText().toString());
            tdi.setYear(Integer.parseInt(etEv.getText().toString()));
            tdi.setMonth(Integer.parseInt(monthText.getText().toString()));
            tdi.setDay(Integer.parseInt(etNap.getText().toString()));
            tdi.setIsCompleted(0);
            intent.putExtra("tdi", tdi);
            setResult(RESULT_OK, intent);
            finish();

    }
}