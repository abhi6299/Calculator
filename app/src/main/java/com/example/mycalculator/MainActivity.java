package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnClear,btnBracket,btnPercent,btnDiv,btnPlus,btnMinus,btnMulti,btnEqual,btnDot;
    Button btnBack;
    TextView input,output;
    String process;
    boolean checkBrackets=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9 );

        btnClear=findViewById(R.id.btnClear);
        btnBracket=findViewById(R.id.btnBracket);
        btnPercent=findViewById(R.id.btnPercentage);
        btnDiv=findViewById(R.id.btnDivision);
        btnDot=findViewById(R.id.btnDot);
        btnPlus=findViewById(R.id.btnPlus);
        btnMinus=findViewById(R.id.btnSub);
        btnEqual=findViewById(R.id.btnEqual);
        btnMulti=findViewById(R.id.btnMultiply);
        btnBack=findViewById(R.id.btnBack);

        input=findViewById(R.id.tvInput);
        output=findViewById(R.id.tvOutput);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!input.getText().toString().equals("")){
                    String value=input.getText().toString();
                    if(value.length()>0)
                    {
                        value=value.substring(0,value.length()-1);
                        input.setText(value);
                    }
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
                output.setText("");
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"9");
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"×");
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"÷");
            }
        });
        btnPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"%");
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"+");
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+"-");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process=input.getText().toString();
                input.setText(process+".");
            }
        });
        btnBracket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBrackets)
                {
                    process=input.getText().toString();
                    input.setText(process+')');
                    checkBrackets=false;
                }
                else
                {
                    process=input.getText().toString();
                    input.setText(process+'(');
                    checkBrackets=true;
                }

            }
        });
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Rhino is an open-source implementation of JavaScript written entirely in Java. It is typically embedded into Java
                // applications to provide scripting to end users. It is embedded in J2SE 6 as the default Java scripting engine.
                process=input.getText().toString();

                process=process.replaceAll("×","*");
                process=process.replaceAll("%","/100");
                process=process.replaceAll("÷","/");

                Context rhino= Context.enter();
                rhino.setOptimizationLevel(-1);//when you set the optimization level to -1, then you switch Rhino into interpreted mode,
                // which means that it goes down a different path for executing the code than it does in compiled mode. Interpreted mode
                // is, of course, much slower than compiled mode. We have a decent test suite in Rhino and don't regularly see differences
                // between the two but there may of course be bugs that we need to fix.
                String finalResult="";
                try{
                    Scriptable scriptable=rhino.initStandardObjects();
                    finalResult=rhino.evaluateString(scriptable,process,"javascript",1,null).toString();
                }
                catch (Exception e)
                {
                    //finalResult="0";
                    finalResult="Error";
                }
                output.setText(finalResult);
            }
        });
    }
}
