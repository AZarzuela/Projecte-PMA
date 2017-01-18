package com.mfast.appgps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class MainActivity extends Activity {

	TextView mensaje1;
	TextView mensaje2;

	private ListView list;
	private ArrayList<String> itemList;
	private ArrayAdapter<String> adapter;
    private ArrayList<String> listposicions = new ArrayList<String>();

    private static final String FILENAME = "pos.txt";

	private GoogleApiClient client;


	private ListView listpos;
    private TextView mensaje3;

	public MainActivity() {
	}


    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mensaje1 = (TextView) findViewById(R.id.mensaje_id);
		mensaje2 = (TextView) findViewById(R.id.mensaje_id2);
        mensaje3 = (TextView) findViewById(R.id.mensaje_id3);
		list = (ListView) findViewById(R.id.list);

		//list = (ListView) findViewById(R.id.list);
		itemList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);
		if(savedInstanceState != null){
			itemList.addAll(savedInstanceState.getStringArrayList("List"));
			adapter.notifyDataSetChanged();
		}
		/* Uso de la clase LocationManager para obtener la localizacion del GPS */
		LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Localizacion Local = new Localizacion();
		Local.setMainActivity(this);
		mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
				(LocationListener) Local);

		//mensaje1.setText("Localizacion agregada");
		//mensaje2.setText("");

		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();





		list.setAdapter(adapter);

	}

	@Override
	public  void onSaveInstanceState(Bundle savedInstanceState){
		savedInstanceState.putStringArrayList("List", itemList);
		super.onSaveInstanceState(savedInstanceState);
	}


	/*
	public void setLocation(Location loc) {
		//Obtener la direccion de la calle a partir de la latitud y la longitud 
		if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
			try {
				Geocoder geocoder = new Geocoder(this, Locale.getDefault());
				List<Address> list = geocoder.getFromLocation(
						loc.getLatitude(), loc.getLongitude(), 1);
				if (!list.isEmpty()) {
					Address DirCalle = list.get(0);
					mensaje2.setText("Mi direccion es: \n"
							+ DirCalle.getAddressLine(0));

                    mensaje3.setText("hola");

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/


	public Action getIndexApiAction() {
		Thing object = new Thing.Builder()
				.setName("Main Page") // TODO: Define a title for the content shown.
				// TODO: Make sure this auto-generated URL is correct.
				.setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
				.build();
		return new Action.Builder(Action.TYPE_VIEW)
				.setObject(object)
				.setActionStatus(Action.STATUS_TYPE_COMPLETED)
				.build();
	}

	@Override
	public void onStart() {
		super.onStart();
		client.connect();
		AppIndex.AppIndexApi.start(client, getIndexApiAction());
	}

	@Override
	public void onStop() {
		super.onStop();
		AppIndex.AppIndexApi.end(client, getIndexApiAction());
		client.disconnect();
	}

	/* Aqui empieza la Clase Localizacion */
	public class Localizacion implements LocationListener {
		MainActivity mainActivity;

		public MainActivity getMainActivity() {
			return mainActivity;
		}

		public void setMainActivity(MainActivity mainActivity) {
			this.mainActivity = mainActivity;
		}

		@Override
		public void onLocationChanged(Location loc) {
			// Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
			// debido a la deteccion de un cambio de ubicacion

			loc.getLatitude();
			loc.getLongitude();
			String Text = "Mi ubicacion actual es: " + "\n Lat = "
					+ loc.getLatitude() + "\n Long = " + loc.getLongitude();
			String item_text =	loc.getLatitude() + "\n Long = " + loc.getLongitude();
			itemList.add(item_text);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}/* Fin de la clase localizacion */

}
