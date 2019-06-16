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
    private Button myreset;
    private Button mynew;
//1
    private int seconds1 = 0;    //for updating clk
    private int minutes1 = 0;    //for updating clk
    private int hourss1 = 0;     //for updating clk
    private int dayss1 = 0;      //for updating clk
    private int years1 = 0;
//2
    private int seconds2 = 0;    //for updating clk
    private int minutes2 = 0;    //for updating clk
    private int hourss2 = 0;     //for updating clk
    private int dayss2 = 0;      //for updating clk
    private int years2 = 0;
//3
    private int seconds3 = 0;    //for updating clk
    private int minutes3 = 0;    //for updating clk
    private int hourss3 = 0;     //for updating clk
    private int dayss3 = 0;      //for updating clk
    private int years3 = 0;

    private Calendar myTime;
    private Calendar myTime2;
    private Calendar myTime3;
    private Calendar currentTime;
    private SharedPreferences mprefs;
    private SharedPreferences.Editor meditor;
    //private int numOfTimers = 0;
    private int pracnums = 0;
    private ScrollView scroll;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout[] horz = new LinearLayout[50];

    private Button btna;

    private Button btna2;

    private Button btna3;



    private daysClass[] mme = new daysClass[50];
    //mme[0] = new daysClass();
    private pracClass[] prac = new pracClass[50];

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
        mme[0] = new daysClass(txt1, prac1, prac2, prac3, prac4, resettime, newtime, dayss1, hourss1, minutes1, seconds1);

        int gayboi = 800;
        btna = new Button(this);
        btna.setId(gayboi);
        btna2 = new Button(this);
        btna2.setId(gayboi + 1);
        btna3 = new Button(this);
        btna3.setId(gayboi + 2);

       // prac1.setText("00");
        //prac2.setText("00");
       // prac3.setText("00");
       // prac4.setText("00");

        txt1.setText("");

        mprefs = PreferenceManager.getDefaultSharedPreferences(this);
        meditor = mprefs.edit();

        //for(int i = 0; i < 50; i++){
        //    prac[i] = new pracClass();
        //}


        //pracnums = 0;

///----------------------------------
        checkprefs();   //if there is data saved, run clock on app startup
//-----------------------------------

//myreset.setOnClickListener(this);
//mynew.setOnClickListener(this);
        add.setOnClickListener(this);
        resettime.setOnClickListener(this);
        newtime.setOnClickListener(this);
        startTimer();
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

        String year2 = mprefs.getString(getString(R.string.year2), "");
        String day2 = mprefs.getString(getString(R.string.day2), "");
        String hour2 = mprefs.getString(getString(R.string.hour2), "");
        String min2 = mprefs.getString(getString(R.string.min2), "");
        String sec2 = mprefs.getString(getString(R.string.sec2), "");
        String text2 = mprefs.getString(getString(R.string.mytext2), "");

        String year3 = mprefs.getString(getString(R.string.year3), "");
        String day3 = mprefs.getString(getString(R.string.day3), "");
        String hour3 = mprefs.getString(getString(R.string.hour3), "");
        String min3 = mprefs.getString(getString(R.string.min3), "");
        String sec3 = mprefs.getString(getString(R.string.sec3), "");
        String text3 = mprefs.getString(getString(R.string.mytext3), "");


        txt1.setText(text1);
        //pracnums = Integer.parseInt(timernum);

        //pracnums = 0;

        /*if (pracnums > 0){
            for(int i = 1; i <= pracnums; i++){
                newlayout(i);
            }
        }*/
        //numOfTimers = Integer.parseInt(timernum);
       // currnum.setText(Integer.toString(numOfTimers));
