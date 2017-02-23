package com.zluo.simpletabgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
        ,View.OnClickListener{
    private String color = "Blue";
    private List<String> colorArray;
    private EditText howManyTaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button startButton = (Button)findViewById(R.id.go);
        startButton.setText(R.string.start);
        startButton.setBackgroundColor(Color.BLACK);
        startButton.setTextColor(Color.WHITE);


        colorArray = new ArrayList<>(Arrays.asList("red","Blue","green","black","white","gray","cyan","magenta",
                "yellow","lightgray","darkgray","grey","lightgrey","darkgrey","aqua","fuchsia","lime","maroon"
                ,"navy","olive","purple","silve","teal"));

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                colorArray);
        Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        colorAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(colorAdapter);
        spinner1.setBackgroundColor(Color.BLACK);

        spinner1.setOnItemSelectedListener(this);

        startButton.setOnClickListener(this);

        howManyTaps = (EditText)findViewById(R.id.reps);
        howManyTaps.setBackgroundColor(Color.BLACK);
        howManyTaps.setTextColor(Color.WHITE);
        howManyTaps.setHint(R.string.howmany);
        howManyTaps.setHintTextColor(Color.WHITE);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        color = colorArray.get(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.KEY_COLOR_NAME,color);
        String twenty = "20";
        String howMany = (howManyTaps.getText().toString().equals("")) ? twenty : howManyTaps.getText().toString();
        intent.putExtra(MainActivity.KEY_TAPS_NUMBER,howMany);
        startActivity(intent);
    }
}
