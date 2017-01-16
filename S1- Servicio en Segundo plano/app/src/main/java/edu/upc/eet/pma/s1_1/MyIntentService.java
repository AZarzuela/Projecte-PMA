package edu.upc.eet.pma.s1_1;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("5sec ", String.valueOf("OnHandleIntet"));
    }

    public void setAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmBcstRec = new Intent(context, MyAlarmBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, alarmBcstRec, 0);
        // Replace the settings with yours here...
        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + MyAlarmBroadcastReceiver.INTERVAL, MyAlarmBroadcastReceiver.INTERVAL, pi);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this,"Service Started...",Toast.LENGTH_SHORT).show();
        setAlarm(this);
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast toast = Toast.makeText(this,"Stopped 5 sec",Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("5sec ", String.valueOf("Oncreate"));
    }

}
