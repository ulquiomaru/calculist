package com.barisgorgulu.calculist;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private TextView resultView, operationView;
    private String result, text;
    ExtendedDoubleEvaluator evaluator = new ExtendedDoubleEvaluator();

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
            case R.id.buttonSCI:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;

            case R.id.buttonSTD:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;

            case R.id.button0:
                operationView.append("0");
                break;

            case R.id.button1:
                operationView.append("1");
                break;

            case R.id.button2:
                operationView.append("2");
                break;

            case R.id.button3:
                operationView.append("3");
                break;

            case R.id.button4:
                operationView.append("4");
                break;

            case R.id.button5:
                operationView.append("5");
                break;

            case R.id.button6:
                operationView.append("6");
                break;

            case R.id.button7:
                operationView.append("7");
                break;

            case R.id.button8:
                operationView.append("8");
                break;

            case R.id.button9:
                operationView.append("9");
                break;

            case R.id.buttonDot:
                operationView.append(".");
                break;

            case R.id.buttonOpenB:
                operationView.append("(");
                break;

            case R.id.buttonCloseB:
                operationView.append(")");
                break;

            case R.id.buttonSquare:
                operationClicked("^2");
                break;

            case R.id.buttonXpown:
                operationClicked("^");
                break;

            case R.id.buttonSqrt:
                operationView.append("sqrt(");
                break;

            case R.id.buttonTan:
                operationView.append("tan(");
                break;

            case R.id.buttonSin:
                operationView.append("sin(");
                break;

            case R.id.buttonCos:
                operationView.append("cos(");
                break;

            case R.id.buttonLog:
                operationView.append("log(");
                break;

            case R.id.buttonLn:
                operationView.append("ln(");
                break;

            case R.id.buttonE:
                operationView.append("e");
                break;

            case R.id.buttonPi:
                operationView.append("pi");
                break;

            case R.id.buttonClear:
                resultView.setText("");
                operationView.setText("");
                result = "";
                break;

            case R.id.buttonBackspace:
                if (operationView.length() != 0) {
                    text = operationView.getText().toString();
                    if (text.endsWith("ln(")) {
                        operationView.setText(text.substring(0, text.length() - 3));
                    } else if (text.endsWith("sin(") || text.endsWith("cos(") || text.endsWith("tan(") || text.endsWith("log(")) {
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

            case R.id.buttonSigned:
                if (operationView.length() != 0) {
                    text = operationView.getText().toString();
                    char arr[] = text.toCharArray();
                    if (arr[0] == '-')
                        operationView.setText(text.substring(1, text.length()));
                    else
                        operationView.append("-", 0, 1);
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
                        resultView.setText(getString(R.string.invalidexp));
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void operationClicked(String op) {
        if (operationView.length() != 0) {
            operationView.append(op);
        } else if (!result.equals("")) {
            operationView.setText(result + op);
        }
    }

    private static String formatResult(double d) {
        if (d == (long) d)
            return String.format(Locale.getDefault(), "%d", (long) d);
        else
            return String.format(Locale.getDefault(), "%s", d);
    }

}

