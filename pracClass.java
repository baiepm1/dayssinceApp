package dopemz.dayssince;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;


public class pracClass{

    private TextView myname;
    private int mynum;
    private Button btn;
    private LinearLayout mylayout2;
    private LinearLayout mylayout1;
    //private int mylayout;
    private boolean open;
    private boolean running;
    private boolean alive;
    private Calendar mytime;

    public pracClass(){
        mynum = 100;
        myname = null;

    }

    public pracClass(Calendar time, int nums, LinearLayout layout1, LinearLayout layout2){
        mytime = time;
        mynum = nums;
        //btn = mybtn;
        mylayout1 = layout1;
        mylayout2 = layout2;
    }

    public void newtime(Calendar time){
        mytime = time;
    }

    public int getnum(){
        return mynum;
    }

    public LinearLayout getlayout(){
        return mylayout2;
    }

    /*public int getlayout(){
        return mylayout;
    }*/

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
        alive = false;
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

}
