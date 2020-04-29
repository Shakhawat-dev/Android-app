package com.metacoders.home;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;


    public class MyNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        private String activityToBeOpened;
        //TODO ADD here (NOT SURE )
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;

            try {
               activityToBeOpened = data.getString("page");
            }
            catch (JSONException e ){

                e.printStackTrace();
            }

            if(activityToBeOpened.equals("PostsListActivity")){
                Intent intent = new Intent(MyApplication.getContext(), PostsListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.getContext().startActivity(intent);
            }




            //While sending a Push notification from OneSignal dashboard
            // you can send an addtional data named "activityToBeOpened" and retrieve the value of it and do necessary operation
            //If key is "activityToBeOpened" and value is "AnotherActivity", then when a user clicks
            //on the notification, AnotherActivity will be opened.
            //Else, if we have not set any additional data MainActivity is opened.
            /*
            if (data != null) {
                activityToBeOpened = data.optString("activityToBeOpened", null);

                if (activityToBeOpened != null && activityToBeOpened.equals("PostsListActivity")) {
                    Log.i("OneSignalExample", "customkey set with value: " + activityToBeOpened);
                    Intent intent = new Intent(MyApplication.getContext(), PostsListActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getContext().startActivity(intent);
                } else if (activityToBeOpened != null && activityToBeOpened.equals("MainActivity")) {
                    Log.i("OneSignalExample", "customkey set with value: " + activityToBeOpened);
                    Intent intent = new Intent(MyApplication.getContext(), Home_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getContext().startActivity(intent);
                } else {
                    Intent intent = new Intent(MyApplication.getContext(), Home_Activity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                    MyApplication.getContext().startActivity(intent);
                }


            }
 */
            //If we send notification with action buttons we need to specidy the button id's and retrieve it to
            //do the necessary operation.

        }
}
