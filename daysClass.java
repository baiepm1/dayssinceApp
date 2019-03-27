package dopemz.dayssince;

import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class daysClass {
    private TextView txt, dtxt, htxt, mtxt, stxt;
    private Button resetbtn;
    private Button newbtn;
    private int days, hours, mins, secs;
    private Calendar mytime;

    public daysClass(){
        txt = null;
        dtxt = null;
        htxt = null;
        mtxt = null;
        stxt = null;
        resetbtn = null;
        newbtn = null;
        days = 8;
        hours = 8;
        mins = 8;
        secs = 8;
    }
//constructor
    public daysClass(TextView text, TextView dtext, TextView htext, TextView mtext, TextView stext, Button rbtn, Button nbtn, int day, int hour, int min, int sec){
            txt = text;
            dtxt = dtext;
            htxt = htext;
            mtxt = mtext;
            stxt = stext;
            resetbtn = rbtn;
            newbtn = nbtn;
            days = day;
            hours = hour;
            mins = min;
            secs = sec;
    }
    public void setTime(){
            dtxt.setText(Integer.toString(days));
            htxt.setText(Integer.toString(hours));
            mtxt.setText(Integer.toString(mins));
            stxt.setText(Integer.toString(secs));
    }
    public void updatenums(int day, int hour, int min, int sec){
        days = day;
        hours = hour;
        mins = min;
        secs = sec;
    }
}
