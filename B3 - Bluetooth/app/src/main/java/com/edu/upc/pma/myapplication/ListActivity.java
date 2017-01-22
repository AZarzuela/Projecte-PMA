package com.edu.upc.pma.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ListActivity extends AppCompatActivity {

    // Declarem referencies
    private ListView listView;

    // Array list perqué mostrarem el nom i la direcció MAC

    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<DeviceItem> adapter;
    private final static int REQUEST_ENABLE_BT = 1;
    private static final int BOND_BONDED = 12;
    private static final int BOND_NONE = 10;
    private static final int REQ_BT_ENABLE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        final List<DeviceItem> s = new ArrayList<DeviceItem>();

        adapter = new ArrayAdapter<DeviceItem>(this, android.R.layout.simple_list_item_1, s);
        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        // Declarem el Bluetooth Adapter
        // https://developer.android.com/reference/android/bluetooth/BluetoothAdapter.html
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        if (mBluetoothAdapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.no_compatible)
                    .setMessage(R.string.no_suportado)
                    .setPositiveButton(R.string.salir, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        if(!mBluetoothAdapter.isEnabled()){
            Intent enabledBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabledBtIntent, REQUEST_ENABLE_BT);
        }

        //Después de emparejado, el teléfono se conectará automáticamente al accesorio o dispositivo
        // cuando esté dentro de su alcance y el Bluetooth del teléfono esté activado.

        for (BluetoothDevice bt : pairedDevices) {
            s.add(new DeviceItem(bt.getName(), bt.getAddress(), bt.getBondState()));
                Log.i("info", bt.getName() + "\n" + bt.getAddress());
                Log.i("info", String.valueOf(bt.getBondState()));
            if(bt.getBondState() == BOND_BONDED){
                Log.i("info", "PRUEBA");

            }
        }

        listView.setAdapter(new ArrayAdapter<DeviceItem>(this,
                android.R.layout.simple_list_item_1, s));

            //mBluetoothAdapter.startDiscovery();

            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            //registerReceiver(mReceiver, filter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, final int pos, long id) {

                // Creacio quadre de dialeg per conectar amb un dispositiu
                final int state = s.get(pos).getConnected();
                final String device = s.get(pos).getDeviceName();

                String dialog =
                        String.format("Estás seguro que quieres conectarte con: '%s' ?", device);
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
                        if(state == BOND_BONDED){
                            Toast.makeText(ListActivity.this, R.string.ok, Toast.LENGTH_SHORT).show();
                        } else if(state == BOND_NONE){
                            Toast.makeText(ListActivity.this, R.string.ko, Toast.LENGTH_SHORT).show();
                        }
                        Log.i("info", String.valueOf(device));

                    }
                });

                //Creació del segon 'button' amb un recurs d'string
                builder.setNegativeButton(R.string.cancelar, null);

                builder.create().show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQ_BT_ENABLE){
            if (resultCode == RESULT_OK){
                Toast.makeText(getApplicationContext(), R.string.activado , Toast.LENGTH_LONG).show();
            }
            if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), R.string.desactivado, Toast.LENGTH_LONG).show();
                ///finish();
            }
        }
    }

    // BroadcastReceiver mReveicer serveix per mostrar tots els dispositius amb el Bluetooth
    // aprop (indiferentment si estan emparellats o no). Alfinal fem servir el métode de aparellar
    // dispositius

/*
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