package com.easygo.rbcdev.easygo.Utility;

import android.app.Activity;
import android.text.format.DateUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by sjha on 15-10-06.
 */
public class DateUtility {


    /**
     *
     * @param duration : time in milliseconds
     * @return String: formatted time in minutes
     */
    public static String formatDurationInMinutes(long duration) {
        NumberFormat formatter = new DecimalFormat("#0");
        return formatter.format(duration / (double) DateUtils.MINUTE_IN_MILLIS);
    }

    /**
     *
     * @param duration: time in milliseconds
     * @return String: formatted time in hours
     */
    public static String formatDurationInHours(long duration) {
        NumberFormat formatter = new DecimalFormat("#0.0");
        return formatter.format(duration / (double)DateUtils.HOUR_IN_MILLIS);
    }

    /**
     *
     * @param cal Calendar object
     * @return String :(Day Month date of month year/ eg: Monday Feb 21, 1989)
     */
    public static String formatCalendarDateFull(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM d, yyyy", Locale.US);
        return sdf.format(cal.getTime());
    }


    /**
     *
     * @param cal Calendar object
     * @return String : (returns simple date format 	eg: 2001-07-04T12:08:56.235-0700)
     */
    public static String formatCalendarDateTime(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.US);
        return sdf.format(cal.getTime());
    }

    /**
     *
     * @param cal Calendar Object
     * @return String : time  in hours and mins with AM/PM (8.04 AM)
     */
    //change for uppercase or lowercase for start and end time
    public static String formatCalendarTime(Calendar cal) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.US);
        return sdf.format(cal.getTime()).toUpperCase(Locale.US);
    }


    /**
     *
     * @param date Date object
     * @return String:  simple date format eg: Feb 21, 1989
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.US);
        return sdf.format(date);
    }


    /**
     *
     * @param strDate Type String (yyyy-MM-dd'T'HH:mm:ss.SSSZZ)
     * @return String : eg - (Feb 21, 1989)
     */
    public static String formatStringDate (String strDate) {

        try {
            SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZ", Locale.US);
            Date date = input.parse(strDate);
            SimpleDateFormat output = new SimpleDateFormat("MMM d, yyyy", Locale.US);
            return output.format(date);
        } catch (Exception e) {
            return strDate;
        }
    }

    //Added "EEEE" to get the day of the provided date

    /**
     *
     * @param date Type Date
     * @return String: eg- Wednesday Sep 30, 2015
     */
    public static String formatDateWithDay(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM d, yyyy", Locale.US);
        return sdf.format(date);
    }


    /**
     *
     * @param date Date Object
     * @return String: eg- September 29, 2015
     */
    public static String formatDateLong(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        return sdf.format(date);
    }


    /**
     *
     * @param date Date Object
     * @return Wednesday Sep 29, 2015
     */
    public static String formatDateFull(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMM d, yyyy", Locale.US);
        return sdf.format(date);
    }

    /**
     *
     * @param date Date Object
     * @return String: 8:45 AM
     */
    public static String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a ", Locale.US);
        return sdf.format(date).toUpperCase(Locale.US);
    }

    /**
     *
     * @return current date with time
     */
    public static Calendar getToday() {
        Calendar today = new GregorianCalendar();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return today;
    }

    /**
     *
     * @param day Calendar Object
     * @return Calendar Object: Returns end of the day for today
     */
    public static Calendar getEndOfDay(Calendar day) {
        Calendar eod = (Calendar)day.clone();
        eod.set(Calendar.HOUR_OF_DAY, 0);
        eod.set(Calendar.MINUTE, 0);
        eod.set(Calendar.SECOND, 0);
        eod.set(Calendar.MILLISECOND, 0);
        eod.add(Calendar.DATE, 1);
        eod.add(Calendar.MILLISECOND, -1);
        return eod;
    }

    /**
     *
     * @return Calendar Object of start of the day Tomorrow
     */
    public static Calendar getTomorrow() {
        Calendar tomorrow = new GregorianCalendar();
        tomorrow.set(Calendar.HOUR_OF_DAY, 0);
        tomorrow.set(Calendar.MINUTE, 0);
        tomorrow.set(Calendar.SECOND, 0);
        tomorrow.set(Calendar.MILLISECOND, 0);
        tomorrow.add(Calendar.DATE, 1);
        return tomorrow;
    }

    /**
     *
     * @return String: tomorrow's date of format 2015-02-21
     */
    public static String getTomorrowString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Calendar calendar = getToday();
        calendar.add(Calendar.DATE, 1);
        return sdf.format(calendar.getTime());
    }

    /**
     *
     * @return String:
     */
    public static String getTodayString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return sdf.format(getToday().getTime());
    }

    /**
     *
     * @return Current date with time
     */
    public static Calendar getNow() {
        return Calendar.getInstance();
    }

    public static Calendar getNextHalfHour() {
        Calendar now = getNow();
        int currentMinute = now.get(Calendar.MINUTE);

        if (currentMinute > 30) {
            now.add(Calendar.MINUTE, 60 - currentMinute);
        } else if (currentMinute > 0) {
            now.add(Calendar.MINUTE, 30 - currentMinute);
        }

        now.set(Calendar.SECOND, 0);
        return now;
    }

    public static boolean isValidEndTime(Calendar startTime, Calendar endTime) {
        boolean valid = true;
        if(isSameDay(startTime,endTime)) {
            if(endTime.getTime().compareTo(startTime.getTime()) < 1) {
                valid = false;
            }
        }
        return valid;
    }

    /**
     *
     * @param minDate : Calendar Object: The Base Object we are comparing to
     * @param startTime: Calendar Object: The Object we are comparing to minDate
     * @return Boolean: if the day is same or not
     */
    public static boolean isSameDay(Calendar minDate, Calendar startTime) {
        boolean sameDay = false;
        if(minDate.get(Calendar.DAY_OF_MONTH) == startTime.get(Calendar.DAY_OF_MONTH) &&
                minDate.get(Calendar.MONTH) == startTime.get(Calendar.MONTH) &&
                minDate.get(Calendar.YEAR) == startTime.get(Calendar.YEAR)) {
            sameDay = true;
        }
        return sameDay;
    }



    public static boolean validateDateRange(Activity activity, Calendar startDate, Calendar endDate) {

        Calendar today = getNow();

        if (compareTo(today, startDate) > 0) {
            //UiUtility.createAlertDialog(activity, null, R.string.errTitle, R.string.errInvalidDate);
            return false;
        } else if (compareTo(startDate, endDate) >= 0) {
            //UiUtility.createAlertDialog(activity, null, R.string.errTitle, R.string.errInvalidDate);
            return false;
        }

        return true;
    }

    public static int compareTo (Calendar cal1, Calendar cal2) {

        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        return cal1.compareTo(cal2);

    }

    public static Calendar createCalendar (int year, int month, int day) {
        return new GregorianCalendar(year, month, day, 0, 0 ,0);
    }




}
