package com.mfast.appgps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by victordelgadomartin on 18/1/17.
 */

public class principal extends Activity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boton);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasar();
            }
        });
    }

    private void pasar() {
        Intent pasar = new Intent(this,MainActivity.class);
        startActivity(pasar);
    }
}