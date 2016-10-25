package com.edu.upc.pma.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class CalculadoraActivity extends AppCompatActivity {

    TextView text ;
    String signe;
    String num1;
    String num2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        Button clr_button = (Button) findViewById(R.id.clr_button);
        text = (TextView) findViewById(R.id.num);

        clr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.setText("");
            }
        });
    }

    public void numberClick(View view) {
        Button b = (Button) view;
        CharSequence num = b.getText();
        String numero = num.toString();
        text.setText(text.getText()+ numero);
    }

    public void operatorClick(View view) {
        Button b = (Button) view;
        // Guardar Operador en una variable
        CharSequence operator = b.getText();
        signe = operator.toString();

        // Guardar numero en una variable
        CharSequence numero1 = text.getText();
        num1 = numero1.toString();

        // Clear del TextView
        text.setText("");
    }


    public void setResult(View view) {
        // Guardo el nou numero introduit en una variable
        CharSequence numero2 = text.getText();
        num2 = numero2.toString();

        Float value1 = Float.parseFloat(num1);
        Float value2 = Float.parseFloat(num2);

        // Switch per saber quina operació hem de fer
        switch (signe) {
            case "+" :
                Float sum = value1+value2;
                String num1 = sum.toString();
                text.setText(num1);
                /*if (Math.round(result) == result){
                    Integer res = Integer.parseInt(num);
                    text.setText(res);
                } else {
                    text.setText(num);
                } */
                break;
            case "-":
                Float rest = value1-value2;
                String num2 = rest.toString();
                text.setText(num2);
            break;

            case "/":
                Float div = value1/value2;
                String num3 = div.toString();
                text.setText(num3);
            break;

            case "x":
                Float mult = value1*value2;
                String num4 = mult.toString();
                text.setText(num4);
            break;

        }
    }
}
