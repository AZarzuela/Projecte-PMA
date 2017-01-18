package com.mfast.appgps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mfast.appgps.MainActivity;
import com.mfast.appgps.R;
import com.mfast.appgps.MainActivity.Localizacion;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView mensaje1;
	TextView mensaje2;

	private ListView list;
	private ArrayList<String> itemList;
	private ArrayAdapter<String> adapter;

    private ArrayList<String> listposicions = new ArrayList<String>();
	private EditText edit_item;

    private static final String FILENAME = "pos.txt";


	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
	private GoogleApiClient client;


	private ListView listpos;
    private TextView mensaje3;

	public MainActivity() {
	}


	public void grabar(String text) throws IOException {
		try {
			OutputStream f0 = new FileOutputStream("file1.txt");
			f0.write(12);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mensaje1 = (TextView) findViewById(R.id.mensaje_id);
		mensaje2 = (TextView) findViewById(R.id.mensaje_id2);
        mensaje3 = (TextView) findViewById(R.id.mensaje_id3);

		list = (ListView) findViewById(R.id.list);

		edit_item = (EditText) findViewById(R.id.edit_item);

		//list = (ListView) findViewById(R.id.list);
		itemList = new ArrayList<String>();

		/* Uso de la clase LocationManager para obtener la localizacion del GPS */
		LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Localizacion Local = new Localizacion();
		Local.setMainActivity(this);
		mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
				(LocationListener) Local);

		//mensaje1.setText("Localizacion agregada");
		//mensaje2.setText("");
		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

		itemList = new ArrayList<String>();
		itemList.add("patatas");
		itemList.add("zanahorias");

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemList);

		list.setAdapter(adapter);

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

	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */
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

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
		client.connect();
		AppIndex.AppIndexApi.start(client, getIndexApiAction());
	}

	@Override
	public void onStop() {
		super.onStop();

		// ATTENTION: This was auto-generated to implement the App Indexing API.
		// See https://g.co/AppIndexing/AndroidStudio for more information.
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
			//mensaje1.setText(Text);
			//this.mainActivity.setLocation(loc);
			// Crea y escribe en un fichero el "texto de prueba" y "texto de prueba2"

			/*
			try {
				OutputStreamWriter fout =
						new OutputStreamWriter(
								openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));

				fout.write("Texto de prueba.");
				fout.write("Texto de prueba2.");
				fout.close();
			} catch (Exception ex) {
				Log.e("Ficheros", "Error al escribir fichero a memoria interna");
			}
			//lee del archivo prueba_int.txt i lo saca en un textview
			String texto = null;
			try {
				BufferedReader fin =
						new BufferedReader(
								new InputStreamReader(
										openFileInput("prueba_int.txt")));

				texto = fin.readLine();
				fin.close();

			} catch (Exception ex) {
				Log.e("Ficheros", "Error al leer fichero desde memoria interna");
			}*/
			//mensaje3.setText(texto);
			//Log.i(file1.txt, String.valueOf(itemList));

			String item_text =	loc.getLatitude() + "\n Long = " + loc.getLongitude();
			itemList.add(item_text);
			adapter.notifyDataSetChanged();




		}



		@Override
		public void onProviderDisabled(String provider) {
			//Este metodo se ejecuta cuando el GPS es desactivado
			//mensaje1.setText("GPS Desactivado");
		}

		@Override
		public void onProviderEnabled(String provider) {
			// Este metodo se ejecuta cuando el GPS es activado
			//mensaje1.setText("GPS Activado");
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// Este metodo se ejecuta cada vez que se detecta un cambio en el
			// status del proveedor de localizacion (GPS)
			// Los diferentes Status son:
			// OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
			// TEMPORARILY_UNAVAILABLE -> Temporalmente no disponible pero se
			// espera que este disponible en breve
			// AVAILABLE -> Disponible
		}



	}/* Fin de la clase localizacion */

}
