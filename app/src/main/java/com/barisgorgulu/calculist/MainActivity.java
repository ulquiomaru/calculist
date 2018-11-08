package com.barisgorgulu.calculist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private TextView resultView, operationView;
    //    private Double result = 0.0;
    private int count = 0;
    private String text, result;
    DoubleEvaluator evaluator = new DoubleEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationView = findViewById(R.id.tV_operation);
        resultView = findViewById(R.id.tV_result);

        Log.d("MainActivity", "Calculator Initialized.");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button0:
                operationView.setText(operationView.getText() + "0");
                break;

            case R.id.button1:
                operationView.setText(operationView.getText() + "1");
                break;

            case R.id.button2:
                operationView.setText(operationView.getText() + "2");
                break;

            case R.id.button3:
                operationView.setText(operationView.getText() + "3");
                break;

            case R.id.button4:
                operationView.setText(operationView.getText() + "4");
                break;

            case R.id.button5:
                operationView.setText(operationView.getText() + "5");
                break;

            case R.id.button6:
                operationView.setText(operationView.getText() + "6");
                break;

            case R.id.button7:
                operationView.setText(operationView.getText() + "7");
                break;

            case R.id.button8:
                operationView.setText(operationView.getText() + "8");
                break;

            case R.id.button9:
                operationView.setText(operationView.getText() + "9");
                break;

            case R.id.buttonDot:
                operationView.setText(operationView.getText() + ".");
                break;

            case R.id.buttonClear:
                clearScreen();
                break;

            case R.id.buttonBackspace:
                text = operationView.getText().toString();
                if (text.length() != 0) {
                    if (text.endsWith("sin(") || text.endsWith("cos(") || text.endsWith("tan(")) {
                        operationView.setText(text.substring(0, text.length() - 4));
                    } else if (text.endsWith("sqrt(")) {
                        operationView.setText(text.substring(0, text.length() - 5));
                    } else
                        operationView.setText(text.substring(0, text.length() - 1));
                }
                break;

            case R.id.buttonPlus:
                operationClicked("+");
                break;

            case R.id.buttonMinus:
                operationClicked("-");
                break;

            case R.id.buttonDivide:
                operationClicked("/");
                break;

            case R.id.buttonMultiply:
                operationClicked("*");
                break;

/*            case R.id.sqrt:
                if(operationView.length()!=0)
                {
                    text=operationView.getText().toString();
                    operationView.setText("sqrt(" + text + ")");
                }
                break;

            case R.id.square:
                if(operationView.length()!=0)
                {
                    text=operationView.getText().toString();
                    operationView.setText("("+text+")^2");
                }
                break;*/

            case R.id.buttonSigned:
                if (operationView.length() != 0) {
                    text = operationView.getText().toString();
                    char arr[] = text.toCharArray();
                    if (arr[0] == '-')
                        operationView.setText(text.substring(1, text.length()));
                    else
                        operationView.setText("-" + text);
                }
                break;

            case R.id.buttonEquals:
                if (operationView.length() != 0) {
                    text = operationView.getText().toString();
                    try {
                        result = formatResult(evaluator.evaluate(text));
                        resultView.setText(result);
                        operationView.setText("");
                    } catch (Exception e) {
                        resultView.setText(R.string.invalidexp);
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void operationClicked(String op) {
        if (operationView.length() != 0) {
            operationView.setText(operationView.getText() + op);
        } else if (!result.equals("")) {
            operationView.setText(result + op);
        }
    }

    private void clearScreen() {
        resultView.setText("");
        operationView.setText("");
        result = "";
    }

    private static String formatResult(double d) {
        if (d == (long) d)
            return String.format(Locale.getDefault(), "%d", (long) d);
        else
            return String.format(Locale.getDefault(), "%s", d);
    }

}

