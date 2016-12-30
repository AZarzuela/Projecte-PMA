package edu.upc.eet.pma.s1_1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyAlarmBroadcastReceiver extends BroadcastReceiver {

    public static final long INTERVAL= 1000 * 5;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MyIntentService.class);
        context.startService(service);
    }
}
