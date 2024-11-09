package com.assignment.evaluation01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView getTextA;
    TextView getTextB;
    TextView displayCalc;
    TextView getOperator;
    RadioGroup radioGroup;
    RadioButton add;
    RadioButton subtract;
    RadioButton multiply;
    RadioButton Divide;
    Button Reset;
    Button Calc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textA), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getTextA=findViewById(R.id.editTextA);
        getTextB=findViewById(R.id.editTextB);
        displayCalc=findViewById(R.id.textViewOutput);
        getOperator=findViewById(R.id.textViewOperator);
        radioGroup=findViewById(R.id.radioGroup);
        String A=getTextA.getText().toString();
        String B=getTextB.getText().toString();
        if(getOperator.getText().toString().equals("Operator"))
        {
            getOperator.setText("?");
        }
        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOperator.setText("?");
                getTextA.setText("");
                getTextB.setText("");
            }
        });
        findViewById(R.id.radioButtonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroup.getCheckedRadioButtonId();
                String A=getTextA.getText().toString();
                String B=getTextB.getText().toString();
                getOperator.setText("+");

                if(!A.equals("") && !B.equals("") && checkedId == R.id.radioButtonAdd){
                calculate(A,B, "+");}
            }
        });
        findViewById(R.id.radioButtonSubtract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroup.getCheckedRadioButtonId();
                String A=getTextA.getText().toString();
                String B=getTextB.getText().toString();
                getOperator.setText("-");
                if(!A.equals("") && !B.equals("") && checkedId == R.id.radioButtonSubtract){
                calculate(A,B, "-");}
            }
        });
        findViewById(R.id.radioButtonMultiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroup.getCheckedRadioButtonId();
                String A=getTextA.getText().toString();
                String B=getTextB.getText().toString();
                getOperator.setText("*");
                if(!A.equals("") && !B.equals("") && checkedId == R.id.radioButtonMultiply){
                calculate(A,B, "*");}
            }
        });
        findViewById(R.id.radioButtonDivide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroup.getCheckedRadioButtonId();
                String A=getTextA.getText().toString();
                String B=getTextB.getText().toString();
                getOperator.setText("/");
                if(!A.equals("") && !B.equals("") && checkedId == R.id.radioButtonDivide){
                calculate(A,B, "/");}
            }
        });

        if(A!="" && B!=""){

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroup.getCheckedRadioButtonId();
                String A=getTextA.getText().toString();
                String B=getTextB.getText().toString();
                if(A.isEmpty() || B.isEmpty()){
                    Toast.makeText(MainActivity.this, "No numbers entered", Toast.LENGTH_SHORT).show();

                }
                if(checkedId==0){
                    Toast.makeText(MainActivity.this, "No operation selected", Toast.LENGTH_SHORT).show();

                }
                

                try {

                    if (checkedId == R.id.radioButtonAdd) {
                        getOperator.setText("+");
                        calculate(A,B, "+");
                    }
                    else if (checkedId == R.id.radioButtonSubtract)
                    {
                        getOperator.setText("-");
                        calculate(A,B, "-");}
                    else if (checkedId == R.id.radioButtonMultiply) {
                        getOperator.setText("*");
                        calculate(A,B, "*");
                    } else if (checkedId==R.id.radioButtonDivide) {
                        getOperator.setText("/");
                        calculate(A,B, "/");
                    }
                }catch(NumberFormatException exception){
                    Toast.makeText(MainActivity.this, "Enter a valid number !!", Toast.LENGTH_SHORT).show();
                }
            }
        });}
    }
    private void calculate(String A,String B, String operation)
    {
        Double a=Double.parseDouble(A);
        Double b=Double.parseDouble(B);
        Double calc;
        try{
            if (operation=="+")
            {
                calc=a+b;
                displayCalc.setText(a+" + "+b+" = "+calc.toString());
            }
            else if (operation=="-")
            {
                calc=a-b;
                displayCalc.setText(a+" - "+b+" = "+calc.toString());
            } else if (operation=="*") {
                calc=a*b;
                displayCalc.setText(a+" * "+b+" = "+calc.toString());
            } else if (operation=="/") {
                calc=a/b;
                displayCalc.setText(a+" / "+b+" = "+calc.toString());
            }
        }catch (NumberFormatException exception){
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_LONG).show();
        }
    }
}