package com.mfast.appgps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by victordelgadomartin on 18/1/17.
 */

public class principal extends Activity {

    private Button button;

    private ArrayList<String> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boton);

        button = (Button) findViewById(R.id.button);
        itemList = new ArrayList<String>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasar();

            }
        });


    }

    private void pasar() {
        Intent pasar = new Intent(this,MainActivity.class);
        pasar.putStringArrayListExtra("List",itemList);
        setResult(RESULT_OK,pasar);
        startActivityForResult(pasar,0);
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
            switch (requestCode){
                case 0:
                    if (resultCode == RESULT_OK) {
                        itemList.clear();
                        itemList.addAll(data.getStringArrayListExtra("List"));
                        Log.i("info", String.format("Hemos llegado onActivityResult %d", itemList.get(0)));
                    }
                    break;

                default:
                    break;
            }


    }
}