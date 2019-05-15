package dopemz.dayssince;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Calendar;


//***************************************************************************************MAIN***************************************************
public class daysSince extends AppCompatActivity implements View.OnClickListener, exampledialog.exdialoglistener{

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
    private TextView currnum;
    private TextView prac1;     //
    private TextView prac2;     //
    private TextView prac3;     //
    private TextView prac4;     //
    private TextView txt1;      //          need this for load 1
    private Button resettime;
    private Button newtime;
    private Button add;
    private int seconds = 0;    //for updating clk
    private int minutes = 0;    //for updating clk
    private int hourss = 0;     //for updating clk
    private int dayss = 0;      //for updating clk
    private int years = 0;
    private Calendar myTime;
    private Calendar currentTime;
    private SharedPreferences mprefs;
    private SharedPreferences.Editor meditor;
    //private int numOfTimers = 0;
    private int pracnums = 0;
    private ScrollView scroll;
    private LinearLayout layout1;
    private LinearLayout layout2;

    private daysClass[] mme = new daysClass[50];
    //mme[0] = new daysClass();
    private pracClass[] prac = new pracClass[10];

    private int testingnum = 0;
    private TextView practextpop;
    private Button pracbuttonpop;

    //--------------------------------------------------------------------------------ONCREATE------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.days_since);

        scroll = findViewById(R.id.myscroll);
        layout1 = findViewById(R.id.mylayout1);
        layout2 = findViewById(R.id.mylayout2);
        add = findViewById(R.id.add);
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
        currnum = findViewById(R.id.currentnum);


       // daysClass[] mme = new daysClass[50];
        //mme[0] = new daysClass(txt1, prac1, prac2, prac3, prac4, resettime, newtime, dayss, hourss, minutes, seconds);
        mme[0] = new daysClass(txt1, prac1, prac2, prac3, prac4, resettime, newtime, dayss, hourss, minutes, seconds);


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
/*
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //numOfTimers++;
                //currnum.setText(Integer.toString(numOfTimers));
                pracnums++;
                currnum.setText(Integer.toString(pracnums));
                int[] gay = new int[100];
                gay[pracnums] = pracnums;

                TextView practext = new TextView(daysSince.this);
                practext.setText(Integer.toString(pracnums));

                Button pracbtn = new Button(daysSince.this);
                pracbtn.setOnClickListener(this);
                pracbtn.setText("edit" + Integer.toString(pracnums));
                pracbtn.setWidth(200);
                pracbtn.setId(pracnums);
                prac[pracnums] = new pracClass(pracnums, pracbtn);

                LinearLayout horz = new LinearLayout(daysSince.this);
                horz.setHorizontalGravity(1);

                //prac[pracnums] = new pracClass();
                horz.addView(practext);
                horz.addView(pracbtn);
                layout1.addView(horz);

            }
        });
*/

/*
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
*/

/*
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
        */

        add.setOnClickListener(this);
        resettime.setOnClickListener(this);
        newtime.setOnClickListener(this);
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
        String timernum = mprefs.getString(getString(R.string.num), "0");

       // prac1.setText(day1);
       // prac2.setText(hour1);
       // prac3.setText(min1);
       // prac4.setText(sec1);

        txt1.setText(text1);
       // numOfTimers = Integer.parseInt(timernum);
       // currnum.setText(Integer.toString(numOfTimers));

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
        mme[0].updatenums(dayss, hourss, minutes, seconds);
        mme[0].setTime();
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

        //String timernum = Integer.toString(numOfTimers);
       // meditor.putString(getString(R.string.num), timernum);
        //meditor.apply();
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

        //currnum.setText(Integer.toString(numOfTimers));

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


    //----------------------------------functionality for all the buttons-------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //edit buttons for all the different timers: open the details with openDialog(). pass details into dialog
            case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                openDialog();
                Toast.makeText(this, Integer.toString(prac[v.getId()].getnum()), Toast.LENGTH_SHORT).show();
                break;
            //"add new" button: adds a new timer with details and edit button
            case R.id.add:
                pracnums++;
                currnum.setText(Integer.toString(pracnums));
               // int[] gay = new int[100];
               // gay[pracnums] = pracnums;

                TextView practext = new TextView(daysSince.this);
                practext.setText(Integer.toString(pracnums));

                Button pracbtn = new Button(daysSince.this);
                pracbtn.setOnClickListener(this);
                pracbtn.setText("edit" + Integer.toString(pracnums));
                pracbtn.setWidth(200);
                pracbtn.setId(pracnums);
                pracbtn.setOnClickListener(this);
                prac[pracnums] = new pracClass(pracnums, pracbtn);

                LinearLayout horz = new LinearLayout(daysSince.this);
                horz.setHorizontalGravity(1);
                //prac[pracnums] = new pracClass();
                horz.addView(practext);
                horz.addView(pracbtn);

                layout1.addView(horz);
                break;
            //    should move into dialog window
            case R.id.resetbtn:
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
                break;
            //    should move into dialog window
            case R.id.newbtn:
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
                    break;

                }
        }
    }

    private void openDialog() {
        exampledialog mydialog = new exampledialog();
        mydialog.show(getSupportFragmentManager(), "edittimer");
    }

    @Override
    public void applytext(String num) {
        currnum.setText(num);
    }
}



