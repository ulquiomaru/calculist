package com.barisgorgulu.calculist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView resultView, operationView;
    private double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        operationView = findViewById(R.id.tV_operation);
        resultView = findViewById(R.id.tV_result);

        operationView.setText("");
        resultView.setText("");
    }

    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.button0:
                operationView.setText(operationView.getText()+"0");
                break;

            case R.id.button1:
                operationView.setText(operationView.getText()+"1");
                break;

            case R.id.button2:
                operationView.setText(operationView.getText()+"2");
                break;

            case R.id.button3:
                operationView.setText(operationView.getText()+"3");
                break;

            case R.id.button4:
                operationView.setText(operationView.getText()+"4");
                break;

            case R.id.button5:
                operationView.setText(operationView.getText()+"5");
                break;

            case R.id.button6:
                operationView.setText(operationView.getText()+"6");
                break;

            case R.id.button7:
                operationView.setText(operationView.getText()+"7");
                break;

            case R.id.button8:
                operationView.setText(operationView.getText()+"8");
                break;

            case R.id.button9:
                operationView.setText(operationView.getText()+"9");
                break;

            case R.id.buttonDot:
                if(count==0 && operationView.length()!=0)
                {
                    operationView.setText(operationView.getText()+".");
                    count++;
                }
                break;

            case R.id.buttonClear:
                resultView.setText("");
                operationView.setText("");
                count=0;
                expression="";
                break;

            case R.id.buttonBackspace:
                text=operationView.getText().toString();
                if(text.length()>0)
                {
                    if(text.endsWith("."))
                    {
                        count=0;
                    }
                    String newText=text.substring(0,text.length()-1);
                    //to delete the data contained in the brackets at once
                    if(text.endsWith(")"))
                    {
                        char []a=text.toCharArray();
                        int pos=a.length-2;
                        int counter=1;
                        //to find the opening bracket position
                        for(int i=a.length-2;i>=0;i--)
                        {
                            if(a[i]==')')
                            {
                                counter++;
                            }
                            else if(a[i]=='(')
                            {
                                counter--;
                            }
                            //if decimal is deleted b/w brackets then count should be zero
                            else if(a[i]=='.')
                            {
                                count=0;
                            }
                            //if opening bracket pair for the last bracket is found
                            if(counter==0)
                            {
                                pos=i;
                                break;
                            }
                        }
                        newText=text.substring(0,pos);
                    }
                    //if operationView edit text contains only - sign or sqrt at last then clear the edit text operationView
                    if(newText.equals("-")||newText.endsWith("sqrt"))
                    {
                        newText="";
                    }
                    //if pow sign is left at the last
                    else if(newText.endsWith("^"))
                        newText=newText.substring(0,newText.length()-1);

                    operationView.setText(newText);
                }
                break;

            case R.id.buttonPlus:
                resultViewClicked("+");
                break;

            case R.id.buttonMinus:
                resultViewClicked("-");
                break;

            case R.id.buttonDivide:
                resultViewClicked("/");
                break;

            case R.id.buttonMultiply:
                resultViewClicked("*");
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
                break;

            case R.id.posneg:
                if(operationView.length()!=0)
                {
                    String s=operationView.getText().toString();
                    char arr[]=s.toCharArray();
                    if(arr[0]=='-')
                        operationView.setText(s.substring(1,s.length()));
                    else
                        operationView.setText("-"+s);
                }
                break;*/

            case R.id.buttonEquals:
                /*for more knowledge on DoubleEvaluator and its tutorial go to the below link
                http://javaluator.sourceforge.net/en/home/*/
                if(operationView.length()!=0)
                {
                    text=operationView.getText().toString();
                    expression=resultView.getText().toString()+text;
                }
                resultView.setText("");
                if(expression.length()==0)
                    expression="0.0";
                DoubleEvaluator evaluator = new DoubleEvaluator();
                try
                {
                    //evaluate the expression
                    result=new ExtendedDoubleEvaluator().evaluate(expression);
                    //insert expression and result in sqlite database if expression is valid and not 0.0
                    if(!expression.equals("0.0"))
                        dbHelper.insert("STANDARD",expression+" = "+result);
                    operationView.setText(result+"");
                }
                catch (Exception e)
                {
                    operationView.setText("Invalid Expression");
                    resultView.setText("");
                    expression="";
                    e.printStackTrace();
                }
                break;
/*
            case R.id.openBracket:
                resultView.setText(resultView.getText()+"(");
                break;

            case R.id.closeBracket:
                resultView.setText(resultView.getText()+")");
                break;*/

            case R.id.buttonHistory:
/*                Intent i=new Intent(this,History.class);
                i.putExtra("calcName","STANDARD");
                startActivity(i);*/
                break;
        }
    }

/*    private void operationClicked(String op)
    {
        if(e2.length()!=0)
        {
            String text=e2.getText().toString();
            e1.setText(e1.getText() + text+op);
            e2.setText("");
            count=0;
        }
        else
        {
            String text=e1.getText().toString();
            if(text.length()>0)
            {
                String newText=text.substring(0,text.length()-1)+op;
                e1.setText(newText);
            }
        }
    }*/

}

