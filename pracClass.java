package dopemz.dayssince;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


public class pracClass{

    private TextView myname;
    private int mynum, days, hours, mins, secs;
    private Button btn;
    private LinearLayout mylayout2;
    private LinearLayout mylayout1;
    private boolean open;
    private boolean running;
    private boolean alive;
    private Calendar mytime;
    private TextView dtxt;
    private TextView htxt;
    private TextView mtxt;
    private TextView stxt;

    public pracClass(){
        mytime = Calendar.getInstance();
        mynum = 400;
        mylayout1 = null;
        mylayout2 = null;

    }

    public pracClass(Calendar time, int nums, LinearLayout layout1, LinearLayout layout2, TextView d, TextView h, TextView m, TextView s){
        mytime = time;
        mynum = nums;
        //btn = mybtn;
        mylayout1 = layout1;
        mylayout2 = layout2;

        dtxt = d;
        htxt = h;
        mtxt = m;
        stxt = s;
    }

    public void newtime(Calendar time){
        mytime = time;
    }

    public Calendar gettime(){
        return mytime;
    }

    //public Calendar getMytime(){return mytime;}
    public int getMynum(){return mynum;}
    public LinearLayout getMylayout1(){return mylayout1;}
    public LinearLayout getMylayout2(){return mylayout2;}


    public int getnum(){
        return mynum;
    }
    public void updatenum(int newnumber){
        mynum = newnumber;
    }

    public void updatelayout(){
        mylayout1.setId(mynum);
    }
    public LinearLayout getlayout(){
        return mylayout2;
    }


    public void openlayout(Button button, Button button2, Button button3){
        open = true;
        mylayout2.setMinimumHeight(200);
        btn = button;
        mylayout2.addView(button);
        mylayout2.addView(button2);
        mylayout2.addView(button3);
    }
    public void closelayout(){
        open = false;
        mylayout2.removeAllViews();
        mylayout2.setMinimumHeight(0);
        //mylayout2.setVisibility(View.GONE);
    }

    public void deleteme(){
        //alive = false;
        //closelayout();
        mylayout1.removeAllViews();
        //mylayout1.setMinimumHeight(0);
        mylayout1.setVisibility(View.GONE);
        mylayout2.removeAllViews();
        //mylayout2.setMinimumHeight(0);
        mylayout2.setVisibility(View.GONE);

    }
    public boolean isitopen(){
        return open;
    }

    public void isrunning(){
        running = true;
    }
    public void notrunning(){
        running = false;
    }
    public boolean isitrunning(){
        return running;
    }

    public void isalive(){
        alive = true;
    }
    public void notalive(){
        alive = false;
    }
    public boolean isitalive(){
        return alive;
    }

    public void setTime(){
        String secsleftformat = String.format("%02d", secs);
        String minsleftformat = String.format("%02d", mins);
        String hoursleftformat = String.format("%02d", hours);
        stxt.setText("     " + secsleftformat + "     ");
        mtxt.setText("     " + minsleftformat + "     ");
        htxt.setText("     " + hoursleftformat+ "     ");
        dtxt.setText("     " + Integer.toString(days) + "     ");

        //dtxt.setText(Integer.toString(days));
        //htxt.setText(Integer.toString(hours));
        //mtxt.setText(Integer.toString(mins));
        //stxt.setText(Integer.toString(secs));
    }
    public void updatenums(int day, int hour, int min, int sec){
        days = day;
        hours = hour;
        mins = min;
        secs = sec;
    }

}
