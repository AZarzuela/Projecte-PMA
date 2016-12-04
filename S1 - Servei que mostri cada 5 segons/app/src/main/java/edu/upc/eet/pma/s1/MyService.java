package edu.upc.eet.pma.s1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private Timer timer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //TODO: S1 - Fer un servei que mostri Log.i(...) - [Android Monitor], cada 5 segons.

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        class SubTimer extends TimerTask{
            public void run(){
                    Log.i("5-sec","ok");
            }
        }
        timer = new Timer();
        SubTimer sub = new SubTimer();
        timer.scheduleAtFixedRate(sub, 0, 1000*5);

        Toast.makeText(this,"Service Started",Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service Destroyed",Toast.LENGTH_LONG).show();
        timer.cancel();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
