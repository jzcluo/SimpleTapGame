package com.zluo.simpletabgame;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_COLOR_NAME = "color name parameter key";
    public static final String KEY_TAPS_NUMBER = "tap number parameter key";
    private int tapLimit;
    private TextView topText;
    private TextView bottomText;
    private int[] colorArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button topButton = (Button) findViewById(R.id.TapTop);
        Button bottomButton = (Button) findViewById(R.id.TapBottom);

        bottomButton.setText(R.string.tap);
        topButton.setText(R.string.tap);
        topButton.setRotation(180);

        topButton.setBackgroundColor(Color.RED);
        bottomButton.setBackgroundColor(Color.BLUE);

        topText = (TextView) findViewById(R.id.textTop);
        topText.setRotation(180);
        bottomText = (TextView) findViewById(R.id.textBottom);
        topText.setTextColor(Color.BLACK);
        bottomText.setTextColor(Color.BLACK);

        tapLimit = Integer.parseInt(getTap().trim());
        String color = getColor();
        colorArray = createColorGradient(Color.BLACK, Color.parseColor(color), tapLimit);

        topButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentScore = Integer.parseInt(topText.getText().toString());
                topText.setText(String.valueOf(currentScore + 1));
                topText.setTextColor(colorArray[currentScore]);
                if (Integer.parseInt(topText.getText().toString()) >= tapLimit) {
                    Toast.makeText(MainActivity.this, "you top dude won", Toast.LENGTH_LONG).show();
                    onRestart();
                }
            }
        });
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentScore = Integer.parseInt(bottomText.getText().toString());
                bottomText.setText(String.valueOf(currentScore + 1));
                bottomText.setTextColor(colorArray[currentScore]);
                if (Integer.parseInt(bottomText.getText().toString()) >= tapLimit) {
                    Toast.makeText(MainActivity.this, "you bottom dude won", Toast.LENGTH_LONG).show();
                    onRestart();
                }
            }
        });

    }

    public int[] createColorGradient(int startingColor, int endingColor, int length) {
        int[] colors = new int[length];
        if (startingColor < endingColor) {
            int difference = (endingColor - startingColor) / 20;
            for (int i = 0; i < colors.length; i++) {
                startingColor += difference;
                colors[i] = startingColor;
            }
        } else {
            int difference = (startingColor - endingColor) / 20;
            for (int i = 0; i < colors.length; i++) {
                startingColor -= difference;
                colors[i] = startingColor;
            }
        }
        return colors;
    }

    public String getColor() {
        return getIntent().getStringExtra(KEY_COLOR_NAME);
    }
    public String getTap() {
        return getIntent().getStringExtra(KEY_TAPS_NUMBER);
    }

    public void onRestart() {
        topText.setText("0");
        bottomText.setText("0");
        topText.setTextColor(Color.BLACK);
        bottomText.setTextColor(Color.BLACK);
    }


}