/*
        if(year1 != "" || day1 != "" || hour1 != "" || min1 != ""  || sec1 != "" ){
            fillClk();
            //prac1.setText(Integer.toString(currentTime.get(Calendar.DAY_OF_YEAR)));
            //startTimer();
            newtime.setText("STOP");
        }else{
            clearData(1);
            days.setText("0");
            hours.setText("00");
            mins.setText("00");
            secs.setText("00");
            txt1.setText("");
            seconds1 = 0;
            minutes1 = 0;
            hourss1 = 0;
            dayss1 = 0;
            years1 = 0;
        }*/
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
        mme[0].updatenums(dayss1, hourss1, minutes1, seconds1);
        mme[0].setTime();
        String secsleftformat = String.format("%02d", seconds1);
        String minsleftformat = String.format("%02d", minutes1);
        String hoursleftformat = String.format("%02d", hourss1);
        secs.setText(secsleftformat);
        mins.setText(minsleftformat);
        hours.setText(hoursleftformat);
        days.setText(Integer.toString(dayss1));
        savetxt();

    }
    private void updateData(){
        //get times from mytime, save it to xml in mem
//1
        myTime = Calendar.getInstance();

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
        /*
//2
        String year2 = Integer.toString(myTime2.get(Calendar.YEAR));
        meditor.putString(getString(R.string.year2), year2);
        meditor.commit();

        String day2 = Integer.toString(myTime2.get(Calendar.DAY_OF_YEAR));
        meditor.putString(getString(R.string.day2), day2);
        meditor.commit();

        String hour2 = Integer.toString(myTime2.get(Calendar.HOUR_OF_DAY));
        meditor.putString(getString(R.string.hour2), hour2);
        meditor.commit();

        String min2 = Integer.toString(myTime2.get(Calendar.MINUTE));
        meditor.putString(getString(R.string.min2), min2);
        meditor.commit();

        String sec2 = Integer.toString(myTime2.get(Calendar.SECOND));
        meditor.putString(getString(R.string.sec2), sec2);
        meditor.commit();
//3
        String year3 = Integer.toString(myTime3.get(Calendar.YEAR));
        meditor.putString(getString(R.string.year3), year3);
        meditor.commit();

        String day3 = Integer.toString(myTime3.get(Calendar.DAY_OF_YEAR));
        meditor.putString(getString(R.string.day3), day3);
        meditor.commit();

        String hour3 = Integer.toString(myTime3.get(Calendar.HOUR_OF_DAY));
        meditor.putString(getString(R.string.hour3), hour3);
        meditor.commit();

        String min3 = Integer.toString(myTime3.get(Calendar.MINUTE));
        meditor.putString(getString(R.string.min3), min3);
        meditor.commit();

        String sec3 = Integer.toString(myTime3.get(Calendar.SECOND));
        meditor.putString(getString(R.string.sec3), sec3);
        meditor.commit();
*/
    }
    private void savetxt(){
        String text1 = txt1.getText().toString();
        meditor.putString(getString(R.string.mytext), text1);
        meditor.apply();

   //     String text2 = txt2.getText().toString();
   //     meditor.putString(getString(R.string.mytext2), text2);
   //     meditor.apply();

   //     String text3 = txt3.getText().toString();
   //     meditor.putString(getString(R.string.mytext3), text3);
   //     meditor.apply();

        String timernum = Integer.toString(pracnums);
        meditor.putString(getString(R.string.num), timernum);
        meditor.apply();
    }
    private void clearData(int clean){
        //replace saved data in mem with ""
        switch (clean) {
            case 1:
//1
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

                //days.setText("0");
                //hours.setText("00");
                //mins.setText("00");
                //secs.setText("00");
                seconds1 = 0;
                minutes1 = 0;
                hourss1 = 0;
                dayss1 = 0;
                years1 = 0;

            break;

            case 2:
//2
            meditor.putString(getString(R.string.year2), "");
            meditor.commit();

            meditor.putString(getString(R.string.day2), "");
            meditor.commit();

            meditor.putString(getString(R.string.hour2), "");
            meditor.commit();

            meditor.putString(getString(R.string.min2), "");
            meditor.commit();

            meditor.putString(getString(R.string.sec2), "");
            meditor.commit();

            meditor.putString(getString(R.string.mytext2), "");
            meditor.commit();

                seconds2 = 0;
                minutes2 = 0;
                hourss2 = 0;
                dayss2 = 0;
                years2 = 0;

            break;

            case 3:
//3
            meditor.putString(getString(R.string.year3), "");
            meditor.commit();

            meditor.putString(getString(R.string.day3), "");
            meditor.commit();

            meditor.putString(getString(R.string.hour3), "");
            meditor.commit();

            meditor.putString(getString(R.string.min3), "");
            meditor.commit();

            meditor.putString(getString(R.string.sec3), "");
            meditor.commit();

            meditor.putString(getString(R.string.mytext3), "");
            meditor.commit();

                seconds3 = 0;
                minutes3 = 0;
                hourss3 = 0;
                dayss3 = 0;
                years3 = 0;


            break;
        }
        btna2.setText("new " + clean);
    }
    private void fillClk() {
        //if(prac[1].isitrunning()) {
//1
        String year1 = mprefs.getString(getString(R.string.year), "");
        String day1 = mprefs.getString(getString(R.string.day), "");
        String hour1 = mprefs.getString(getString(R.string.hour), "");
        String min1 = mprefs.getString(getString(R.string.min), "");
        String sec1 = mprefs.getString(getString(R.string.sec), "");

        currentTime = Calendar.getInstance();
        if (year1 != "" || day1 != "" || hour1 != "" || min1 != "" || sec1 != ""){
            years1 = currentTime.get(Calendar.YEAR) - Integer.parseInt(String.valueOf(year1));
            dayss1 = currentTime.get(Calendar.DAY_OF_YEAR) - Integer.parseInt(String.valueOf(day1));
            hourss1 = currentTime.get(Calendar.HOUR_OF_DAY) - Integer.parseInt(String.valueOf(hour1));
            minutes1 = currentTime.get(Calendar.MINUTE) - Integer.parseInt(String.valueOf(min1));
            seconds1 = currentTime.get(Calendar.SECOND) - Integer.parseInt(String.valueOf(sec1));

            /*currentTime = Calendar.getInstance();     //uncomment to Troubleshoot
            years = currentTime.get(Calendar.YEAR) - 2019;
            dayss = currentTime.get(Calendar.DAY_OF_YEAR) - 150;
            hourss = currentTime.get(Calendar.HOUR_OF_DAY) - 22;
            minutes = currentTime.get(Calendar.MINUTE) - 22;
            seconds = currentTime.get(Calendar.SECOND) - 0;*/

            if (seconds1 < 0) {
                minutes1 = minutes1 - 1;
                seconds1 = 60 + seconds1;
            }
            if (minutes1 < 0) {
                hourss1 = hourss1 - 1;
                minutes1 = 60 + minutes1;
            }
            if (hourss1 < 0) {
                dayss1 = dayss1 - 1;
                hourss1 = 24 + hourss1;
            }
            if (dayss1 < 0) {
                years1 = years1 - 1;
                dayss1 = 365 + dayss1;
             }
    }
        //}
        //if(prac[2].isitrunning()) {
        /*
//2
            String year2 = mprefs.getString(getString(R.string.year2), "");
            String day2 = mprefs.getString(getString(R.string.day2), "");
            String hour2 = mprefs.getString(getString(R.string.hour2), "");
            String min2 = mprefs.getString(getString(R.string.min2), "");
            String sec2 = mprefs.getString(getString(R.string.sec2), "");

            years2 = currentTime.get(Calendar.YEAR) - Integer.parseInt(String.valueOf(year2));
            dayss2 = currentTime.get(Calendar.DAY_OF_YEAR) - Integer.parseInt(String.valueOf(day2));
            hourss2 = currentTime.get(Calendar.HOUR_OF_DAY) - Integer.parseInt(String.valueOf(hour2));
            minutes2 = currentTime.get(Calendar.MINUTE) - Integer.parseInt(String.valueOf(min2));
            seconds2 = currentTime.get(Calendar.SECOND) - Integer.parseInt(String.valueOf(sec2));

            if (seconds2 < 0) {
                minutes2 = minutes2 - 1;
                seconds2 = 60 + seconds2;
            }
            if (minutes2 < 0) {
                hourss2 = hourss2 - 1;
                minutes2 = 60 + minutes2;
            }
            if (hourss2 < 0) {
                dayss2 = dayss2 - 1;
                hourss2 = 24 + hourss2;
            }
            if (dayss2 < 0) {
                years2 = years2 - 1;
                dayss2 = 365 + dayss2;
            }
        //}
        //if(prac[3].isitrunning()) {
//3
            String year3 = mprefs.getString(getString(R.string.year3), "");
            String day3 = mprefs.getString(getString(R.string.day3), "");
            String hour3 = mprefs.getString(getString(R.string.hour3), "");
            String min3 = mprefs.getString(getString(R.string.min3), "");
            String sec3 = mprefs.getString(getString(R.string.sec3), "");

            years3 = currentTime.get(Calendar.YEAR) - Integer.parseInt(String.valueOf(year3));
            dayss3 = currentTime.get(Calendar.DAY_OF_YEAR) - Integer.parseInt(String.valueOf(day3));
            hourss3 = currentTime.get(Calendar.HOUR_OF_DAY) - Integer.parseInt(String.valueOf(hour3));
            minutes3 = currentTime.get(Calendar.MINUTE) - Integer.parseInt(String.valueOf(min3));
            seconds3 = currentTime.get(Calendar.SECOND) - Integer.parseInt(String.valueOf(sec3));

            if (seconds3 < 0) {
                minutes3 = minutes3 - 1;
                seconds3 = 60 + seconds3;
            }
            if (minutes3 < 0) {
                hourss3 = hourss3 - 1;
                minutes3 = 60 + minutes3;
            }
            if (hourss3 < 0) {
                dayss3 = dayss3 - 1;
                hourss3 = 24 + hourss3;
            }
            if (dayss3 < 0) {
                years3 = years3 - 1;
                dayss3 = 365 + dayss3;
            }
        //}
        */
    }


    //----------------------------------functionality for all the buttons-------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //edit buttons for all the different timers: open the details with openDialog(). pass details into dialog
            case 0: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
                //openDialog(); //opens pop up menu
                Toast.makeText(this, Integer.toString(prac[v.getId()].getnum()), Toast.LENGTH_SHORT).show();
                //prac[3].closelayout();
                closeLayouts();
                break;
            case 1:
                Toast.makeText(this, Integer.toString(prac[v.getId()].getnum()), Toast.LENGTH_SHORT).show();
                closeLayouts();

                //Button btna = new Button(this);
                btna.setText("reset " + v.getId());
                btna.setHeight(50);
                btna.setWidth(200);
                btna.setOnClickListener(this);
                //Button btna2 = new Button(this);
                if(!prac[v.getId()].isitrunning())
                btna2.setText("new " + v.getId());
                else
                    btna2.setText("stop " + v.getId());
                btna2.setHeight(50);
                btna2.setWidth(200);
                btna2.setOnClickListener(this);
                btna3.setText("delete " + v.getId());
                btna3.setHeight(50);
                btna3.setWidth(200);
                btna3.setOnClickListener(this);
                prac[v.getId()].openlayout(btna,btna2,btna3);

                break;
            case 2:
                Toast.makeText(this, Integer.toString(prac[v.getId()].getnum()), Toast.LENGTH_SHORT).show();
                closeLayouts();

                //Button btnb = new Button(this);
                btna.setText("reset 2 ");
                btna.setHeight(50);
                btna.setWidth(200);
                btna.setOnClickListener(this);
                //Button btnb2 = new Button(this);
                if(!prac[v.getId()].isitrunning())
                    btna2.setText("new " + v.getId());
                else
                    btna2.setText("stop " + v.getId());
                btna2.setHeight(50);
                btna2.setWidth(200);
                btna2.setOnClickListener(this);
                btna3.setText("delete " + v.getId());
                btna3.setHeight(50);
                btna3.setWidth(200);
                btna3.setOnClickListener(this);
                prac[v.getId()].openlayout(btna,btna2,btna3);

                break;
            case 3:
                Toast.makeText(this, Integer.toString(prac[v.getId()].getnum()), Toast.LENGTH_SHORT).show();
                closeLayouts();

                //Button btnc = new Button(this);
                btna.setText("reset 3 ");
                btna.setHeight(50);
                btna.setWidth(200);
                btna.setOnClickListener(this);
                //Button btnc2 = new Button(this);
                if(!prac[v.getId()].isitrunning())
                    btna2.setText("new " + v.getId());
                else
                    btna2.setText("stop " + v.getId());
                btna2.setHeight(50);
                btna2.setWidth(200);
                btna2.setOnClickListener(this);
                btna3.setText("delete " + v.getId());
                btna3.setHeight(50);
                btna3.setWidth(200);
                btna3.setOnClickListener(this);
                prac[v.getId()].openlayout(btna,btna2,btna3);

                break;

            //"add new" button: adds a new timer with details and edit button
            case R.id.add:
                if (pracnums < 20) {
                    pracnums++;
                    //pracnums = 0;
                    newlayout(pracnums);
                }
                else{
                    pracnums = 20;
                    Toast.makeText(this, "can only have 20", Toast.LENGTH_SHORT).show();
                }

                break;
            case 800:
                //btna
                //look through all the prac classes, see whose opened.
                //copy reset btn
                for(int x = 1; x < pracnums; x++){
                    if(prac[x].isitopen()){
                        clearData(x);
                        prac[x].notrunning();
                        btna2.setText("new " + x);
                    }
                }

                break;
            case 801:
                //btna2
                //look through all the prac classes, see whose opened.
                //copy new/stop btn
                for(int x = 1; x < pracnums; x++){
                    if(prac[x].isitopen()){
                       // clearData(x);
                        if (prac[x].isitrunning()) {
                            //prac[x].notrunning();
                            btna2.setText("new " + x);
                        }else {

                            //prac[x].newtime(Calendar.getInstance());
                            //updateData();
                            //prac[x].isrunning();
                            btna2.setText("stop " + x);
                        }
                    }
                }
                break;
            case 802:
                //look through all the prac classes, see whose opened.
                //make delete btn
                int fill = 0;
                for(int x = 1; x <= pracnums; x++){
                    if(prac[x].isitopen()){
                    clearData(x);
                    prac[x].notrunning();
                    prac[x].deleteme();
                    //pracnums--;
                   // fill = x;
                    for (int c = x; c < pracnums; c++){
                        prac[c] = prac[c + 1];
                        prac[c].updatenum(c);
                        prac[c].updatelayout();
                    }

                    }
                }

                //clearData(pracnums);
                //prac[pracnums].notrunning();
                //prac[pracnums].deleteme();
                pracnums--;
                break;
            //    should move into dialog window
            case R.id.resetbtn:
                clearData(1);
                days.setText("0");
                hours.setText("00");
                mins.setText("00");
                secs.setText("00");
                seconds1 = 0;
                minutes1 = 0;
                hourss1 = 0;
                dayss1 = 0;
                years1 = 0;
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
                    years1 = 0;
                    dayss1 = 0;
                    hourss1 = 0;
                    minutes1 = 0;
                    seconds1 = 0;

                    updateData();
                    startTimer();
                    newtime.setText("STOP");
                    break;

                }
        }
    }

    private void openDialog() { //used for popup menu
        exampledialog mydialog = new exampledialog();
        mydialog.show(getSupportFragmentManager(), "edittimer");
    }

    @Override
    public void applytext(String num) { //used for popup menu
        currnum.setText(num);
    }

    public void closeLayouts(){
        for(int x = 1; x < pracnums; x++){
            prac[x].closelayout();
        }
    }

    public void newlayout(int newpracnums){
        currnum.setText(Integer.toString(newpracnums));
        // int[] gay = new int[100];
        // gay[pracnums] = pracnums;

        String formatstuff = String.format("%02d      ", newpracnums);

        TextView multiday = new TextView(daysSince.this);
        multiday.setText("Days: " + formatstuff);
        TextView multihour = new TextView(daysSince.this);
        multihour.setText("Hours: " + formatstuff);
        TextView multimin = new TextView(daysSince.this);
        multimin.setText("Mins: " + formatstuff);
        TextView multisec = new TextView(daysSince.this);
        multisec.setText("Secs: " + formatstuff);

        LinearLayout horz = new LinearLayout(daysSince.this);
        //horz.setHorizontalGravity(1);
        horz.setOnClickListener(this);
        horz.setId(newpracnums);

        horz.setPadding(20,20,20,20);
        //horz.setDividerPadding(100);
        //prac[pracnums] = new pracClass();
        horz.addView(multiday);
        horz.addView(multihour);
        horz.addView(multimin);
        horz.addView(multisec);
        //horz.addView(pracbtn);

        LinearLayout horz2 = new LinearLayout(daysSince.this);
        horz2.setPadding(0,0,0,0);

        layout1.addView(horz);
        layout1.addView(horz2);
        prac[newpracnums] = new pracClass(Calendar.getInstance(), newpracnums, horz, horz2, multiday, multihour, multimin, multisec);
        prac[newpracnums].isalive();
        prac[newpracnums].notrunning();
    }
}



