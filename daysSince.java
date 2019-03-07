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

    public static final String SHARED_PREFS = "SHAREDPrefs";
    public static final String SEC_PREFS = "SECPrefs";
    public static final String MIN_PREFS = "MINPrefs";
    public static final String HOUR_PREFS = "HOURPrefs";
    public static final String DAY_PREFS = "DAYPrefs";
    public static final String YEAR_PREFS = "YEARPrefs";
    public static final String myyears = "";
    public static final String mydays = "";
    public static final String myhours = "";
    public static final String mymins = "";
    public static final String mysecs = "";
    public static final String mytxt = "";  //need this for load 2

    private String text1;                   //need this for load 3
    private String helloseconds;
    private String hellomins;
    private String hellohour;
    private String helloday;
    private String helloyear;






    private SharedPreferences mprefs;
    private SharedPreferences.Editor meditor;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_since);

        currentTime = Calendar.getInstance();

        days = findViewById(R.id.dsince);
        hours = findViewById(R.id.hsince);
        mins = findViewById(R.id.msince);
        secs = findViewById(R.id.ssince);
        prac1 = findViewById(R.id.prac1);
        prac2 = findViewById(R.id.prac2);
        prac3 = findViewById(R.id.prac3);
        prac4 = findViewById(R.id.prac4);
        txt1 = findViewById(R.id.txt1);

        prac1.setText("00");
        prac2.setText("00");
        prac3.setText("00");
        prac4.setText("00");

        resettime = findViewById(R.id.resetbtn);
        newtime = findViewById(R.id.newbtn);

        mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        meditor = mprefs.edit();


        checkprefs();

        meditor.putString(mysecs, secs.getText().toString());
        meditor.commit();
        meditor.putString(mydays, days.getText().toString());
        meditor.commit();

        
        resettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

        newtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    myTime = Calendar.getInstance();
/*
                        years = myTime.get(Calendar.YEAR) - myTime.get(Calendar.YEAR);
                        dayss = myTime.get(Calendar.DATE) - myTime.get(Calendar.DATE) + (365 * years);   //if difference in years is zero, add nothing. else do math to add days
                        hourss =  (myTime.get(Calendar.HOUR_OF_DAY) - myTime.get(Calendar.HOUR_OF_DAY) + (24 * dayss)) % 24;
                        minutes = (myTime.get(Calendar.MINUTE) - myTime.get(Calendar.MINUTE) + (60 * hourss)) % 60;
                        seconds = (myTime.get(Calendar.SECOND) - myTime.get(Calendar.SECOND) + (60 * minutes)) % 60;   //%60 so value never overflow 60 or negative
*/

                    years = myTime.get(Calendar.YEAR) - 2018;
                    dayss = myTime.get(Calendar.DATE) - 42 + (365 * years);   //if difference in years is zero, add nothing. else do math to add days
                    hourss = (myTime.get(Calendar.HOUR_OF_DAY) - 9 + (24 * dayss)) % 24;
                    minutes = (myTime.get(Calendar.MINUTE) - 20 + (60 * hourss)) % 60;
                    seconds = (myTime.get(Calendar.SECOND) - 16 + (60 * minutes)) % 60;   //%60 so value never overflow 60 or negative

                    //updateTime();
                    saveData();
                    startTimer();
                }
            }
        });


        //loadData();
        //updateData();
    }


    public void checkprefs(){
        helloday = mprefs.getString(mydays, "");
        helloseconds = mprefs.getString(mysecs, "");

        prac1.setText(helloday);
        prac2.setText(helloseconds);


    }

    public void saveData() {
      /* saveSec();
       saveMin();
       saveHour();
       saveDay();*/
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mysecs, secs.getText().toString());
        editor.apply();
        editor.putString(mymins, mins.getText().toString());
        editor.apply();
        editor.putString(myhours, hours.getText().toString());
        editor.apply();
        editor.putString(mydays, days.getText().toString());
        editor.apply();
    }/*
        public void saveData(){
       saveSec();
       saveMin();
       saveHour();
       saveDay();
}

    public void saveSec(){
        SharedPreferences sharedPreferences = getSharedPreferences(SEC_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mysecs, secs.getText().toString());
        editor.commit();
    }
    public void saveMin(){
        SharedPreferences sharedPreferences = getSharedPreferences(MIN_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mymins, mins.getText().toString());
        editor.commit();
    }
    public void saveHour(){
        SharedPreferences sharedPreferences = getSharedPreferences(HOUR_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(myhours, hours.getText().toString());
        editor.commit();
    }
    public void saveDay(){
        SharedPreferences sharedPreferences = getSharedPreferences(DAY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mydays, days.getText().toString());
        editor.commit();
    }
    public void saveYear(){
        SharedPreferences sharedPreferences = getSharedPreferences(YEAR_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mysecs, secs.getText().toString());
        editor.commit();
    }
*/
    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        //years = sharedPreferences.getInt(myyears, -1);
        helloday = sharedPreferences.getString(mydays, "");
        hellohour = sharedPreferences.getString(myhours, "");
        hellomins = sharedPreferences.getString(mymins, "");
        helloseconds = sharedPreferences.getString(mysecs, "");
        //text1 = sharedPreferences.getString(mysecs, "");


    }
    public void updateData(){
       // txt1.setText(helloseconds);
        prac4.setText(helloseconds);
        prac3.setText(hellomins);
        prac2.setText(hellohour);
        prac1.setText(helloday);
    }

    private void startTimer() {
        mTimerRunning = true;
            countTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {  //do this for X mins
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTime();
                    seconds = seconds + 1;
                }
                @Override
                public void onFinish() {
                    mTimerRunning = false;
                }
            }.start();
    }


    private void pauseTimer(){
        if (mTimerRunning){
            countTimer.cancel();
        }
        mTimerRunning = false;
    }
    private void updateTime(){
        if (Integer.parseInt(String.valueOf(secs.getText())) >= 59) {
            seconds = 0;
            minutes = minutes + 1;
            if (Integer.parseInt(String.valueOf(mins.getText())) >= 59) {
                minutes = 0;
                hourss = hourss + 1;
                if (Integer.parseInt(String.valueOf(hours.getText())) >= 23) {
                    hourss = 0;
                    dayss = dayss + 1;
                }
            }
        }

        String secsleftformat = String.format("%02d", seconds);
        String minsleftformat = String.format("%02d", minutes);
        String hoursleftformat = String.format("%02d", hourss);

        secs.setText(secsleftformat);
        mins.setText(minsleftformat);
        hours.setText(hoursleftformat);
        days.setText(Integer.toString(dayss));

        //prac1.setText(Integer.toString(myTime.get(Calendar.SECOND)));
        saveData();

/*
        meditor.putString(mysecs, secs.getText().toString());
        meditor.commit();
        meditor.putString(mydays, days.getText().toString());
        meditor.commit();*/
    }

}
