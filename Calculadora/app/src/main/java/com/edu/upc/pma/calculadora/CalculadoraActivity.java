package com.edu.upc.pma.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class CalculadoraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        Button clr_button = (Button) findViewById(R.id.clr_button);
        Button siete_button = (Button) findViewById(R.id.siete_button);
        Button ocho_button = (Button) findViewById(R.id.ocho_button);
        Button nueve_button = (Button) findViewById(R.id.nueve_button);
        Button dividir_button = (Button) findViewById(R.id.dividir_button);
        Button cuatro_btn = (Button) findViewById(R.id.cuatro_button);
        Button cinco_btn = (Button) findViewById(R.id.cinco_button);
        Button seis_btn = (Button) findViewById(R.id.seis_button);
        Button multiplicar_button = (Button) findViewById(R.id.multiplicar_button);
        Button uno_button = (Button) findViewById(R.id.uno_button);
        Button dos_button = (Button) findViewById(R.id.dos_button);
        Button tres_button = (Button) findViewById(R.id.tres_button);
        Button resta_button = (Button) findViewById(R.id.resta_button);
        Button punto_button = (Button) findViewById(R.id.punto_button);
        Button igual_button = (Button) findViewById(R.id.igual_button);
        Button suma_button = (Button) findViewById(R.id.suma_button);

        final TextView num = (TextView) findViewById(R.id.num);




        clr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = String.format(" ");
                num.setText(text);
            }
        });


        nueve_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor = 9;
                String text = String.format("%d", valor);
                num.setText(text);
            }
        });


    }
}
