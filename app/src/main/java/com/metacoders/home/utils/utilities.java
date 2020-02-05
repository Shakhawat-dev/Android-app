package com.metacoders.home.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class utilities {

    public int calculateDayCount(String QueryDate , String todayDate) {
        //Setting dates
        String dayDifference = "0.00" ;
        try {
            //Dates to compare
            String CurrentDate=  QueryDate;
            String FinalDate=  todayDate;

            Date date1;
            Date date2;

            SimpleDateFormat dates = new SimpleDateFormat("dd-mm-yyyy");

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


}

