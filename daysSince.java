package dopemz.dayssince;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Calendar;


//***************************************************************************************MAIN***************************************************
public class daysSince extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 600000000;   //10000 mins
    //    private static final long START_TIME_IN_MILLIS = 60000;   //1 mins
//    private static final long START_TIME_IN_MILLIS = 6000;   //10 secs
    private CountDownTimer countTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private TextView days;
    private TextView hours;
    private TextView mins;
    private TextView secs;

    private TextView prac1;     //
    private TextView prac2;     //
    private TextView prac3;     //
    private TextView prac4;     //
    private TextView txt1;      //          need this for load 1
    private Button resettime;
    private Button newtime;
    private int seconds = 0;    //for updating clk
    private int minutes = 0;    //for updating clk
    private int hourss = 0;     //for updating clk
    private int dayss = 0;      //for updating clk
    private int years = 0;
    private Calendar myTime;
    private Calendar currentTime;
    private SharedPreferences mprefs;
    private SharedPreferences.Editor meditor;

    //--------------------------------------------------------------------------------CREATE------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_since);

        days = findViewById(R.id.dsince);
        hours = findViewById(R.id.hsince);
        mins = findViewById(R.id.msince);
        secs = findViewById(R.id.ssince);
        prac1 = findViewById(R.id.prac1);
        prac2 = findViewById(R.id.prac2);
        prac3 = findViewById(R.id.prac3);
        prac4 = findViewById(R.id.prac4);
        txt1 = findViewById(R.id.txt1);
        resettime = findViewById(R.id.resetbtn);
        newtime = findViewById(R.id.newbtn);

       // prac1.setText("00");
        //prac2.setText("00");
       // prac3.setText("00");
       // prac4.setText("00");

        txt1.setText("");

        mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        meditor = mprefs.edit();

///----------------------------------
        checkprefs();   //if there is data saved, run clock on app startup
//-----------------------------------

        resettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearData();
                days.setText("0");
                hours.setText("00");
                mins.setText("00");
                secs.setText("00");
                seconds = 0;
                minutes = 0;
                hourss = 0;
                dayss = 0;
                years = 0;
                pauseTimer();
                newtime.setText("NEW");
            }
        });

        newtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                    newtime.setText("NEW");
                } else {

                    myTime = Calendar.getInstance();
                    years = 0;
                    dayss = 0;
                    hourss = 0;
                    minutes = 0;
                    seconds = 0;

                    updateData();
                    startTimer();
                    newtime.setText("STOP");
                }
            }
        });
    }
