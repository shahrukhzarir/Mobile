package com.shahrukhzarir.lab4;

/**
 * Created by shahrukhzarir on 2017-10-17.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.R.id.message;
import static com.shahrukhzarir.lab4.R.attr.icon;

public class BatteryNotification extends BroadcastReceiver{
    NotificationCompat.Builder notification;
    private String msg = "";

    @Override
    public void onReceive(Context context, Intent intent) {

        //Get info
        int batteryStatus = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging =  batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING;
        if (isCharging)
            msg += "Charging\n";

        boolean batteryFull = batteryStatus == BatteryManager.BATTERY_STATUS_FULL;
        if (batteryFull)
            msg += "Full\n";

        boolean batteryDischarging = batteryStatus == BatteryManager.BATTERY_STATUS_DISCHARGING;
        if (batteryDischarging)
            msg += "Discharging\n";

        boolean batteryHealthGood = batteryStatus == BatteryManager.BATTERY_HEALTH_GOOD;
        boolean batteryOverHeated = batteryStatus == BatteryManager.BATTERY_HEALTH_OVERHEAT;
        boolean batteryOverVoltage = batteryStatus == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE;
        boolean batteryDead = batteryStatus == BatteryManager.BATTERY_HEALTH_DEAD;
        boolean batteryHealthUnknown = batteryStatus == BatteryManager.BATTERY_HEALTH_UNKNOWN;



        if (batteryHealthGood) {
            msg += "Level is Healthy\n";
        } else if (batteryOverHeated) {
            msg += "Battery is overheating\n";
        } else if (batteryOverVoltage) {
            msg += "Battery is overcharging\n";
        } else if (batteryDead) {
            msg += "Battery is dead";
        } else  {
            msg += "Uknown Battery Issue";
        }

        boolean chargingDeviceAC = batteryStatus == BatteryManager.BATTERY_PLUGGED_AC;

        boolean chargingDeviceUSB = batteryStatus == BatteryManager.BATTERY_PLUGGED_USB;

        int batteryTemp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 2);
        msg += "Phone temperature is: " + String.valueOf(batteryTemp) + "\n";


        if (chargingDeviceAC) {
            msg += "Phone is charging via AC\n";
        } else if  (chargingDeviceUSB) {
            msg += "Phone is charging via USB\n";
        } else {
            msg += "Phone is not plugged in";
        }

        notification = new NotificationCompat.Builder(context);
        notification.setAutoCancel(true);

        notification.setSmallIcon(R.drawable.pic);
        notification.setTicker("This is the ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Battery Report");
        notification.setContentText(msg);
        notification.setStyle(new NotificationCompat.BigTextStyle().bigText(msg));

        Intent notificationIntent = new Intent(context, BatteryNotification.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(201, notification.build());

    }
}