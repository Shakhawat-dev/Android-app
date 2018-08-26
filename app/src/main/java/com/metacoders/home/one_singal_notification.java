package com.metacoders.home;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;

import java.math.BigInteger;

public class one_singal_notification  {


/*
    //public class one_singal_notification extends NotificationExtenderService
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {

        OverrideSettings overrideSettings = new OverrideSettings();
        overrideSettings.extender = new NotificationCompat.Extender() {
            @Override
            public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                // Sets the background notification color to Green on Android 5.0+ devices.
                return builder.setColor(new BigInteger("FF00FF00", 16).intValue());
            }
        };
        OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
        Log.d("OneSignalExample", "Notification displayed with id: " + displayedResult.androidNotificationId);
        return true ;
    }
    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        onTaskRemoved(intent);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(@NonNull Intent intent) {

        throw new UnsupportedOperationException("Not Yet Done");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartservirce = new Intent(getApplicationContext(), this.getClass()) ;
        restartservirce.setPackage(getPackageName());
        startService(restartservirce);
        super.onTaskRemoved(rootIntent);
    }
    */
}
