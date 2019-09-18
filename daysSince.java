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
    //private int secarray[1] = 0;    //for updating clk
    //private int minarray[1] = 0;    //for updating clk
    //private int hourarray[1] = 0;     //for updating clk
    //private int dayarray[1] = 0;      //for updating clk
    //private int yeararray[1] = 0;
//2
    //private int secarray[2] = 0;    //for updating clk
    //private int minarray[2] = 0;    //for updating clk
    //private int hourarray[2] = 0;     //for updating clk
    //private int dayarray[2] = 0;      //for updating clk
    //private int yeararray[2] = 0;
//3
    private int seconds3 = 0;    //for updating clk
    private int minutes3 = 0;    //for updating clk
    private int hourss3 = 0;     //for updating clk
    private int dayss3 = 0;      //for updating clk
    private int years3 = 0;

    private int idnum = 1;

    //initializing everything as an array

    private int secarray[] = new int[50];
    private int minarray[] = new int[50];
    private int hourarray[] = new int[50];
    private int dayarray[] = new int[50];
    private int yeararray[] = new int[50];

    private int good[] = new int[50];


    private String yearstrings[] = new String[50];
    private String daystrings[] = new String[50];
    private String hourstrings[] = new String[50];
    private String minstrings[] = new String[50];
    private String secstrings[] = new String[50];
    private String textstrings[] = new String[50];
    private String runstrings[] = new String[50];




    private Calendar myTime;
    private Calendar myTime2;
    private Calendar myTime3;
    private Calendar currentTime;
    private SharedPreferences mprefs;
    private SharedPreferences.Editor meditor;
    //private int numOfTimers = 0;
    private int pracnums = 0;
    //private int good[1] = 0;
    //private int good[2] = 0;
    private int good3 = 0;
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

        fillstringarray();


       // daysClass[] mme = new daysClass[50];
        //mme[0] = new daysClass(txt1, prac1, prac2, prac3, prac4, resettime, newtime, dayss, hourss, minutes, seconds);
        mme[0] = new daysClass(txt1, prac1, prac2, prac3, prac4, resettime, newtime, dayarray[1], hourarray[1], minarray[1], secarray[1]);

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

    public void fillstringarray(){

        yearstrings[1] = getString(R.string.year1);
        daystrings[1] = getString(R.string.day1);
        hourstrings[1] = getString(R.string.hour1);
        minstrings[1] = getString(R.string.min1);
        secstrings[1] = getString(R.string.sec1);
        textstrings[1] = getString(R.string.mytext1);
        runstrings[1] = getString(R.string.run1);

        yearstrings[2] = getString(R.string.year2);
        daystrings[2] = getString(R.string.day2);
        hourstrings[2] = getString(R.string.hour2);
        minstrings[2] = getString(R.string.min2);
        secstrings[2] = getString(R.string.sec2);
        textstrings[2] = getString(R.string.mytext2);
        runstrings[2] = getString(R.string.run2);

        yearstrings[3] = getString(R.string.year3);
        daystrings[3] = getString(R.string.day3);
        hourstrings[3] = getString(R.string.hour3);
        minstrings[3] = getString(R.string.min3);
        secstrings[3] = getString(R.string.sec3);
        textstrings[3] = getString(R.string.mytext3);
        runstrings[3] = getString(R.string.run3);

        yearstrings[4] = getString(R.string.year4);
        daystrings[4] = getString(R.string.day4);
        hourstrings[4] = getString(R.string.hour4);
        minstrings[4] = getString(R.string.min4);
        secstrings[4] = getString(R.string.sec4);
        textstrings[4] = getString(R.string.mytext4);
        runstrings[4] = getString(R.string.run4);

        yearstrings[5] = getString(R.string.year5);
        daystrings[5] = getString(R.string.day5);
        hourstrings[5] = getString(R.string.hour5);
        minstrings[5] = getString(R.string.min5);
        secstrings[5] = getString(R.string.sec5);
        textstrings[5] = getString(R.string.mytext5);
        runstrings[5] = getString(R.string.run5);

        yearstrings[6] = getString(R.string.year6);
        daystrings[6] = getString(R.string.day6);
        hourstrings[6] = getString(R.string.hour6);
        minstrings[6] = getString(R.string.min6);
        secstrings[6] = getString(R.string.sec6);
        textstrings[6] = getString(R.string.mytext6);
        runstrings[6] = getString(R.string.run6);

        yearstrings[7] = getString(R.string.year7);
        daystrings[7] = getString(R.string.day7);
        hourstrings[7] = getString(R.string.hour7);
        minstrings[7] = getString(R.string.min7);
        secstrings[7] = getString(R.string.sec7);
        textstrings[7] = getString(R.string.mytext7);
        runstrings[7] = getString(R.string.run7);

        yearstrings[8] = getString(R.string.year8);
        daystrings[8] = getString(R.string.day8);
        hourstrings[8] = getString(R.string.hour8);
        minstrings[8] = getString(R.string.min8);
        secstrings[8] = getString(R.string.sec8);
        textstrings[8] = getString(R.string.mytext8);
        runstrings[8] = getString(R.string.run8);

        yearstrings[9] = getString(R.string.year6);
        daystrings[9] = getString(R.string.day9);
        hourstrings[9] = getString(R.string.hour9);
        minstrings[9] = getString(R.string.min9);
        secstrings[9] = getString(R.string.sec9);
        textstrings[9] = getString(R.string.mytext9);
        runstrings[9] = getString(R.string.run9);

        yearstrings[10] = getString(R.string.year10);
        daystrings[10] = getString(R.string.day10);
        hourstrings[10] = getString(R.string.hour10);
        minstrings[10] = getString(R.string.min10);
        secstrings[10] = getString(R.string.sec10);
        textstrings[10] = getString(R.string.mytext10);
        runstrings[10] = getString(R.string.run10);

        yearstrings[11] = getString(R.string.year11);
        daystrings[11] = getString(R.string.day11);
        hourstrings[11] = getString(R.string.hour11);
        minstrings[11] = getString(R.string.min11);
        secstrings[11] = getString(R.string.sec11);
        textstrings[11] = getString(R.string.mytext11);
        runstrings[11] = getString(R.string.run11);

        yearstrings[12] = getString(R.string.year12);
        daystrings[12] = getString(R.string.day12);
        hourstrings[12] = getString(R.string.hour12);
        minstrings[12] = getString(R.string.min12);
        secstrings[12] = getString(R.string.sec12);
        textstrings[12] = getString(R.string.mytext12);
        runstrings[12] = getString(R.string.run12);

    }
    public void checkprefs(){           //get values from mem, run if data is there

        String text1 = mprefs.getString(textstrings[1], "");
        String timernum = mprefs.getString(getString(R.string.num), "0");
        String run = "";
        pracnums = Integer.parseInt(timernum);

        for (int x = 1; x <= pracnums; x++) {
            run = mprefs.getString(runstrings[x], "0");
            if (run == "")
                good[x] = 0;
            else
                good[x] = Integer.parseInt(run);
        }

        if (pracnums > 0){
            for(int i = 1; i <= pracnums; i++){
                newlayout(i);
            }
        }

        for (int x = 1; x <= pracnums; x++) {
            //good[1] = Integer.parseInt(run1);
            if (good[x] == 1) {
                prac[x].isrunning();
            }
            else
                prac[x].notrunning();
        }

        txt1.setText(text1);

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
        mme[0].updatenums(dayarray[1], hourarray[1], minarray[1], secarray[1]);
        mme[0].setTime();

        //prac[1].updatenums(dayarray[1], hourarray[1], minarray[1], secarray[1]);
        //prac[1].setTime();
        //prac[2].updatenums(dayarray[2], hourarray[2], minarray[2], secarray[2]);
        //prac[2].setTime();
        //prac[3].updatenums(dayarray[3], hourarray[3], minarray[3], secarray[3]);
        //prac[3].setTime();

        for(int x = 1; x <= pracnums; x++){
            prac[x].updatenums(dayarray[x], hourarray[x], minarray[x], secarray[x]);
            prac[x].setTime();
        }

        String secsleftformat = String.format("%02d", secarray[1]);
        String minsleftformat = String.format("%02d", minarray[1]);
        String hoursleftformat = String.format("%02d", hourarray[1]);
        secs.setText(secsleftformat);
        mins.setText(minsleftformat);
        hours.setText(hoursleftformat);
        days.setText(Integer.toString(dayarray[1]));
        savetxt();

    }
    private void updateData(int num) {
        //get times from mytime, save it to xml in mem


        if (num == 999){
            //num = 1;
            for(num = 1; num < pracnums; num++){
                if (prac[num].isitrunning()) {
                    good[num] = 1;
                } else
                    good[num] = 0;

                String year1 = Integer.toString(prac[num].gettime().get(Calendar.YEAR));
                meditor.putString(yearstrings[num], year1);
                meditor.commit();

                String day1 = Integer.toString(prac[num].gettime().get(Calendar.DAY_OF_YEAR));
                meditor.putString(daystrings[num], day1);
                meditor.commit();

                String hour1 = Integer.toString(prac[num].gettime().get(Calendar.HOUR_OF_DAY));
                meditor.putString(hourstrings[num], hour1);
                meditor.commit();

                String min1 = Integer.toString(prac[num].gettime().get(Calendar.MINUTE));
                meditor.putString(minstrings[num], min1);
                meditor.commit();

                String sec1 = Integer.toString(prac[num].gettime().get(Calendar.SECOND));
                meditor.putString(secstrings[num], sec1);
                meditor.commit();


                String run1 = Integer.toString(good[num]);
                meditor.putString(runstrings[num], run1);
                meditor.commit();
            }
        }
        else {
            if (prac[num].isitrunning()) {
                good[num] = 1;
            } else
                good[num] = 0;

            String year1 = Integer.toString(prac[num].gettime().get(Calendar.YEAR));
            meditor.putString(yearstrings[num], year1);
            meditor.commit();

            String day1 = Integer.toString(prac[num].gettime().get(Calendar.DAY_OF_YEAR));
            meditor.putString(daystrings[num], day1);
            meditor.commit();

            String hour1 = Integer.toString(prac[num].gettime().get(Calendar.HOUR_OF_DAY));
            meditor.putString(hourstrings[num], hour1);
            meditor.commit();

            String min1 = Integer.toString(prac[num].gettime().get(Calendar.MINUTE));
            meditor.putString(minstrings[num], min1);
            meditor.commit();

            String sec1 = Integer.toString(prac[num].gettime().get(Calendar.SECOND));
            meditor.putString(secstrings[num], sec1);
            meditor.commit();


            String run1 = Integer.toString(good[num]);
            meditor.putString(runstrings[num], run1);
            meditor.commit();
        }


    }
    private void savetxt(){
        String text1 = txt1.getText().toString();
        meditor.putString(textstrings[1], text1);
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

//1
            meditor.putString(yearstrings[clean], "");
            meditor.commit();

            meditor.putString(daystrings[clean], "");
            meditor.commit();

            meditor.putString(hourstrings[clean], "");
            meditor.commit();

            meditor.putString(minstrings[clean], "");
            meditor.commit();

            meditor.putString(secstrings[clean], "");
            meditor.commit();

            meditor.putString(textstrings[clean], "");
            meditor.commit();

            meditor.putString(runstrings[clean], "");
            meditor.commit();

                //days.setText("0");
                //hours.setText("00");
                //mins.setText("00");
                //secs.setText("00");
                secarray[clean] = 0;
                minarray[clean] = 0;
                hourarray[clean] = 0;
                dayarray[clean] = 0;
                yeararray[clean] = 0;

                if(clean != pracnums)
                    btna2.setText("new " + clean);
    }
    private void fillClk() {
        currentTime = Calendar.getInstance();

        String syear = "";
        String sday = "";
        String shour = "";
        String smin = "";
        String ssec = "";

        for (int x = 1; x <= pracnums; x++) {
            if (prac[x].isitalive() && prac[x].isitrunning()) {
//all
                syear = mprefs.getString(yearstrings[x], "");
                sday = mprefs.getString(daystrings[x], "");
                shour = mprefs.getString(hourstrings[x], "");
                smin = mprefs.getString(minstrings[x], "");
                ssec = mprefs.getString(secstrings[x], "");

                if (syear != "" || sday != "" || shour != "" || smin != "" || ssec != "") {
                    yeararray[x] = currentTime.get(Calendar.YEAR) - Integer.parseInt(String.valueOf(syear));
                    dayarray[x] = currentTime.get(Calendar.DAY_OF_YEAR) - Integer.parseInt(String.valueOf(sday));
                    hourarray[x] = currentTime.get(Calendar.HOUR_OF_DAY) - Integer.parseInt(String.valueOf(shour));
                    minarray[x] = currentTime.get(Calendar.MINUTE) - Integer.parseInt(String.valueOf(smin));
                    secarray[x] = currentTime.get(Calendar.SECOND) - Integer.parseInt(String.valueOf(ssec));

                    if (secarray[x] < 0) {
                        minarray[x] = minarray[x] - 1;
                        secarray[x] = 60 + secarray[x];
                    }
                    if (minarray[x] < 0) {
                        hourarray[x] = hourarray[x] - 1;
                        minarray[x] = 60 + minarray[x];
                    }
                    if (hourarray[x] < 0) {
                        dayarray[x] = dayarray[x] - 1;
                        hourarray[x] = 24 + hourarray[x];
                    }
                    if (dayarray[x] < 0) {
                        yeararray[x] = yeararray[x] - 1;
                        dayarray[x] = 365 + dayarray[x];
                    }
                }
            }
        }
    }

    //----------------------------------functionality for all the buttons-------------------------------------
    @Override
    public void onClick(View v) {
        //prac[idnum].closelayout();
        idnum = v.getId();
        switch (idnum) {
            //edit buttons for all the different timers: open the details with openDialog(). pass details into dialog
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7: case 8: case 9: case 10:
            case 11: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20:
                buttonstuff(idnum);
                break;

            case R.id.add:
                //(ADD NEW)
                //increases pracnums then makes a new row/pracclass
                if (pracnums < 20) {
                    pracnums++;
                    //pracnums = 0;
                    newlayout(pracnums);
                    savetxt();
                }
                else{
                    pracnums = 20;
                    Toast.makeText(this, "can only have 20", Toast.LENGTH_SHORT).show();
                }

                break;
            case 800:
                //btna (RESET)
                //look through all the prac classes, see whose opened.
                //copy reset btn
                for(int x = 1; x <= pracnums; x++){
                    if(prac[x].isitopen()){
                        clearData(x);
                        prac[x].notrunning();
                        btna2.setText("new " + x);
                    }
                }

                break;
            case 801:
                //btna2 (NEW)
                //look through all the prac classes, see whose opened.
                //start clock to count from 0/current time
                for(int x = 1; x <= pracnums; x++){
                    if(prac[x].isitopen()){
                       // clearData(x);
                        if (prac[x].isitrunning()) {    //stop the timer
                            prac[x].notrunning();
                            updateData(x);
                            btna2.setText("new " + x);  //stop -> new
                        }else {
                            prac[x].newtime(Calendar.getInstance());    //start the timer
                            prac[x].isrunning();
                            //prac[x].newtime(Calendar.getInstance());
                            updateData(x);
                            btna2.setText("stop " + x); //new -> stop
                        }
                    }
                }
                break;
            case 802:
                //btna3 (DELETE)
                //look through all the prac classes, see whose opened.
                //make delete btn
                for(int x = 1; x <= pracnums; x++){
                    if(prac[x].isitopen()){
                        //prac[x].notrunning();
                        //clearData(x);
                        //updateData(x);
                        //prac[x].deleteme();
                        //prac[x].closelayout();
                    for (int c = x; c < pracnums; c++){

                       //prac[40] = prac[c];         //c gets saved to a temp
                        //prac[c] = prac[c + 1];      //c gets filled by above
                        //prac[c + 1] = prac[40];     //above gets filled by temp
                        //prac[c].updatenum(c);
                        //prac[c].updatelayout();
                        prac[c].newtime(prac[c + 1].gettime());
                        //clearData(c + 1);
                        //prac[c + 1].updatenums(0,0,0,0);
                        updateData(c);
                        //updateData(c + 1);
                    }

                    }
                }
                //prac[pracnums].updatenums(0,0,0,0);
                //prac[pracnums + 1].updatenums(0,0,0,0);     //for some reason one higher exists, just clear it too
                clearData(pracnums);
                //clearData(pracnums + 1);
                prac[pracnums].deleteme();
                prac[pracnums].notrunning();
                prac[pracnums].notalive();
                //updateData(999);     //999 == update all
                pracnums--;
                currnum.setText(Integer.toString(pracnums));
                break;
            //    should move into dialog window
            case R.id.resetbtn:
                clearData(1);
                days.setText("0");
                hours.setText("00");
                mins.setText("00");
                secs.setText("00");
                secarray[1] = 0;
                minarray[1] = 0;
                hourarray[1] = 0;
                dayarray[1] = 0;
                yeararray[1] = 0;
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
                    yeararray[1] = 0;
                    dayarray[1] = 0;
                    hourarray[1] = 0;
                    minarray[1] = 0;
                    secarray[1] = 0;

                    updateData(1);
                    startTimer();
                    newtime.setText("STOP");
                    break;

                }
        }
    }

    private void buttonstuff(int vnum){
        Toast.makeText(this, Integer.toString(prac[vnum].getnum()), Toast.LENGTH_SHORT).show();
        closeLayouts();

        //Button btna = new Button(this);
        btna.setText("reset " + vnum);
        btna.setHeight(50);
        btna.setWidth(200);
        btna.setOnClickListener(this);
        //Button btna2 = new Button(this);
        if(!prac[vnum].isitrunning())
            btna2.setText("new " + vnum);
        else
            btna2.setText("stop " + vnum);
        btna2.setHeight(50);
        btna2.setWidth(200);
        btna2.setOnClickListener(this);
        btna3.setText("delete " + vnum);
        btna3.setHeight(50);
        btna3.setWidth(200);
        btna3.setOnClickListener(this);
        prac[vnum].openlayout(btna,btna2,btna3);

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
        for(int x = 1; x <= pracnums; x++){
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



