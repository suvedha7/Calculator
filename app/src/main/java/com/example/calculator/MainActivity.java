package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity  {
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bm, bd, bc, bde, beq, bac;
    String k = "", f = "", s = "", op = "", t = "";
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditText
        ed = findViewById(R.id.editTextText);

        // Initialize Buttons
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        ba = findViewById(R.id.buttonadd);
        bs = findViewById(R.id.buttonsub);
        bm = findViewById(R.id.buttonmul);
        bd = findViewById(R.id.buttondiv);
        bde = findViewById(R.id.buttond);
        beq = findViewById(R.id.buttoneq);
        bac = findViewById(R.id.buttonac);
        bc = findViewById(R.id.buttonc);

        // Set background tint for buttons
        int buttonTintColor = Color.parseColor("#e0e800"); // Example color, you can change it as needed
        Button[] buttons = {b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bm, bd, bc, bde, beq, bac};
        for (Button button : buttons) {
            button.setBackgroundTintList(ColorStateList.valueOf(buttonTintColor));
        }

        // Set click listeners for buttons
        setDigitButtonClickListener(b0, "0");
        setDigitButtonClickListener(b1, "1");
        setDigitButtonClickListener(b2, "2");
        setDigitButtonClickListener(b3, "3");
        setDigitButtonClickListener(b4, "4");
        setDigitButtonClickListener(b5, "5");
        setDigitButtonClickListener(b6, "6");
        setDigitButtonClickListener(b7, "7");
        setDigitButtonClickListener(b8, "8");
        setDigitButtonClickListener(b9, "9");
        ba.setOnClickListener(createOperatorClickListener("+"));
        bs.setOnClickListener(createOperatorClickListener("-"));
        bm.setOnClickListener(createOperatorClickListener("*"));
        bd.setOnClickListener(createOperatorClickListener("/"));
        bde.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!k.contains(".")) {
                    t = ".";
                    k = k + t;
                    ed.setText(k);
                }
            }
        });
        beq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = ed.getText().toString();
                if (k.contains(".") || f.contains(".") || s.contains(".")) {
                    // Decimal point calculation
                    double a = Double.parseDouble(f);
                    double b = Double.parseDouble(s);
                    performCalculation(a, b);
                } else {
                    // Integer calculation
                    int a = Integer.parseInt(f);
                    int b = Integer.parseInt(s);
                    performCalculation(a, b);
                }
            }
            private void performCalculation(double a, double b) {
                double r = 0.0;
                switch (op) {
                    case "+":
                        r = a + b;
                        break;
                    case "-":
                        r = a - b;
                        break;
                    case "*":
                        r = a * b;
                        break;
                    case "/":
                        if (b != 0) {
                            r = a / b;
                        } else {
                            ed.setText("Error: Division by zero");
                            return;
                        }
                        break;
                }

                // Round the result to 10 decimal places
                DecimalFormat df = new DecimalFormat("#.##########");
                String roundedResult = df.format(r);

                ed.setText(roundedResult);
                op = "";
                f = "";
                k = "";
            }
            private void performCalculation(int a, int b) {
                int r = 0;
                switch (op) {
                    case "+":
                        r = a + b;
                        break;
                    case "-":
                        r = a - b;
                        break;
                    case "*":
                        r = a * b;
                        break;
                    case "/":
                        if (b != 0) {
                            r = a / b;
                        } else {
                            ed.setText("Error: Division by zero");
                            return;
                        }
                        break;
                }
                ed.setText(String.valueOf(r));
                op = "";
                f = "";
                k = "";
            }
        });
        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f = "";
                op = "";
                s = "";
                k = "";
                ed.setText("");
            }
        });
    }
    private View.OnClickListener createOperatorClickListener(final String operator) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                op = operator;
                f = ed.getText().toString();
                ed.setText("");
                k = "";
            }
        };
    }
    private void setDigitButtonClickListener(Button button, String digit) {
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t = digit;
                k = k + t;
                ed.setText(k);
            }
        });
    }
}