//--------------------------------------------------------------------------------CREATE------------------------------------------------
//***************************************************************************************MAIN***************************************************

    public void checkprefs(){           //get values from mem, run if data is there
        String year1 = mprefs.getString(getString(R.string.year), "");
        String day1 = mprefs.getString(getString(R.string.day), "");
        String hour1 = mprefs.getString(getString(R.string.hour), "");
        String min1 = mprefs.getString(getString(R.string.min), "");
        String sec1 = mprefs.getString(getString(R.string.sec), "");
        String text1 = mprefs.getString(getString(R.string.mytext), "");

       // prac1.setText(day1);
       // prac2.setText(hour1);
       // prac3.setText(min1);
       // prac4.setText(sec1);

        txt1.setText(text1);

        if(year1 != "" || day1 != "" || hour1 != "" || min1 != ""  || sec1 != "" ){
            fillClk();
            //prac1.setText(Integer.toString(currentTime.get(Calendar.DAY_OF_YEAR)));
            startTimer();
            newtime.setText("STOP");
        }else{
            clearData();
            days.setText("0");
            hours.setText("00");
            mins.setText("00");
            secs.setText("00");
            txt1.setText("");
            seconds = 0;
            minutes = 0;
            hourss = 0;
            dayss = 0;
            years = 0;
        }
    }

    private void startTimer() {
        mTimerRunning = true;
            countTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {  //do this for X mins
                @Override
                public void onTick(long millisUntilFinished) {      //this is what loops every second when timer is on
                    updateTime();
                }
                @Override
                public void onFinish() {
                    mTimerRunning = false;
                }
            }.start();
    }

    private void pauseTimer(){          //hold values in txtboxes
        if (mTimerRunning){
            countTimer.cancel();
        }
        mTimerRunning = false;
    }
    private void updateTime(){          //update txtboxes

        fillClk();

        String secsleftformat = String.format("%02d", seconds);
        String minsleftformat = String.format("%02d", minutes);
        String hoursleftformat = String.format("%02d", hourss);
        secs.setText(secsleftformat);
        mins.setText(minsleftformat);
        hours.setText(hoursleftformat);
        days.setText(Integer.toString(dayss));
        savetxt();
    }
    private void updateData(){
        //get times from mytime, txt from txtbox, save it to xml in mem
        String year1 = Integer.toString(myTime.get(Calendar.YEAR));
        meditor.putString(getString(R.string.year), year1);
        meditor.commit();

        String day1 = Integer.toString(myTime.get(Calendar.DAY_OF_YEAR));
        meditor.putString(getString(R.string.day), day1);
        meditor.commit();

        String hour1 = Integer.toString(myTime.get(Calendar.HOUR_OF_DAY));
        meditor.putString(getString(R.string.hour), hour1);
        meditor.commit();

        String min1 = Integer.toString(myTime.get(Calendar.MINUTE));
        meditor.putString(getString(R.string.min), min1);
        meditor.commit();

        String sec1 = Integer.toString(myTime.get(Calendar.SECOND));
        meditor.putString(getString(R.string.sec), sec1);
        meditor.commit();
    }
    private void savetxt(){
        String text1 = txt1.getText().toString();
        meditor.putString(getString(R.string.mytext), text1);
        meditor.apply();
    }
    private void clearData(){                       //replace saved data in mem with ""
        meditor.putString(getString(R.string.year), "");
        meditor.commit();

        meditor.putString(getString(R.string.day), "");
        meditor.commit();

        meditor.putString(getString(R.string.hour), "");
        meditor.commit();

        meditor.putString(getString(R.string.min), "");
        meditor.commit();

        meditor.putString(getString(R.string.sec), "");
        meditor.commit();

        meditor.putString(getString(R.string.mytext), "");
        meditor.commit();
    }
    private void fillClk(){
        String year1 = mprefs.getString(getString(R.string.year), "");
        String day1 = mprefs.getString(getString(R.string.day), "");
        String hour1 = mprefs.getString(getString(R.string.hour), "");
        String min1 = mprefs.getString(getString(R.string.min), "");
        String sec1 = mprefs.getString(getString(R.string.sec), "");

        currentTime = Calendar.getInstance();
        years = currentTime.get(Calendar.YEAR) - Integer.parseInt(String.valueOf(year1));
        if(currentTime.get(Calendar.DAY_OF_YEAR) < Integer.parseInt(String.valueOf(day1))){
            years = years - 1;
            dayss = 365 + currentTime.get(Calendar.DAY_OF_YEAR) - Integer.parseInt(String.valueOf(day1));
        }else{
            dayss = currentTime.get(Calendar.DAY_OF_YEAR) - Integer.parseInt(String.valueOf(day1));
        }
        if(currentTime.get(Calendar.HOUR_OF_DAY) < Integer.parseInt(String.valueOf(hour1))){
            dayss = dayss - 1;
            hourss = 24 + currentTime.get(Calendar.HOUR_OF_DAY) - Integer.parseInt(String.valueOf(hour1));
        }else{
            hourss = currentTime.get(Calendar.HOUR_OF_DAY) - Integer.parseInt(String.valueOf(hour1));
        }
        if(currentTime.get(Calendar.MINUTE) < Integer.parseInt(String.valueOf(min1))){
            hourss = hourss - 1;
            minutes = 60 + currentTime.get(Calendar.MINUTE) - Integer.parseInt(String.valueOf(min1));
        }else{
            minutes = currentTime.get(Calendar.MINUTE) - Integer.parseInt(String.valueOf(min1));
        }
        if(currentTime.get(Calendar.SECOND) < Integer.parseInt(String.valueOf(sec1))){
            minutes = minutes - 1;
            seconds = 60 + currentTime.get(Calendar.SECOND) - Integer.parseInt(String.valueOf(sec1));
        }else{
            seconds = currentTime.get(Calendar.SECOND) - Integer.parseInt(String.valueOf(sec1));
        }

    }

}



