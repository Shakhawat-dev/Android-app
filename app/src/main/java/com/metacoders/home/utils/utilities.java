package com.metacoders.home.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.metacoders.home.Home_Activity;
import com.metacoders.home.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;

public class utilities {

    public int calculateDayCount(String QueryDate , String todayDate) {
        //Setting dates
        String dayDifference = "0.00" ;
        try {
            //Dates to compare
            String CurrentDate=  todayDate;
            String FinalDate=  QueryDate;

            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");

            //Setting dates
            date1 = dates.parse(CurrentDate);
            date2 = dates.parse(FinalDate);

            //Comparing dates
            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            //Convert long to String
            dayDifference = Long.toString(differenceDates);

            Log.e("HERE","HERE: " + dayDifference);
        }

        catch (Exception exception) {
            Log.e("DIDN'T WORK", "exception " + exception);
            dayDifference = "0.00" ;
        }



        return  Integer.parseInt(dayDifference) ;
    }

    //  8o8Rnfu2GxQ68bubfTKLugxnIqo2 test uid


    public  void TriggerAlertDialougeForPurchage(final Context context)
    {
        new PrettyDialog(context)
                .setIcon(R.drawable.logo)
                .setTitle("PrettyDialog Title")
                .setMessage("PrettyDialog Message")
                .addButton(
                        "OK",     // button text
                        R.color.pdlg_color_white,  // button text color
                        R.color.pdlg_color_green,  // button background color
                        new PrettyDialogCallback() {  // button OnClick listener
                            @Override
                            public void onClick() {
                                // Do what you gotta do
                                Toast.makeText(context ,"Clicked", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                )
                .show();
    }


}

