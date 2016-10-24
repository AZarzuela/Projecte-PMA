package com.edu.upc.pma.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


public class CalculadoraActivity extends AppCompatActivity {

    TextView text ;

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


}
