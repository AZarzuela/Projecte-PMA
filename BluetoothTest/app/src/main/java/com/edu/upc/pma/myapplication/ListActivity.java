package com.edu.upc.pma.myapplication;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class ListActivity extends AppCompatActivity {

    // Declarem referencies
    private ListView listView;

    // Array list perqué mostrarem el nom i la direcció MAC
    private ArrayList<String> mDeviceList = new ArrayList<String>();
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> adapter;
    private final static int REQUEST_ENABLE_BT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDeviceList);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        // Declarem el Bluetooth Adapter
        // https://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if(!mBluetoothAdapter.isEnabled()){
            Intent enabledBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabledBtIntent, REQUEST_ENABLE_BT);
        }


        final List<String> s = new ArrayList<String>();
        for (BluetoothDevice bt : pairedDevices) {
            s.add(bt.getName() + "\n" + bt.getAddress());
            Log.i("info", bt.getName() + "\n" + bt.getAddress());
        }

        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, s));
        /*
        if (!mBluetoothAdapter.isEnabled()) {
            Toast.makeText(ListActivity.this, R.string.bt_toast, Toast.LENGTH_LONG).show();
        }*/

            mBluetoothAdapter.startDiscovery();

            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            //registerReceiver(mReceiver, filter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, final int pos, long id) {
                // Creacio quadre de dialeg per conectar amb un dispositiu
                String device = s.get(pos);

                String dialog =
                        String.format("Are you sure you want to connect with: '%s' ?", device);
                // Constructor del quadre de dialeg
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);

                // Evitar que l'usuari pugui sortir del quadre de dialeg sense apretar un dels dos botons
                builder.setCancelable(false);

                // Mostrar el quadre de dialeg
                builder.setMessage(dialog);

                //Creació del primer 'button' amb un recurs d'string
                builder.setPositiveButton(R.string.conectar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                //Creació del segon 'button' amb un recurs d'string
                builder.setNegativeButton(R.string.calcelar, null);

                builder.create().show();
            }
        });

    }
/*
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDeviceList.add(device.getName() + "\n" + device.getAddress());
                //Log.i("test", device.getName() + "\n" + device.getAddress());
                //String id = "F4:5C:89:9F:00:6E";
                //Toast.makeText(ListActivity.this, device.getAddress(), Toast.LENGTH_LONG).show();
                listView.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, mDeviceList));
            }
        }
    };
*/

}